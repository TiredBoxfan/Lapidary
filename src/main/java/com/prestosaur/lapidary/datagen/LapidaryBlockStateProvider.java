package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.Lapidary;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
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
        stairAllWithItem(LapidaryBlocks.NETHERRACK_STAIRS, new ResourceLocation("block/netherrack"));
        slabAllWithItem(LapidaryBlocks.NETHERRACK_SLAB, new ResourceLocation("block/netherrack"));

        stairAllWithItem(LapidaryBlocks.END_STONE_STAIRS, new ResourceLocation("block/end_stone"));
        slabAllWithItem(LapidaryBlocks.END_STONE_SLAB, new ResourceLocation("block/end_stone"));
        cubeAllWithItem(LapidaryBlocks.CRACKED_END_STONE_BRICKS);
    }

    // Creates a simple block and item with the same texture on each side.
    private void cubeAllWithItem(RegistryObject<Block> block)
    {
        Block b = block.get();
        simpleBlockWithItem(b, cubeAll(b));
    }

    // Creates an item from an existing block.
    private <T extends Block> void simpleBlockItem(RegistryObject<T> block)
    {
        ResourceLocation id = block.getId();
        itemModels().getBuilder(id.getPath()).parent(models().getExistingFile(id));
    }

    // Creates a stair with an item where all sides are the same.
    private void stairAllWithItem(RegistryObject<StairBlock> stair, ResourceLocation texture)
    {
        // Do Block Model.
        stairsBlock(stair.get(), texture);
        // Do Item Model.
        simpleBlockItem(stair);
    }

    // Creates a slab with an item where all sides are the same.
    private void slabAllWithItem(RegistryObject<SlabBlock> slab, ResourceLocation texture)
    {
        // Do Block Model.
        slabBlock(slab.get(), texture, texture);
        // Do Item Model.
        simpleBlockItem(slab);
    }
}

