package com.prestosaur.lapidary.mixin;

import com.prestosaur.lapidary.enchantment.LapidaryEnchantments;
import com.prestosaur.lapidary.enchantment.SculptEnchantment;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
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

    // TODO: What is the correct way?
    @Nullable
    @Unique
    BlockPos lapidary$lastTargetPos;

    public ShovelItemMixin(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack pStack) {
        return UseAnim.BRUSH;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack pStack) {
        return 32; // TODO: Determine ideal value.
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

        System.out.println("Called ShovelItemMixin.useOnMixin - Secondary Use: " + player.isUsingItem());

        if (EnchantmentHelper.getEnchantmentLevel(LapidaryEnchantments.SCULPT.get(), player) <= 0 && !player.isUsingItem()) {

            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player) > 0 || (pContext.getHand() == InteractionHand.MAIN_HAND && !player.getItemInHand(InteractionHand.OFF_HAND).isEmpty())) {
                info.setReturnValue(InteractionResult.PASS);
                return;
            }

            System.out.println("Not cancelled.");

            lapidary$lastTargetPos = blockpos;
            player.startUsingItem(pContext.getHand());

            info.setReturnValue(InteractionResult.CONSUME);
        }
    }

    @Override
    public void onUseTick(@NotNull Level pLevel, @NotNull LivingEntity pLivingEntity, @NotNull ItemStack pStack, int pRemainingUseDuration) {
        if (pLivingEntity instanceof Player player && pRemainingUseDuration >= 0) {
            // Get the targeted block information, if any.
            HitResult hitResult = lapidary$calculateHitResult(pLivingEntity);
            if (hitResult instanceof BlockHitResult blockHitResult) {
                // Ensure the same block is being targeted.
                BlockPos blockpos = blockHitResult.getBlockPos();
                if (blockpos.equals(lapidary$lastTargetPos)) {
                    if (pRemainingUseDuration <= 1)
                        useOn(new UseOnContext(player, player.getUsedItemHand(), blockHitResult));
                } else {
                    pLivingEntity.releaseUsingItem();
                }
            } else {
                pLivingEntity.releaseUsingItem();
            }
        } else {
            pLivingEntity.releaseUsingItem();
        }
    }
}
