package com.prestosaur.lapidary.sculpt;

import com.google.common.collect.ImmutableMap;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;

public class LapidarySculpt {
    public static final Map<Block, Block> CRACKABLES = (new ImmutableMap.Builder<Block, Block>())
            .put(Blocks.STONE, Blocks.COBBLESTONE)
            .put(Blocks.STONE_BRICKS, Blocks.CRACKED_STONE_BRICKS)
            .put(Blocks.DEEPSLATE, Blocks.COBBLED_DEEPSLATE)
            .put(Blocks.DEEPSLATE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS)
            .put(Blocks.DEEPSLATE_TILES, Blocks.CRACKED_DEEPSLATE_TILES)
            .put(Blocks.BRICKS, LapidaryBlocks.CRACKED_BRICKS.get())
            .put(Blocks.MUD_BRICKS, LapidaryBlocks.CRACKED_MUD_BRICKS.get())
            .put(Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS)
            .put(Blocks.NETHER_BRICKS, Blocks.CRACKED_NETHER_BRICKS)
            .put(Blocks.END_STONE_BRICKS, LapidaryBlocks.CRACKED_END_STONE_BRICKS.get())
            .put(Blocks.PURPUR_BLOCK, LapidaryBlocks.CRACKED_PURPUR_BLOCK.get())
            .build();
}
