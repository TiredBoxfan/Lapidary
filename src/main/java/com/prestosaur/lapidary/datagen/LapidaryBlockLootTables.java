package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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
        dropSelf(LapidaryBlocks.NETHERRACK_STAIRS.get());
        dropSlab(LapidaryBlocks.NETHERRACK_SLAB.get());

        dropSelf(LapidaryBlocks.END_STONE_STAIRS.get());
        dropSlab(LapidaryBlocks.END_STONE_SLAB.get());
        dropSelf(LapidaryBlocks.CRACKED_END_STONE_BRICKS.get());
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
}
