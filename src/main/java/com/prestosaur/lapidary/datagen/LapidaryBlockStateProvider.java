package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.Lapidary;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
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
        wallAllWithItem(LapidaryBlocks.STONE_WALL, new ResourceLocation("block/stone"));

        wallAllWithItem(LapidaryBlocks.POLISHED_GRANITE_WALL, new ResourceLocation("block/polished_granite"));
        cubeAllWithItem(LapidaryBlocks.POLISHED_GRANITE_BRICKS);
        triadAllWithItem(LapidaryBlocks.POLISHED_GRANITE_BRICK_STAIRS, LapidaryBlocks.POLISHED_GRANITE_BRICK_SLAB, LapidaryBlocks.POLISHED_GRANITE_BRICK_WALL,
                new ResourceLocation(Lapidary.MODID, "block/polished_granite_bricks"));

        wallAllWithItem(LapidaryBlocks.POLISHED_DIORITE_WALL, new ResourceLocation("block/polished_diorite"));
        cubeAllWithItem(LapidaryBlocks.POLISHED_DIORITE_BRICKS);
        triadAllWithItem(LapidaryBlocks.POLISHED_DIORITE_BRICK_STAIRS, LapidaryBlocks.POLISHED_DIORITE_BRICK_SLAB, LapidaryBlocks.POLISHED_DIORITE_BRICK_WALL,
                new ResourceLocation(Lapidary.MODID, "block/polished_diorite_bricks"));

        wallAllWithItem(LapidaryBlocks.POLISHED_ANDESITE_WALL, new ResourceLocation("block/polished_andesite"));
        cubeAllWithItem(LapidaryBlocks.POLISHED_ANDESITE_BRICKS);
        triadAllWithItem(LapidaryBlocks.POLISHED_ANDESITE_BRICK_STAIRS, LapidaryBlocks.POLISHED_ANDESITE_BRICK_SLAB, LapidaryBlocks.POLISHED_ANDESITE_BRICK_WALL,
                new ResourceLocation(Lapidary.MODID, "block/polished_andesite_bricks"));

        cubeAllWithItem(LapidaryBlocks.CRACKED_BRICKS);

        cubeMirrorWithItem(LapidaryBlocks.MOSSY_MUD_BRICKS.get());
        triadAllWithItem(LapidaryBlocks.MOSSY_MUD_BRICK_STAIRS, LapidaryBlocks.MOSSY_MUD_BRICK_SLAB, LapidaryBlocks.MOSSY_MUD_BRICK_WALL,
                new ResourceLocation(Lapidary.MODID, "block/mossy_mud_bricks"));
        cubeMirrorWithItem(LapidaryBlocks.CRACKED_MUD_BRICKS.get());

        triadAllWithItem(LapidaryBlocks.NETHERRACK_STAIRS, LapidaryBlocks.NETHERRACK_SLAB, LapidaryBlocks.NETHERRACK_WALL,
                new ResourceLocation("block/netherrack"));

        triadAllWithItem(LapidaryBlocks.END_STONE_STAIRS, LapidaryBlocks.END_STONE_SLAB, LapidaryBlocks.END_STONE_WALL,
                new ResourceLocation("block/end_stone"));
        cubeAllWithItem(LapidaryBlocks.CRACKED_END_STONE_BRICKS);

        wallAllWithItem(LapidaryBlocks.PURPUR_WALL, new ResourceLocation("block/purpur_block"));
        cubeAllWithItem(LapidaryBlocks.CRACKED_PURPUR_BLOCK);
    }

    // Creates a simple block and item with the same texture on each side.
    private void cubeAllWithItem(RegistryObject<Block> block)
    {
        Block b = block.get();
        simpleBlockWithItem(b, cubeAll(b));
    }

    // Creates an item from an existing block.
    private <T extends Block> void simpleItemFromBlock(RegistryObject<T> block)
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
        simpleItemFromBlock(stair);
    }

    // Creates a slab with an item where all sides are the same.
    private void slabAllWithItem(RegistryObject<SlabBlock> slab, ResourceLocation texture)
    {
        // Do Block Model.
        slabBlock(slab.get(), texture, texture);
        // Do Item Model.
        simpleItemFromBlock(slab);
    }

    // Creates a wall with an item where all sides are the same.
    private void wallAllWithItem(RegistryObject<WallBlock> wall, ResourceLocation texture)
    {
        // Do Block Model.
        wallBlock(wall.get(), texture);
        // Do Item Model.
        itemModels().wallInventory(wall.getId().getPath(), texture);
    }

    private void triadAllWithItem(RegistryObject<StairBlock> stair, RegistryObject<SlabBlock> slab, RegistryObject<WallBlock> wall, ResourceLocation texture)
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

