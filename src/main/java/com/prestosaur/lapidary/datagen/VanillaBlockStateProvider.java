package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.Lapidary;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import com.prestosaur.lapidary.datagen.util.ExtendedBlockStateProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
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

        slabBlockSide((SlabBlock) Blocks.POLISHED_BLACKSTONE_SLAB,
                new ResourceLocation(Lapidary.MODID, "block/polished_blackstone_slab_side"),
                blockTexture(Blocks.POLISHED_BLACKSTONE));

        slabBlockSide((SlabBlock) Blocks.QUARTZ_SLAB,
                new ResourceLocation(Lapidary.MODID, "block/quartz_slab_side"),
                new ResourceLocation("block/quartz_block_top"));
    }
}
