package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.Lapidary;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class LapidaryBlockTagsProvider extends BlockTagsProvider
{

    public LapidaryBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                LapidaryBlocks.NETHERRACK_STAIRS.get(),
                LapidaryBlocks.NETHERRACK_SLAB.get(),
                LapidaryBlocks.NETHERRACK_WALL.get(),

                LapidaryBlocks.END_STONE_STAIRS.get(),
                LapidaryBlocks.END_STONE_SLAB.get(),
                LapidaryBlocks.CRACKED_END_STONE_BRICKS.get()
        );
        this.tag(BlockTags.SLABS).add(
                LapidaryBlocks.NETHERRACK_SLAB.get(),
                LapidaryBlocks.END_STONE_SLAB.get()
        );
        this.tag(BlockTags.STAIRS).add(
                LapidaryBlocks.NETHERRACK_STAIRS.get(),
                LapidaryBlocks.END_STONE_STAIRS.get()
        );
        this.tag(BlockTags.WALLS).add(
                LapidaryBlocks.NETHERRACK_WALL.get()
        );
        this.tag(BlockTags.INFINIBURN_OVERWORLD).add(
                LapidaryBlocks.NETHERRACK_STAIRS.get(),
                LapidaryBlocks.NETHERRACK_SLAB.get()
        );
        this.tag(BlockTags.INFINIBURN_NETHER).add(
                LapidaryBlocks.NETHERRACK_STAIRS.get(),
                LapidaryBlocks.NETHERRACK_SLAB.get()
        );
        this.tag(BlockTags.INFINIBURN_END).add(
                LapidaryBlocks.NETHERRACK_STAIRS.get(),
                LapidaryBlocks.NETHERRACK_SLAB.get()
        );
        this.tag(BlockTags.DRAGON_IMMUNE).add(
                Blocks.END_STONE_BRICKS,
                Blocks.END_STONE_BRICK_STAIRS,
                Blocks.END_STONE_BRICK_SLAB,
                Blocks.END_STONE_BRICK_WALL,
                LapidaryBlocks.END_STONE_STAIRS.get(),
                LapidaryBlocks.END_STONE_SLAB.get(),
                LapidaryBlocks.CRACKED_END_STONE_BRICKS.get()
        );
    }
}
