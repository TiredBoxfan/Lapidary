package com.prestosaur.lapidary.datagen.util;

import com.prestosaur.lapidary.block.groups.BlockTriad;
import com.prestosaur.lapidary.block.groups.CustomStoneGroup;
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

public abstract class ExtendedBlockStateProvider extends BlockStateProvider {

    public ExtendedBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    // Creates a simple block and item with the same texture on each side.
    protected void cubeAllWithItem(Block block) {
        simpleBlockWithItem(block, cubeAll(block));
    }

    // Creates an item from an existing block.
    protected <T extends Block> void simpleItemFromBlock(Block block) {
        ResourceLocation id = ForgeRegistries.BLOCKS.getKey(block);
        itemModels().getBuilder(id.getPath()).parent(models().getExistingFile(id));
    }

    // Creates a stair with an item where all sides are the same.
    protected void stairAllWithItem(StairBlock stair, ResourceLocation texture) {
        // Do Block Model.
        stairsBlock(stair, texture);
        // Do Item Model.
        simpleItemFromBlock(stair);
    }

    // Creates a slab with an item where all sides are the same.
    protected void slabAllWithItem(SlabBlock slab, ResourceLocation texture) {
        // Do Block Model.
        slabBlock(slab, texture, texture);
        // Do Item Model.
        simpleItemFromBlock(slab);
    }

    // Creates a wall with an item where all sides are the same.
    protected void wallAllWithItem(WallBlock wall, ResourceLocation texture) {
        // Do Block Model.
        wallBlock(wall, texture);
        // Do Item Model.
        itemModels().wallInventory(ForgeRegistries.BLOCKS.getKey(wall).getPath(), texture);
    }

    protected void triadAllWithItem(BlockTriad triad, ResourceLocation texture) {
        stairAllWithItem(triad.STAIR.get(), texture);
        slabAllWithItem(triad.SLAB.get(), texture);
        wallAllWithItem(triad.WALL.get(), texture);
    }

    protected void customStoneGroupAllWithItem(CustomStoneGroup group) {
        cubeAllWithItem(group.BASE_BLOCK.get());
        triadAllWithItem(group.BASE_TRIAD, blockTexture(group.BASE_BLOCK.get()));
        cubeAllWithItem(group.POLISHED_BLOCK.get());
        triadAllWithItem(group.POLISHED_TRIAD, blockTexture(group.POLISHED_BLOCK.get()));
        cubeAllWithItem(group.BRICK_BLOCK.get());
        triadAllWithItem(group.BRICK_TRIAD, blockTexture(group.BRICK_BLOCK.get()));
    }

    protected void cubeMirrorWithItem(Block block) {
        String name = ForgeRegistries.BLOCKS.getKey(block).getPath();
        ModelFile allModel = cubeAll(block);
        ModelFile mirrorModel = models().singleTexture(name + "_north_west_mirrored", new ResourceLocation(ModelProvider.BLOCK_FOLDER + "/cube_north_west_mirrored_all"), "all", blockTexture(block));
        simpleBlock(block, mirrorModel);
        simpleBlockItem(block, allModel);
    }

    protected void slabBlockSide(SlabBlock slab, ResourceLocation sideTexture, ResourceLocation endTexture) {
        String name = ForgeRegistries.BLOCKS.getKey(slab).getPath();
        ModelFile bottomslab = models().slab(name, sideTexture, endTexture, endTexture);
        ModelFile topslab = models().slabTop(name + "_top", sideTexture, endTexture, endTexture);
        ModelFile doubleslab = models().cubeColumn(name + "_double", sideTexture, endTexture);

        slabBlock(slab, bottomslab, topslab, doubleslab);
    }
}
