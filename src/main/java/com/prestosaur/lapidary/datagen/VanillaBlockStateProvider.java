package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.Lapidary;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class VanillaBlockStateProvider extends BlockStateProvider
{

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

    private void slabBlockSide(SlabBlock slab, ResourceLocation sideTexture, ResourceLocation endTexture)
    {
        String name = ForgeRegistries.BLOCKS.getKey(slab).getPath();
        ModelFile bottomslab = models().slab(name, sideTexture, endTexture, endTexture);
        ModelFile topslab = models().slabTop(name + "_top", sideTexture, endTexture, endTexture);
        ModelFile doubleslab = models().cubeColumn(name + "_double", sideTexture, endTexture);

        slabBlock(slab, bottomslab, topslab, doubleslab);
    }
}
