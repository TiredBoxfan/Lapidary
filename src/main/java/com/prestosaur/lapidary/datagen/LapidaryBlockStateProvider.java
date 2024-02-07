package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.Lapidary;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.*;
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
        logBlock(LapidaryBlocks.PETRIFIED_LOG.get());
        simpleItemFromBlock(LapidaryBlocks.PETRIFIED_LOG.get());
        cubeAllWithItem(LapidaryBlocks.PETRIFIED_PLANKS.get());
        stairAllWithItem(LapidaryBlocks.PETRIFIED_STAIRS.get(), new ResourceLocation(Lapidary.MODID, "block/petrified_planks"));

        wallAllWithItem(LapidaryBlocks.STONE_WALL.get(), new ResourceLocation("block/stone"));

        wallAllWithItem(LapidaryBlocks.POLISHED_GRANITE_WALL.get(), new ResourceLocation("block/polished_granite"));
        cubeAllWithItem(LapidaryBlocks.POLISHED_GRANITE_BRICKS.get());
        triadAllWithItem(LapidaryBlocks.POLISHED_GRANITE_BRICK_STAIRS.get(),
                LapidaryBlocks.POLISHED_GRANITE_BRICK_SLAB.get(),
                LapidaryBlocks.POLISHED_GRANITE_BRICK_WALL.get(),
                new ResourceLocation(Lapidary.MODID, "block/polished_granite_bricks"));

        wallAllWithItem(LapidaryBlocks.POLISHED_DIORITE_WALL.get(), new ResourceLocation("block/polished_diorite"));
        cubeAllWithItem(LapidaryBlocks.POLISHED_DIORITE_BRICKS.get());
        triadAllWithItem(LapidaryBlocks.POLISHED_DIORITE_BRICK_STAIRS.get(),
                LapidaryBlocks.POLISHED_DIORITE_BRICK_SLAB.get(),
                LapidaryBlocks.POLISHED_DIORITE_BRICK_WALL.get(),
                new ResourceLocation(Lapidary.MODID, "block/polished_diorite_bricks"));

        wallAllWithItem(LapidaryBlocks.POLISHED_ANDESITE_WALL.get(), new ResourceLocation("block/polished_andesite"));
        cubeAllWithItem(LapidaryBlocks.POLISHED_ANDESITE_BRICKS.get());
        triadAllWithItem(LapidaryBlocks.POLISHED_ANDESITE_BRICK_STAIRS.get(),
                LapidaryBlocks.POLISHED_ANDESITE_BRICK_SLAB.get(),
                LapidaryBlocks.POLISHED_ANDESITE_BRICK_WALL.get(),
                new ResourceLocation(Lapidary.MODID, "block/polished_andesite_bricks"));

        cubeAllWithItem(LapidaryBlocks.CRACKED_BRICKS.get());

        cubeMirrorWithItem(LapidaryBlocks.MOSSY_MUD_BRICKS.get());
        triadAllWithItem(LapidaryBlocks.MOSSY_MUD_BRICK_STAIRS.get(),
                LapidaryBlocks.MOSSY_MUD_BRICK_SLAB.get(),
                LapidaryBlocks.MOSSY_MUD_BRICK_WALL.get(),
                new ResourceLocation(Lapidary.MODID, "block/mossy_mud_bricks"));
        cubeMirrorWithItem(LapidaryBlocks.CRACKED_MUD_BRICKS.get());

        triadAllWithItem(LapidaryBlocks.NETHERRACK_STAIRS.get(),
                LapidaryBlocks.NETHERRACK_SLAB.get(),
                LapidaryBlocks.NETHERRACK_WALL.get(),
                new ResourceLocation("block/netherrack"));

        triadAllWithItem(LapidaryBlocks.END_STONE_STAIRS.get(),
                LapidaryBlocks.END_STONE_SLAB.get(),
                LapidaryBlocks.END_STONE_WALL.get(),
                new ResourceLocation("block/end_stone"));
        cubeAllWithItem(LapidaryBlocks.CRACKED_END_STONE_BRICKS.get());

        wallAllWithItem(LapidaryBlocks.PURPUR_WALL.get(), new ResourceLocation("block/purpur_block"));
        cubeAllWithItem(LapidaryBlocks.CRACKED_PURPUR_BLOCK.get());
    }

    // Creates a simple block and item with the same texture on each side.
    private void cubeAllWithItem(Block block)
    {
        simpleBlockWithItem(block, cubeAll(block));
    }

    // Creates an item from an existing block.
    private <T extends Block> void simpleItemFromBlock(Block block)
    {
        ResourceLocation id = ForgeRegistries.BLOCKS.getKey(block);
        itemModels().getBuilder(id.getPath()).parent(models().getExistingFile(id));
    }

    // Creates a stair with an item where all sides are the same.
    private void stairAllWithItem(StairBlock stair, ResourceLocation texture)
    {
        // Do Block Model.
        stairsBlock(stair, texture);
        // Do Item Model.
        simpleItemFromBlock(stair);
    }

    // Creates a slab with an item where all sides are the same.
    private void slabAllWithItem(SlabBlock slab, ResourceLocation texture)
    {
        // Do Block Model.
        slabBlock(slab, texture, texture);
        // Do Item Model.
        simpleItemFromBlock(slab);
    }

    // Creates a wall with an item where all sides are the same.
    private void wallAllWithItem(WallBlock wall, ResourceLocation texture)
    {
        // Do Block Model.
        wallBlock(wall, texture);
        // Do Item Model.
        itemModels().wallInventory(ForgeRegistries.BLOCKS.getKey(wall).getPath(), texture);
    }

    private void triadAllWithItem(StairBlock stair, SlabBlock slab, WallBlock wall, ResourceLocation texture)
    {
        stairAllWithItem(stair, texture);
        slabAllWithItem(slab, texture);
        wallAllWithItem(wall, texture);
    }

    private void cubeMirrorWithItem(Block block)
    {
        String name = ForgeRegistries.BLOCKS.getKey(block).getPath();
        ModelFile allModel = cubeAll(block);
        ModelFile mirrorModel = models().singleTexture(name + "_north_west_mirrored", new ResourceLocation(ModelProvider.BLOCK_FOLDER + "/cube_north_west_mirrored_all"), "all", blockTexture(block));
        simpleBlock(block, mirrorModel);
        simpleBlockItem(block, allModel);
    }
}

