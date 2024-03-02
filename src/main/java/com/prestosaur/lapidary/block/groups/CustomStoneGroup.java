package com.prestosaur.lapidary.block.groups;

import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class CustomStoneGroup {
    public final RegistryObject<Block> BASE_BLOCK;
    public final BlockTriad BASE_TRIAD;
    public final RegistryObject<Block> POLISHED_BLOCK;
    public final BlockTriad POLISHED_TRIAD;
    public final RegistryObject<Block> BRICK_BLOCK;
    public final BlockTriad BRICK_TRIAD;

    public CustomStoneGroup(String baseName, Supplier<Block> baseSupplier) {
        BASE_BLOCK = LapidaryBlocks.registerBlock(baseName, baseSupplier);
        BASE_TRIAD = new BlockTriad(BASE_BLOCK, baseName);
        POLISHED_BLOCK = LapidaryBlocks.registerBlock("polished_" + baseName,
                () -> new Block(BlockBehaviour.Properties.copy(BASE_BLOCK.get())));
        POLISHED_TRIAD = new BlockTriad(POLISHED_BLOCK, "polished_" + baseName);
        BRICK_BLOCK = LapidaryBlocks.registerBlock("polished_" + baseName + "_bricks",
                () -> new Block(BlockBehaviour.Properties.copy(POLISHED_BLOCK.get())));
        BRICK_TRIAD = new BlockTriad(BRICK_BLOCK, "polished_" + baseName + "_brick");
    }
}
