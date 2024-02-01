package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class LapidaryBlockLootTables extends BlockLootSubProvider
{

    public LapidaryBlockLootTables()
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate()
    {
        dropOtherSilkTouch(LapidaryBlocks.STONE_WALL.get(), Blocks.COBBLESTONE_WALL);

        dropSelf(LapidaryBlocks.POLISHED_GRANITE_WALL.get());
        dropSelf(LapidaryBlocks.POLISHED_GRANITE_BRICKS.get());
        dropSelf(LapidaryBlocks.POLISHED_GRANITE_BRICK_STAIRS.get());
        dropSlab(LapidaryBlocks.POLISHED_GRANITE_BRICK_SLAB.get());
        dropSelf(LapidaryBlocks.POLISHED_GRANITE_BRICK_WALL.get());

        dropSelf(LapidaryBlocks.POLISHED_DIORITE_WALL.get());
        dropSelf(LapidaryBlocks.POLISHED_DIORITE_BRICKS.get());
        dropSelf(LapidaryBlocks.POLISHED_DIORITE_BRICK_STAIRS.get());
        dropSlab(LapidaryBlocks.POLISHED_DIORITE_BRICK_SLAB.get());
        dropSelf(LapidaryBlocks.POLISHED_DIORITE_BRICK_WALL.get());

        dropSelf(LapidaryBlocks.POLISHED_ANDESITE_WALL.get());
        dropSelf(LapidaryBlocks.POLISHED_ANDESITE_BRICKS.get());
        dropSelf(LapidaryBlocks.POLISHED_ANDESITE_BRICK_STAIRS.get());
        dropSlab(LapidaryBlocks.POLISHED_ANDESITE_BRICK_SLAB.get());
        dropSelf(LapidaryBlocks.POLISHED_ANDESITE_BRICK_WALL.get());

        dropSelf(LapidaryBlocks.CRACKED_BRICKS.get());

        dropSelf(LapidaryBlocks.NETHERRACK_STAIRS.get());
        dropSlab(LapidaryBlocks.NETHERRACK_SLAB.get());
        dropSelf(LapidaryBlocks.NETHERRACK_WALL.get());

        dropSelf(LapidaryBlocks.END_STONE_STAIRS.get());
        dropSlab(LapidaryBlocks.END_STONE_SLAB.get());
        dropSelf(LapidaryBlocks.END_STONE_WALL.get());
        dropSelf(LapidaryBlocks.CRACKED_END_STONE_BRICKS.get());

        dropSelf(LapidaryBlocks.CRACKED_PURPUR_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return LapidaryBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    private void dropSlab(SlabBlock slab)
    {
        add(slab, createSlabItemTable(slab));
    }

    private void dropOtherSilkTouch(Block block, ItemLike other)
    {
        this.add(block, this.createSingleItemTableWithSilkTouch(block, other));
    }
}
