package com.prestosaur.lapidary.mixin;

import com.prestosaur.lapidary.block.LapidaryBlocks;
import com.prestosaur.lapidary.tag.LapidaryTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static net.minecraft.world.level.block.PointedDripstoneBlock.*;

@Mixin(PointedDripstoneBlock.class)
public abstract class PointedDripstoneMixin {
    private static final float PETRIFICATION_CHANCE = (float) 15 / 256;

    private void maybePetrify(BlockState pState, ServerLevel pLevel, BlockPos pPos, float pRandChance) {
        if (pRandChance < PETRIFICATION_CHANCE && isStalactiteStartPos(pState, pLevel, pPos)) {
            var optional = getFluidAboveStalactite(pLevel, pPos, pState);
            if (optional.isEmpty())
                return;
            Fluid fluid = (optional.get()).fluid();
            if (fluid != Fluids.WATER)
                return;
            BlockPos tipPos = findTip(pState, pLevel, pPos, 11, false);
            if (tipPos == null)
                return;
            BlockPos targetPos = findPetrifiableBlockBelowStalactiteTip(pLevel, tipPos);
            if (targetPos == null)
                return;
            BlockState targetState = pLevel.getBlockState(targetPos);
            if (targetState == null)
                return;

            BlockState replaceState;
            try {
                if (targetState.is(LapidaryTags.Blocks.BARK_BLOCKS)) {
                    replaceState = LapidaryBlocks.PETRIFIED_WOOD.get().defaultBlockState()
                            .setValue(BlockStateProperties.AXIS, targetState.getValue(BlockStateProperties.AXIS));
                } else if (targetState.is(BlockTags.LOGS)) {
                    replaceState = LapidaryBlocks.PETRIFIED_LOG.get().defaultBlockState()
                            .setValue(BlockStateProperties.AXIS, targetState.getValue(BlockStateProperties.AXIS));
                } else if (targetState.is(BlockTags.STAIRS)) {
                    replaceState = LapidaryBlocks.PETRIFIED_STAIRS.get().defaultBlockState()
                            .setValue(BlockStateProperties.HORIZONTAL_FACING, targetState.getValue(BlockStateProperties.HORIZONTAL_FACING))
                            .setValue(BlockStateProperties.HALF, targetState.getValue(BlockStateProperties.HALF))
                            .setValue(BlockStateProperties.STAIRS_SHAPE, targetState.getValue(BlockStateProperties.STAIRS_SHAPE))
                            .setValue(BlockStateProperties.WATERLOGGED, targetState.getValue(BlockStateProperties.WATERLOGGED));
                } else if (targetState.is(BlockTags.SLABS)) {
                    replaceState = Blocks.PETRIFIED_OAK_SLAB.defaultBlockState()
                            .setValue(BlockStateProperties.SLAB_TYPE, targetState.getValue(BlockStateProperties.SLAB_TYPE))
                            .setValue(BlockStateProperties.WATERLOGGED, targetState.getValue(BlockStateProperties.WATERLOGGED));
                } else {
                    replaceState = LapidaryBlocks.PETRIFIED_PLANKS.get().defaultBlockState();
                }
            } catch (Exception e) {
                return;
            }

            pLevel.setBlockAndUpdate(targetPos, replaceState);
            Block.pushEntitiesUp((optional.get()).sourceState(), replaceState, pLevel, (optional.get()).pos());
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, (optional.get()).pos(), GameEvent.Context.of(replaceState));
            pLevel.levelEvent(LevelEvent.DRIPSTONE_DRIP, tipPos, 0);
        }
    }

    @Nullable
    private static BlockPos findPetrifiableBlockBelowStalactiteTip(Level level, BlockPos pos) {
        Predicate<BlockState> predicate = (s) -> {
            return s.is(LapidaryTags.Blocks.PETRIFIABLE);
        };
        BiPredicate<BlockPos, BlockState> bipredicate = (p, s) -> {
            return canDripThrough(level, p, s);
        };
        return findBlockVertical(level, pos, Direction.DOWN.getAxisDirection(), bipredicate, predicate, 11).orElse(null);
    }

    @Inject(method = "randomTick", at = @At("HEAD"))
    private void randomTickMixin(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom, CallbackInfo ci) {
        maybePetrify(pState, pLevel, pPos, pRandom.nextFloat());
    }
}
