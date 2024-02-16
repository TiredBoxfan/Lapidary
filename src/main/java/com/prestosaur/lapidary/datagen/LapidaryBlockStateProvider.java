package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.Lapidary;
import com.prestosaur.lapidary.block.BlockTriad;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class LapidaryBlockStateProvider extends BlockStateProvider {
    public LapidaryBlockStateProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, Lapidary.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        logBlock(LapidaryBlocks.PETRIFIED_LOG.get());
        simpleItemFromBlock(LapidaryBlocks.PETRIFIED_LOG.get());
        axisBlock(LapidaryBlocks.PETRIFIED_WOOD.get(),
                new ResourceLocation(Lapidary.MODID, "block/petrified_log"),
                new ResourceLocation(Lapidary.MODID, "block/petrified_log"));
        simpleItemFromBlock(LapidaryBlocks.PETRIFIED_WOOD.get());
        cubeAllWithItem(LapidaryBlocks.PETRIFIED_PLANKS.get());
        stairAllWithItem(LapidaryBlocks.PETRIFIED_STAIRS.get(), new ResourceLocation(Lapidary.MODID, "block/petrified_planks"));

        wallAllWithItem(LapidaryBlocks.STONE_WALL.get(), new ResourceLocation("block/stone"));

        wallAllWithItem(LapidaryBlocks.POLISHED_GRANITE_WALL.get(), new ResourceLocation("block/polished_granite"));
        cubeAllWithItem(LapidaryBlocks.POLISHED_GRANITE_BRICKS.get());
        triadAllWithItem(LapidaryBlocks.POLISHED_GRANITE_BRICK_TRIAD,
                new ResourceLocation(Lapidary.MODID, "block/polished_granite_bricks"));

        wallAllWithItem(LapidaryBlocks.POLISHED_DIORITE_WALL.get(), new ResourceLocation("block/polished_diorite"));
        cubeAllWithItem(LapidaryBlocks.POLISHED_DIORITE_BRICKS.get());
        triadAllWithItem(LapidaryBlocks.POLISHED_DIORITE_BRICK_TRIAD,
                new ResourceLocation(Lapidary.MODID, "block/polished_diorite_bricks"));

        wallAllWithItem(LapidaryBlocks.POLISHED_ANDESITE_WALL.get(), new ResourceLocation("block/polished_andesite"));
        cubeAllWithItem(LapidaryBlocks.POLISHED_ANDESITE_BRICKS.get());
        triadAllWithItem(LapidaryBlocks.POLISHED_ANDESITE_BRICK_TRIAD,
                new ResourceLocation(Lapidary.MODID, "block/polished_andesite_bricks"));

        cubeAllWithItem(LapidaryBlocks.PERIDOTITE.get());
        triadAllWithItem(LapidaryBlocks.PERIDOTITE_TRIAD, blockTexture(LapidaryBlocks.PERIDOTITE.get()));
        cubeAllWithItem(LapidaryBlocks.POLISHED_PERIDOTITE.get());
        triadAllWithItem(LapidaryBlocks.POLISHED_PERIDOTITE_TRIAD, blockTexture(LapidaryBlocks.POLISHED_PERIDOTITE.get()));

        cubeAllWithItem(LapidaryBlocks.CRACKED_BRICKS.get());

        cubeMirrorWithItem(LapidaryBlocks.MOSSY_MUD_BRICKS.get());
        triadAllWithItem(LapidaryBlocks.MOSSY_MUD_BRICK_TRIAD,
                new ResourceLocation(Lapidary.MODID, "block/mossy_mud_bricks"));
        cubeMirrorWithItem(LapidaryBlocks.CRACKED_MUD_BRICKS.get());

        triadAllWithItem(LapidaryBlocks.NETHERRACK_TRIAD,
                new ResourceLocation("block/netherrack"));

        triadAllWithItem(LapidaryBlocks.END_STONE_TRIAD,
                new ResourceLocation("block/end_stone"));
        cubeAllWithItem(LapidaryBlocks.CRACKED_END_STONE_BRICKS.get());

        wallAllWithItem(LapidaryBlocks.PURPUR_WALL.get(), new ResourceLocation("block/purpur_block"));
        cubeAllWithItem(LapidaryBlocks.CRACKED_PURPUR_BLOCK.get());

        cubeAllWithItem(LapidaryBlocks.JADE.get());
        cubeAllWithItem(LapidaryBlocks.POLISHED_JADE.get());
    }

    // Creates a simple block and item with the same texture on each side.
    private void cubeAllWithItem(Block block) {
        simpleBlockWithItem(block, cubeAll(block));
    }

    // Creates an item from an existing block.
    private <T extends Block> void simpleItemFromBlock(Block block) {
        ResourceLocation id = ForgeRegistries.BLOCKS.getKey(block);
        itemModels().getBuilder(id.getPath()).parent(models().getExistingFile(id));
    }

    // Creates a stair with an item where all sides are the same.
    private void stairAllWithItem(StairBlock stair, ResourceLocation texture) {
        // Do Block Model.
        stairsBlock(stair, texture);
        // Do Item Model.
        simpleItemFromBlock(stair);
    }

    // Creates a slab with an item where all sides are the same.
    private void slabAllWithItem(SlabBlock slab, ResourceLocation texture) {
        // Do Block Model.
        slabBlock(slab, texture, texture);
        // Do Item Model.
        simpleItemFromBlock(slab);
    }

    // Creates a wall with an item where all sides are the same.
    private void wallAllWithItem(WallBlock wall, ResourceLocation texture) {
        // Do Block Model.
        wallBlock(wall, texture);
        // Do Item Model.
        itemModels().wallInventory(ForgeRegistries.BLOCKS.getKey(wall).getPath(), texture);
    }

    private void triadAllWithItem(StairBlock stair, SlabBlock slab, WallBlock wall, ResourceLocation texture) {
        stairAllWithItem(stair, texture);
        slabAllWithItem(slab, texture);
        wallAllWithItem(wall, texture);
    }

    private void triadAllWithItem(BlockTriad triad, ResourceLocation texture) {
        stairAllWithItem(triad.STAIR.get(), texture);
        slabAllWithItem(triad.SLAB.get(), texture);
        wallAllWithItem(triad.WALL.get(), texture);
    }

    private void cubeMirrorWithItem(Block block) {
        String name = ForgeRegistries.BLOCKS.getKey(block).getPath();
        ModelFile allModel = cubeAll(block);
        ModelFile mirrorModel = models().singleTexture(name + "_north_west_mirrored", new ResourceLocation(ModelProvider.BLOCK_FOLDER + "/cube_north_west_mirrored_all"), "all", blockTexture(block));
        simpleBlock(block, mirrorModel);
        simpleBlockItem(block, allModel);
    }
}

