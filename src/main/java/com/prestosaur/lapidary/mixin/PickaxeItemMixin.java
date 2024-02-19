package com.prestosaur.lapidary.mixin;

import com.google.common.collect.ImmutableMap;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Map;
import java.util.Optional;

import static com.prestosaur.lapidary.sculpt.LapidarySculpt.CRACKABLES;

@Mixin(PickaxeItem.class)
public class PickaxeItemMixin extends SculptMixin {


    public PickaxeItemMixin(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext pContext) {
        // Do start tests.
        var result = lapidary$useOnSculpt(pContext);
        if (result != null)
            return result;
        //return InteractionResult.PASS;
        Player player = pContext.getPlayer();
        ItemStack itemstack = pContext.getItemInHand();
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState originalState = level.getBlockState(blockpos);
        Block replacement = CRACKABLES.get(originalState.getBlock());
        Optional<BlockState> test = Optional.ofNullable(replacement != null ? replacement.defaultBlockState() : null);
        if (test.isPresent()) {
            level.playSound(player, blockpos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);

            if (player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, blockpos, itemstack);
            }

            level.setBlock(blockpos, test.get(), 11);
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, test.get()));

            if (player != null) {
                itemstack.hurtAndBreak(1, player, (p) -> {
                    p.broadcastBreakEvent(pContext.getHand());
                });
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }
}
