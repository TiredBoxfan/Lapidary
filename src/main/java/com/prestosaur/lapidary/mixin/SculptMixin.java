package com.prestosaur.lapidary.mixin;

import com.prestosaur.lapidary.Config;
import com.prestosaur.lapidary.accessors.PlayerSculptAccessor;
import com.prestosaur.lapidary.enchantment.LapidaryEnchantments;
import com.prestosaur.lapidary.util.SculptParticlesDelta;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraftforge.common.ForgeMod.BLOCK_REACH;

@Mixin(DiggerItem.class)
public abstract class SculptMixin extends Item {

    public SculptMixin(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack pStack) {
        return UseAnim.BRUSH;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack pStack) {
        return Config.sculptTime;
    }

    // Referenced from BrushItem.
    @Unique
    private HitResult lapidary$calculateHitResult(LivingEntity pEntity) {
        return ProjectileUtil.getHitResultOnViewVector(pEntity, (p) -> !p.isSpectator() && p.isPickable(), pEntity instanceof Player player ? player.getBlockReach() : BLOCK_REACH.get().getDefaultValue());
    }

    @Unique
    protected boolean lapidary$isTargetedBlock(Player player, BlockPos blockpos) {
        BlockPos target = ((PlayerSculptAccessor) player).getLastSculptLocation();
        return blockpos != null && target != null && blockpos.getX() == target.getX() && blockpos.getY() == target.getY() && blockpos.getZ() == target.getZ();
    }

    @Unique
    public void lapidary$useOnSculpt(UseOnContext pContext, CallbackInfoReturnable<InteractionResult> info) {
        Player player = pContext.getPlayer();
        BlockPos blockpos = pContext.getClickedPos();

        // Cancel if there is no player.
        if (player == null) {
            info.setReturnValue(InteractionResult.PASS);
            return;
        }

        // If the item is in the main hand and there is an off-hand item, give that off-hand item priority.
        if (pContext.getHand() == InteractionHand.MAIN_HAND && !player.getItemInHand(InteractionHand.OFF_HAND).isEmpty() && !(player.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof DiggerItem)) {
            info.setReturnValue(InteractionResult.PASS);
            return;
        }

        // Get flags.
        boolean hasSculptEnchant = EnchantmentHelper.getEnchantmentLevel(LapidaryEnchantments.SCULPT.get(), player) >= 1;
        if (!hasSculptEnchant) {
            boolean sameBlock = lapidary$isTargetedBlock(player, blockpos);
            boolean usingTool = player.isUsingItem();
            boolean useTick = player.getUseItemRemainingTicks() == 1;

            // Determine if a state is either new or old.
            if (!sameBlock || !usingTool) {
                // Stop using any item.
                player.stopUsingItem();

                // Disable sculpting all together for silk touch.
                if ((EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player) >= 1 && Config.disableSilkTouchSculpt)) {
                    info.setReturnValue(InteractionResult.PASS);
                    return;
                }

                // Set position and start to use.
                ((PlayerSculptAccessor) player).setLastSculptLocation(blockpos);
                player.startUsingItem(pContext.getHand());

                info.setReturnValue(InteractionResult.CONSUME);
            } else if (!useTick) { // Do not fall to Vanilla method unless useTick is true.
                info.setReturnValue(InteractionResult.CONSUME_PARTIAL);
            }
        }
    }

    //@Unique
    //public void lapidary$onUseTickSculpt(@NotNull Level pLevel, @NotNull LivingEntity pLivingEntity, @NotNull ItemStack pStack, int pRemainingUseDuration) {
    public void onUseTick(@NotNull Level pLevel, @NotNull LivingEntity pLivingEntity, @NotNull ItemStack pStack, int pRemainingUseDuration) {
        if (pLivingEntity instanceof Player player && pRemainingUseDuration >= 0) {
            // Get the targeted block information, if any.
            HitResult hitResult = lapidary$calculateHitResult(pLivingEntity);
            if (hitResult instanceof BlockHitResult blockHitResult) {
                // Ensure the same block is being targeted.
                BlockPos blockpos = blockHitResult.getBlockPos();
                if (lapidary$isTargetedBlock(player, blockpos)) {
                    if (pRemainingUseDuration == 1) { // Only use when exactly 1 remaining use ticks are left.
                        useOn(new UseOnContext(player, player.getUsedItemHand(), blockHitResult));
                        pLivingEntity.releaseUsingItem();
                    } else if (pRemainingUseDuration % 10 == 5) {
                        HumanoidArm arm = pLivingEntity.getUsedItemHand() == InteractionHand.MAIN_HAND ? player.getMainArm() : player.getMainArm().getOpposite();
                        this.lapidary$spawnDustParticles(pLevel, blockHitResult, pLevel.getBlockState(blockpos), pLivingEntity.getViewVector(0.0F), arm);
                    }
                } else {
                    // Release due to a different block being targeted.
                    pLivingEntity.releaseUsingItem();
                }
            } else {
                // Release because no block is being targeted.
                pLivingEntity.releaseUsingItem();
            }
        } else {
            // Release because action is not done by a player.
            pLivingEntity.releaseUsingItem();
        }
    }

    // Borrowed from BrushItem.
    @Unique
    public void lapidary$spawnDustParticles(Level pLevel, BlockHitResult pHitResult, BlockState pState, Vec3 pPos, HumanoidArm pArm) {
        int i = pArm == HumanoidArm.RIGHT ? 1 : -1;
        int j = pLevel.getRandom().nextInt(7, 12);
        BlockParticleOption blockparticleoption = new BlockParticleOption(ParticleTypes.BLOCK, pState);
        Direction direction = pHitResult.getDirection();
        SculptParticlesDelta particlesDelta = SculptParticlesDelta.fromDirection(pPos, direction);
        Vec3 vec3 = pHitResult.getLocation();

        for(int k = 0; k < j; ++k) {
            pLevel.addParticle(blockparticleoption, vec3.x - (double)(direction == Direction.WEST ? 1.0E-6F : 0.0F), vec3.y, vec3.z - (double)(direction == Direction.NORTH ? 1.0E-6F : 0.0F), particlesDelta.xd() * (double)i * 3.0D * pLevel.getRandom().nextDouble(), 0.0D, particlesDelta.zd() * (double)i * 3.0D * pLevel.getRandom().nextDouble());
        }

    }
}