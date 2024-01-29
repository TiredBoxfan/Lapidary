package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class LapidaryBlockLootTables extends BlockLootSubProvider
{

    public LapidaryBlockLootTables()
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(LapidaryBlocks.CRACKED_END_STONE_BRICKS.get());
        dropSelf(LapidaryBlocks.NETHERRACK_STAIRS.get());
        add(LapidaryBlocks.NETHERRACK_SLAB.get(), createSlabItemTable(LapidaryBlocks.NETHERRACK_SLAB.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return LapidaryBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
