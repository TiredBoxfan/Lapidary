package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.Lapidary;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import com.prestosaur.lapidary.datagen.util.ExtendedBlockStateProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.common.data.ExistingFileHelper;

public class VanillaBlockStateProvider extends ExtendedBlockStateProvider {

    public VanillaBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, "minecraft", exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        slabBlock((SlabBlock) Blocks.PETRIFIED_OAK_SLAB,
                LapidaryBlocks.PETRIFIED_PLANKS.getId(),
                blockTexture(LapidaryBlocks.PETRIFIED_PLANKS.get()));

        slabBlockSide((SlabBlock) Blocks.POLISHED_GRANITE_SLAB,
                new ResourceLocation(Lapidary.MODID, "block/polished_granite_slab_side"),
                blockTexture(Blocks.POLISHED_GRANITE));

        slabBlockSide((SlabBlock) Blocks.POLISHED_DIORITE_SLAB,
                new ResourceLocation(Lapidary.MODID, "block/polished_diorite_slab_side"),
                blockTexture(Blocks.POLISHED_DIORITE));

        slabBlockSide((SlabBlock) Blocks.POLISHED_ANDESITE_SLAB,
                new ResourceLocation(Lapidary.MODID, "block/polished_andesite_slab_side"),
                blockTexture(Blocks.POLISHED_ANDESITE));

        slabBlockSide((SlabBlock) Blocks.POLISHED_DEEPSLATE_SLAB,
                new ResourceLocation(Lapidary.MODID, "block/polished_deepslate_slab_side"),
                blockTexture(Blocks.POLISHED_DEEPSLATE));

        ResourceLocation sandstoneSide = new ResourceLocation("block/sandstone");
        ResourceLocation sandstoneTop = new ResourceLocation("block/sandstone_top");
        columnBlock(Blocks.SANDSTONE, sandstoneSide, sandstoneTop);
        stairsBlock((StairBlock) Blocks.SANDSTONE_STAIRS, sandstoneSide, sandstoneTop, sandstoneTop);
        slabBlockSide((SlabBlock) Blocks.SANDSTONE_SLAB, sandstoneSide, sandstoneTop);
        cubeAll(Blocks.CUT_SANDSTONE);
        //slabBlockSide((SlabBlock) Blocks.CUT_SANDSTONE_SLAB, new ResourceLocation(Lapidary.MODID, "block/polished_sandstone_slab_side"), blockTexture(Blocks.CUT_SANDSTONE));
        slabBlock((SlabBlock) Blocks.CUT_SANDSTONE_SLAB, blockTexture(Blocks.CUT_SANDSTONE), blockTexture(Blocks.CUT_SANDSTONE));
        columnBlock(Blocks.CHISELED_SANDSTONE, blockTexture(Blocks.CHISELED_SANDSTONE), new ResourceLocation(Lapidary.MODID, "block/polished_sandstone"));

        ResourceLocation redSandstoneSide = new ResourceLocation("block/red_sandstone");
        ResourceLocation redSandstoneTop = new ResourceLocation("block/red_sandstone_top");
        columnBlock(Blocks.RED_SANDSTONE, redSandstoneSide, redSandstoneTop);
        stairsBlock((StairBlock) Blocks.RED_SANDSTONE_STAIRS, redSandstoneSide, redSandstoneTop, redSandstoneTop);
        slabBlockSide((SlabBlock) Blocks.RED_SANDSTONE_SLAB, redSandstoneSide, redSandstoneTop);
        cubeAll(Blocks.CUT_RED_SANDSTONE);
        //slabBlockSide((SlabBlock) Blocks.CUT_RED_SANDSTONE_SLAB, new ResourceLocation(Lapidary.MODID, "block/polished_red_sandstone_slab_side"), blockTexture(Blocks.CUT_RED_SANDSTONE));
        slabBlock((SlabBlock) Blocks.CUT_RED_SANDSTONE_SLAB, blockTexture(Blocks.CUT_RED_SANDSTONE), blockTexture(Blocks.CUT_RED_SANDSTONE));
        columnBlock(Blocks.CHISELED_RED_SANDSTONE, blockTexture(Blocks.CHISELED_RED_SANDSTONE), new ResourceLocation(Lapidary.MODID,"block/polished_red_sandstone"));

        slabBlockSide((SlabBlock) Blocks.POLISHED_BLACKSTONE_SLAB,
                new ResourceLocation(Lapidary.MODID, "block/polished_blackstone_slab_side"),
                blockTexture(Blocks.POLISHED_BLACKSTONE));

        slabBlockSide((SlabBlock) Blocks.QUARTZ_SLAB,
                new ResourceLocation(Lapidary.MODID, "block/quartz_slab_side"),
                new ResourceLocation("block/quartz_block_top"));
    }
}
