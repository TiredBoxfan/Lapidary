package com.prestosaur.lapidary.mixin;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

import javax.annotation.Nullable;
import java.util.Optional;

@Mixin(AxeItem.class)
public abstract class AxeItemMixinFancy extends Item {
    // How far the secondary uses of the axe can be used.
    // Referenced from BrushItem.
    @Unique
    private static final double MAX_SECONDARY_DISTANCE = Math.sqrt(ServerGamePacketListenerImpl.MAX_INTERACTION_DISTANCE) - 1.0D;

    // TODO: Is there a better way?
    @Nullable
    @Unique
    BlockPos lapidary$lastTargetPos;

    public AxeItemMixinFancy(Properties pProperties) {
        super(pProperties);
    }

    /**
     * @author Prestosaur
     * @reason Allowing Stripping Animation.
     */
    @Overwrite
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        Player player = pContext.getPlayer();
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);

        // To use secondary uses, axe must be in main hand and off-hand must be empty.
        ItemStack offhand = player.getItemInHand(InteractionHand.OFF_HAND);
        if (pContext.getHand() != InteractionHand.MAIN_HAND || !offhand.isEmpty())
            return InteractionResult.PASS;

        // Set use.
        if (player != null && blockstate != null) {
            lapidary$lastTargetPos = blockpos;
            player.startUsingItem(pContext.getHand());
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public void onUseTick(@NotNull Level pLevel, @NotNull LivingEntity pLivingEntity, @NotNull ItemStack pStack, int pRemainingUseDuration) {
        if (pLivingEntity instanceof Player player && pRemainingUseDuration >= 0) {
            // Get the targeted block information, if any.
            HitResult hitResult = lapidary$calculateHitResult(pLivingEntity);
            if (hitResult instanceof BlockHitResult blockHitResult) {
                BlockPos blockpos = blockHitResult.getBlockPos();

                // Determine if the targeted block has changed.
                if (!blockpos.equals(lapidary$lastTargetPos)) {
                    // Target block has changed; stop using tool.
                    pLivingEntity.releaseUsingItem();
                    return;
                }

                if (pRemainingUseDuration == 1)
                    lapidary$doSecondaryAction(new UseOnContext(player, player.getUsedItemHand(), blockHitResult));

            } else {
                // Target was blocked; stop using the item.
                pLivingEntity.releaseUsingItem();
            }
        } else {
            pLivingEntity.releaseUsingItem();
        }
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack pStack) {
        return UseAnim.BRUSH; // Reuse the brush animation.
    }

    @Override
    public int getUseDuration(@NotNull ItemStack pStack) {
        return 32; // TODO: Determine ideal value.
    }

    // Used AxeItem#useOn method as a reference.
    @Unique
    private void lapidary$doSecondaryAction(UseOnContext pContext) {
        // Gather information.
        Player player = pContext.getPlayer();
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);

        // Instantiate conditions.
        Optional<BlockState> optStrip = Optional.ofNullable(
                blockstate.getToolModifiedState(pContext, ToolActions.AXE_STRIP, false));
        Optional<BlockState> optScrape = Optional.ofNullable(
                blockstate.getToolModifiedState(pContext, ToolActions.AXE_SCRAPE, false));
        Optional<BlockState> optWaxOff = Optional.ofNullable(
                blockstate.getToolModifiedState(pContext, ToolActions.AXE_WAX_OFF, false));

        // Do actions
        if (optStrip.isPresent()) {
            level.playSound(player, blockpos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            lapidary$doSecondaryActionComplete(pContext, optStrip.get());
        } else if (optScrape.isPresent()) {
            level.playSound(player, blockpos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.levelEvent(player, 3005, blockpos, 0);
            lapidary$doSecondaryActionComplete(pContext, optStrip.get());
        } else if (optWaxOff.isPresent()) {
            level.playSound(player, blockpos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.levelEvent(player, 3004, blockpos, 0);
            lapidary$doSecondaryActionComplete(pContext, optStrip.get());
        }
    }

    // Should only be called when an optional in doSecondaryAction() is present.
    @Unique
    private void lapidary$doSecondaryActionComplete(UseOnContext pContext, BlockState blockstate) {
        // Re-collect information.
        Player player = pContext.getPlayer();
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        ItemStack itemstack = pContext.getItemInHand();

        if (player instanceof ServerPlayer serverPlayer)
            CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, blockpos, itemstack);

        level.setBlock(blockpos, blockstate, 11);
        level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, blockstate));
        if (player != null)
            itemstack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(pContext.getHand()));
    }

    // Referenced from BrushItem.
    @Unique
    private HitResult lapidary$calculateHitResult(LivingEntity pEntity) {
        return ProjectileUtil.getHitResultOnViewVector(pEntity, (p) -> !p.isSpectator() && p.isPickable(), MAX_SECONDARY_DISTANCE);
    }
}
