package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.block.BlockTriad;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class LapidaryBlockLootTables extends BlockLootSubProvider {

    public LapidaryBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(LapidaryBlocks.PETRIFIED_LOG.get());
        dropSelf(LapidaryBlocks.PETRIFIED_WOOD.get());
        dropSelf(LapidaryBlocks.PETRIFIED_PLANKS.get());
        dropSelf(LapidaryBlocks.PETRIFIED_STAIRS.get());

        dropOtherSilkTouch(LapidaryBlocks.STONE_WALL.get(), Blocks.COBBLESTONE_WALL);

        dropSelf(LapidaryBlocks.POLISHED_GRANITE_WALL.get());
        dropSelf(LapidaryBlocks.POLISHED_GRANITE_BRICKS.get());
        triadDrops(LapidaryBlocks.POLISHED_GRANITE_BRICK_TRIAD);

        dropSelf(LapidaryBlocks.POLISHED_DIORITE_WALL.get());
        dropSelf(LapidaryBlocks.POLISHED_DIORITE_BRICKS.get());
        triadDrops(LapidaryBlocks.POLISHED_DIORITE_BRICK_TRIAD);

        dropSelf(LapidaryBlocks.POLISHED_ANDESITE_WALL.get());
        dropSelf(LapidaryBlocks.POLISHED_ANDESITE_BRICKS.get());
        triadDrops(LapidaryBlocks.POLISHED_ANDESITE_BRICK_TRIAD);

        dropSelf(LapidaryBlocks.PERIDOTITE.get());
        triadDrops(LapidaryBlocks.PERIDOTITE_TRIAD);

        dropSelf(LapidaryBlocks.CRACKED_BRICKS.get());

        dropSelf(LapidaryBlocks.MOSSY_MUD_BRICKS.get());
        triadDrops(LapidaryBlocks.MOSSY_MUD_BRICK_TRIAD);
        dropSelf(LapidaryBlocks.CRACKED_MUD_BRICKS.get());

        triadDrops(LapidaryBlocks.NETHERRACK_TRIAD);

        triadDrops(LapidaryBlocks.END_STONE_TRIAD);
        dropSelf(LapidaryBlocks.CRACKED_END_STONE_BRICKS.get());

        dropSelf(LapidaryBlocks.PURPUR_WALL.get());
        dropSelf(LapidaryBlocks.CRACKED_PURPUR_BLOCK.get());

        dropSelf(LapidaryBlocks.JADE.get());
        dropSelf(LapidaryBlocks.POLISHED_JADE.get());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return LapidaryBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    private void dropSlab(SlabBlock slab) {
        add(slab, createSlabItemTable(slab));
    }

    private void dropOtherSilkTouch(Block block, ItemLike other) {
        this.add(block, this.createSingleItemTableWithSilkTouch(block, other));
    }

    private void triadDrops(BlockTriad triad) {
        dropSelf(triad.STAIR.get());
        dropSlab(triad.SLAB.get());
        dropSelf(triad.WALL.get());
    }
}
