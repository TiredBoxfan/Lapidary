package com.prestosaur.lapidary.mixin;

import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShovelItem.class)
public abstract class ShovelItemMixin extends Item {
    public ShovelItemMixin(Properties pProperties) {
        super(pProperties);
    }

    // How far the secondary uses of the axe can be used.
    // Referenced from BrushItem.
    //@Unique
    //protected static final double MAX_SCULPT_DISTANCE = Math.sqrt(ServerGamePacketListenerImpl.MAX_INTERACTION_DISTANCE) - 1.0D;

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack pStack) {
        return UseAnim.BRUSH;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack pStack) {
        return 120; // TODO: Determine ideal value.
    }

    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    public void useOnMixin(UseOnContext pContext, CallbackInfoReturnable<InteractionResult> info) {
        Player player = pContext.getPlayer();
        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, pContext.getPlayer()) > 0 || (pContext.getHand() == InteractionHand.MAIN_HAND && !player.getItemInHand(InteractionHand.OFF_HAND).isEmpty()))
            info.setReturnValue(InteractionResult.PASS);
    }
}
