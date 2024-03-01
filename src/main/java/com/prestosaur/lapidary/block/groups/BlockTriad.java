package com.prestosaur.lapidary.block.groups;

import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockTriad {
    public final RegistryObject<StairBlock> STAIR;
    public final RegistryObject<SlabBlock> SLAB;
    public final RegistryObject<WallBlock> WALL;

    public BlockTriad(Supplier<Block> blockSupplier, String baseName) {
        STAIR = LapidaryBlocks.registerBlock(baseName + "_stairs",
                () -> {
                    Block block = blockSupplier.get();
                    return new StairBlock(block::defaultBlockState,
                            BlockBehaviour.Properties.copy(block));
                });

        SLAB = LapidaryBlocks.registerBlock(baseName + "_slab",
                () -> new SlabBlock(BlockBehaviour.Properties.copy(blockSupplier.get())));

        WALL = LapidaryBlocks.registerBlock(baseName + "_wall",
                () -> new WallBlock(BlockBehaviour.Properties.copy(blockSupplier.get())));
    }
}