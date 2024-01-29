package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.Lapidary;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
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
                LapidaryBlocks.CRACKED_END_STONE_BRICKS.get()
        );
        this.tag(BlockTags.DRAGON_IMMUNE).add(
                Blocks.END_STONE_BRICKS,
                Blocks.END_STONE_BRICK_SLAB,
                Blocks.END_STONE_BRICK_STAIRS,
                Blocks.END_STONE_BRICK_WALL,
                LapidaryBlocks.CRACKED_END_STONE_BRICKS.get()
        );
    }
}
