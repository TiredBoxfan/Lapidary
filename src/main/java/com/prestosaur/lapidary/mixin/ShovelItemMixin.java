package com.prestosaur.lapidary.mixin;

import com.prestosaur.lapidary.Config;
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
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraftforge.common.ForgeMod.BLOCK_REACH;

@Mixin(ShovelItem.class)
public abstract class ShovelItemMixin extends Item {

    // TODO: What is the correct way? ItemProperties?
    // TODO: Check blockstate instead?
    @Nullable
    @Unique
    BlockPos lapidary$lastTargetPos;
    //BlockState lapidary$lastTargetBlockState;

    /*@Unique
    int lapidary$targetX = 0;
    @Unique
    int lapidary$targetY = 0;
    @Unique
    int lapidary$targetZ = 0;*/

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

        if (player == null) {
            info.setReturnValue(InteractionResult.PASS);
            return;
        }

        //System.out.println("New Use: " + player.isUsingItem() + " " + lapidary$isTargetedBlock(blockpos));

        if (pContext.getHand() == InteractionHand.MAIN_HAND && !player.getItemInHand(InteractionHand.OFF_HAND).isEmpty()) {
            info.setReturnValue(InteractionResult.PASS);
            System.out.println("Hand Full");
            return;
        }

        boolean hasSculptEnchant = EnchantmentHelper.getEnchantmentLevel(LapidaryEnchantments.SCULPT.get(), player) >= 1;
        boolean sameBlock = lapidary$isTargetedBlock(blockpos);
        boolean usingTool = player.isUsingItem();
        boolean useTick = player.getUseItemRemainingTicks() == 1;

        System.out.println(player.getUseItemRemainingTicks() + "!");

        if (!hasSculptEnchant && (!sameBlock || !usingTool)) {
        //if (EnchantmentHelper.getEnchantmentLevel(LapidaryEnchantments.SCULPT.get(), player) <= 0 && (player.getUseItemRemainingTicks() >= 1 || !player.isUsingItem()) || !lapidary$isTargetedBlock(blockpos)) {
            //player.releaseUsingItem();
            System.out.println("Start Use.");
            player.stopUsingItem();

            if ((EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player) >= 1 && Config.disableSilkTouchSculpt)) {
                info.setReturnValue(InteractionResult.PASS);
                return;
            }

            System.out.println("Not cancelled.");

            lapidary$lastTargetPos = blockpos;
            //lapidary$lastTargetBlockState = pContext.getLevel().getBlockState(blockpos);
            //lapidary$lastTargetBlockState = pContext.getLevel().getBlockState(blockpos);
            //lapidary$targetX = blockpos.getX();
            //lapidary$targetY = blockpos.getY();
            //lapidary$targetZ = blockpos.getZ();
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
                //if (blockpos.equals(lapidary$lastTargetPos)) {
                //if (pLevel.getBlockState(blockpos).equals(lapidary$lastTargetBlockState)) {
                if (lapidary$isTargetedBlock(blockpos)) {
                    if (pRemainingUseDuration == 1) {
                        useOn(new UseOnContext(player, player.getUsedItemHand(), blockHitResult));
                        System.out.println("Complete.");
                        pLivingEntity.releaseUsingItem();
                    }
                } else {
                    pLivingEntity.releaseUsingItem();
                    System.out.println("Stop: Different Block");
                }
            } else {
                pLivingEntity.releaseUsingItem();
                System.out.println("Stop: No Block");
            }
        } else {
            pLivingEntity.releaseUsingItem();
            System.out.println("Stop: Not Player");
        }
    }

    @Unique
    protected boolean lapidary$isTargetedBlock(BlockPos blockpos) {
        //return blockpos.getX() == lapidary$targetX && blockpos.getY() == lapidary$targetY && blockpos.getZ() == lapidary$targetZ;
        return blockpos != null && lapidary$lastTargetPos != null && blockpos.getX() == lapidary$lastTargetPos.getX() && blockpos.getY() == lapidary$lastTargetPos.getY() && blockpos.getZ() == lapidary$lastTargetPos.getZ();
    }
}
