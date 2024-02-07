package com.prestosaur.lapidary.mixin;

import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static net.minecraft.world.level.block.PointedDripstoneBlock.*;

@Mixin(PointedDripstoneBlock.class)
public abstract class PointedDripstoneMixin
{
    private static final float PETRIFICATION_CHANCE = 1F;//(float) 45 / 256;

    private void maybePetrify(BlockState pState, ServerLevel pLevel, BlockPos pPos, float pRandChance)
    {
        System.out.println("Maybe Petrify");
        if(pRandChance < PETRIFICATION_CHANCE && isStalactiteStartPos(pState, pLevel, pPos))
        {
            var optional = getFluidAboveStalactite(pLevel, pPos, pState);
            if(optional.isEmpty())
                return;
            Fluid fluid = (optional.get()).fluid();
            if(fluid != Fluids.WATER)
                return;
            BlockPos tipPos = findTip(pState, pLevel, pPos, 11, false);
            if(tipPos == null)
                return;
            BlockPos targetPos = findPetrifiableBlockBelowStalactiteTip(pLevel, tipPos, BlockTags.LOGS);
            if(targetPos == null)
                return;
            BlockState targetState = pLevel.getBlockState(targetPos);
            if(targetState == null)
                return;
            BlockState replace = LapidaryBlocks.PETRIFIED_LOG.get().defaultBlockState().setValue(BlockStateProperties.AXIS, targetState.getValue(BlockStateProperties.AXIS));
            pLevel.setBlockAndUpdate(targetPos, replace);
            Block.pushEntitiesUp((optional.get()).sourceState(), replace, pLevel, (optional.get()).pos());
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, (optional.get()).pos(), GameEvent.Context.of(replace));
            pLevel.levelEvent(LevelEvent.DRIPSTONE_DRIP, tipPos, 0);
        }
    }

    @Nullable
    private static BlockPos findPetrifiableBlockBelowStalactiteTip(Level level, BlockPos pos, TagKey<Block> tag)
    {
        Predicate<BlockState> predicate = (s) -> {
            return s.is(tag);
        };
        BiPredicate<BlockPos, BlockState> bipredicate = (p, s) -> {
            return canDripThrough(level, p, s);
        };
        return findBlockVertical(level, pos, Direction.DOWN.getAxisDirection(), bipredicate, predicate, 11).orElse(null);
    }

    @Inject(method="randomTick", at = @At("HEAD"))
    private void randomTickMixin(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom, CallbackInfo ci)
    {
        maybePetrify(pState, pLevel, pPos, pRandom.nextFloat());
    }
}
