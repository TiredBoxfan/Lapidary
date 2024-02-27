package com.prestosaur.lapidary.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class DestabilizerBlock extends DiodeBlock implements EntityBlock {
    public static final EnumProperty<DestabilizerMode> MODE = LapidaryBlockStateProperties.MODE_DESTABILIZER;

    protected DestabilizerBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, Boolean.valueOf(false)).setValue(MODE, DestabilizerMode.BOUNDS));
    }

    @Override
    protected int getDelay(BlockState pState) {
        return 2;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!player.getAbilities().mayBuild) {
            return InteractionResult.PASS;
        } else {
            level.setBlock(pos, state.cycle(MODE), 3);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    }

    @Override
    protected int getOutputSignal(BlockGetter level, BlockPos pos, BlockState state) {
        return level.getBlockEntity(pos) instanceof DestabilizerBlockEntity entity ? entity.getOutputSignal() : 0;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rng) {
        Boolean prev = state.getValue(POWERED);
        super.tick(state, level, pos, rng);
        BlockState newState = level.getBlockState(pos);
        Boolean curr = newState.getValue(POWERED);

        System.out.println("TICK" + prev + curr);

        if (level.getBlockEntity(pos) instanceof DestabilizerBlockEntity entity) {
            if (!curr) {
                if (entity.getOutputSignal() != 0) {
                    entity.setOutputSignal(0);
                    updateNeighborsInFront(level, pos, newState);
                }
            } else if (!prev) {
                Direction facing = newState.getValue(FACING);
                int left = getDirectionInput(level, pos, facing.getCounterClockWise());
                int right = getDirectionInput(level, pos, facing.getClockWise());
                int i;
                if (newState.getValue(MODE) == DestabilizerMode.BOUNDS) {
                    i = rng.nextIntBetweenInclusive(Math.min(left, right), Math.max(left, right));
                } else {
                    i = rng.nextBoolean() ? left : right;
                }
                entity.setOutputSignal(i);
                updateNeighborsInFront(level, pos, newState);
                System.out.println(i);
            }
        }
    }

    private int getDirectionInput(Level level, BlockPos pos, Direction dir) {
        return level.getControlInputSignal(pos.relative(dir), dir, this.sideInputDiodesOnly());
    }

    /*@Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rng) {

        Boolean prev = state.getValue(POWERED);
        super.tick(state, level, pos, rng);
        Boolean curr = level.getBlockState(pos).getValue(POWERED);

        System.out.println("TICK" + prev + curr);

        if (!curr) {
            if (state.getValue(VALUE) != 0)
                level.setBlock(pos, state.setValue(VALUE, 0), 2);
        } else if (!prev) {
            System.out.println("HERE");
            level.setBlock(pos, state.setValue(VALUE, rng.nextInt(0,15)), 2);
        }
    }*/

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DestabilizerBlockEntity(pos, state);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, MODE, POWERED);
    }
}
