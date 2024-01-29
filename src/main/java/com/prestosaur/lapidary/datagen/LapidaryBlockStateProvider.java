package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.Lapidary;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class LapidaryBlockStateProvider extends BlockStateProvider
{
    public LapidaryBlockStateProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper)
    {
        super(packOutput, Lapidary.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        cubeAllWithItem(LapidaryBlocks.CRACKED_END_STONE_BRICKS);
    }

    // Creates a simple block and item with the same texture on each side.
    private void cubeAllWithItem(RegistryObject<Block> blockRegistryObject)
    {
        Block block = blockRegistryObject.get();
        simpleBlockWithItem(block, cubeAll(block));
    }
}
