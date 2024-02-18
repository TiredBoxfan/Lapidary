package com.prestosaur.lapidary.mixin;

import com.prestosaur.lapidary.Config;
import com.prestosaur.lapidary.accessors.PlayerSculptAccessor;
import com.prestosaur.lapidary.enchantment.LapidaryEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraftforge.common.ForgeMod.BLOCK_REACH;

@Mixin(ShovelItem.class)
public abstract class ShovelItemMixin extends Item {

    public ShovelItemMixin(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack pStack) {
        return UseAnim.BRUSH;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack pStack) {
        return 120; // TODO: Determine ideal value.
    }

    // Referenced from BrushItem.
    @Unique
    private HitResult lapidary$calculateHitResult(LivingEntity pEntity) {
        return ProjectileUtil.getHitResultOnViewVector(pEntity, (p) -> !p.isSpectator() && p.isPickable(), pEntity instanceof Player player ? player.getBlockReach() : BLOCK_REACH.get().getDefaultValue());
    }

    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    public void useOnMixin(UseOnContext pContext, CallbackInfoReturnable<InteractionResult> info) {
        Player player = pContext.getPlayer();
        BlockPos blockpos = pContext.getClickedPos();

        // Cancel if there is no player.
        if (player == null) {
            info.setReturnValue(InteractionResult.PASS);
            return;
        }

        // If the item is in the main hand and there is an off-hand item, give that off-hand item priority.
        if (pContext.getHand() == InteractionHand.MAIN_HAND && !player.getItemInHand(InteractionHand.OFF_HAND).isEmpty()) {
            info.setReturnValue(InteractionResult.PASS);
            return;
        }

        // Get flags.
        boolean hasSculptEnchant = EnchantmentHelper.getEnchantmentLevel(LapidaryEnchantments.SCULPT.get(), player) >= 1;
        boolean sameBlock = lapidary$isTargetedBlock(player, blockpos);
        boolean usingTool = player.isUsingItem();
        boolean useTick = player.getUseItemRemainingTicks() == 1;

        // Determine if a state is either new or old.
        if (!hasSculptEnchant && (!sameBlock || !usingTool)) {
            // Stop using any item.
            player.stopUsingItem();

            // Disable sculpting all together for silk touch.
            if ((EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player) >= 1 && Config.disableSilkTouchSculpt)) {
                info.setReturnValue(InteractionResult.PASS);
                return;
            }

            // Set position and start to use.
            ((PlayerSculptAccessor)player).setLastSculptLocation(blockpos);
            player.startUsingItem(pContext.getHand());

            info.setReturnValue(InteractionResult.CONSUME);
        } else if (!useTick) {
            info.setReturnValue(InteractionResult.CONSUME_PARTIAL);
        }
    }

    @Override
    public void onUseTick(@NotNull Level pLevel, @NotNull LivingEntity pLivingEntity, @NotNull ItemStack pStack, int pRemainingUseDuration) {
        System.out.println(pRemainingUseDuration);
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

    @Unique
    protected boolean lapidary$isTargetedBlock(Player player, BlockPos blockpos) {
        BlockPos target = ((PlayerSculptAccessor)player).getLastSculptLocation();
        return blockpos != null && target != null && blockpos.getX() == target.getX() && blockpos.getY() == target.getY() && blockpos.getZ() == target.getZ();
    }
}
