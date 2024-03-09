package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.Lapidary;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import com.prestosaur.lapidary.datagen.util.ExtendedBlockStateProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;

public class LapidaryBlockStateProvider extends ExtendedBlockStateProvider {
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

        customStoneGroupAllWithItem(LapidaryBlocks.PERIDOTITE_GROUP);

        customStoneGroupAllWithItem(LapidaryBlocks.RHYOLITE_GROUP);

        cubeAllWithItem(LapidaryBlocks.CRACKED_BRICKS.get());

        cubeMirrorWithItem(LapidaryBlocks.MOSSY_MUD_BRICKS.get());
        triadAllWithItem(LapidaryBlocks.MOSSY_MUD_BRICK_TRIAD,
                new ResourceLocation(Lapidary.MODID, "block/mossy_mud_bricks"));
        cubeMirrorWithItem(LapidaryBlocks.CRACKED_MUD_BRICKS.get());

        cubeAllWithItem(LapidaryBlocks.POLISHED_SANDSTONE.get());
        triadAllWithItemSlab(LapidaryBlocks.POLISHED_SANDSTONE_TRIAD, blockTexture(LapidaryBlocks.POLISHED_SANDSTONE.get()));
        stairAllWithItem(LapidaryBlocks.CUT_SANDSTONE_STAIRS.get(), blockTexture(Blocks.CUT_SANDSTONE));
        wallAllWithItem(LapidaryBlocks.CUT_SANDSTONE_WALL.get(), blockTexture(Blocks.CUT_SANDSTONE));

        cubeAllWithItem(LapidaryBlocks.POLISHED_RED_SANDSTONE.get());
        triadAllWithItemSlab(LapidaryBlocks.POLISHED_RED_SANDSTONE_TRIAD, blockTexture(LapidaryBlocks.POLISHED_RED_SANDSTONE.get()));
        stairAllWithItem(LapidaryBlocks.CUT_RED_SANDSTONE_STAIRS.get(), blockTexture(Blocks.CUT_RED_SANDSTONE));
        wallAllWithItem(LapidaryBlocks.CUT_RED_SANDSTONE_WALL.get(), blockTexture(Blocks.CUT_RED_SANDSTONE));

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


}

