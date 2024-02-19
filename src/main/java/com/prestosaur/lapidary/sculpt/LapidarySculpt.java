package com.prestosaur.lapidary.sculpt;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;

public class LapidarySculpt {
    public static final Map<Block, Block> CRACKABLES = (new ImmutableMap.Builder<Block, Block>())
            .put(Blocks.STONE, Blocks.COBBLESTONE)
            .build();
}
