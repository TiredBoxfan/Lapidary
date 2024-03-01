package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.block.groups.BlockTriad;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import com.prestosaur.lapidary.tag.LapidaryTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class LapidaryBlockTagsProvider extends BlockTagsProvider {

    public LapidaryBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        addTriadKindTags(
                LapidaryBlocks.POLISHED_GRANITE_BRICK_TRIAD,
                LapidaryBlocks.POLISHED_DIORITE_BRICK_TRIAD,
                LapidaryBlocks.POLISHED_ANDESITE_BRICK_TRIAD,
                LapidaryBlocks.POLISHED_PERIDOTITE_TRIAD,
                LapidaryBlocks.POLISHED_PERIDOTITE_BRICKS_TRIAD,
                LapidaryBlocks.PERIDOTITE_TRIAD,
                LapidaryBlocks.NETHERRACK_TRIAD,
                LapidaryBlocks.NETHERRACK_TRIAD,
                LapidaryBlocks.END_STONE_TRIAD
        );

        addTriadTag(BlockTags.MINEABLE_WITH_PICKAXE,
                LapidaryBlocks.POLISHED_GRANITE_BRICK_TRIAD,
                LapidaryBlocks.POLISHED_DIORITE_BRICK_TRIAD,
                LapidaryBlocks.POLISHED_ANDESITE_BRICK_TRIAD,
                LapidaryBlocks.POLISHED_PERIDOTITE_TRIAD,
                LapidaryBlocks.PERIDOTITE_TRIAD,
                LapidaryBlocks.POLISHED_PERIDOTITE_BRICKS_TRIAD,
                LapidaryBlocks.NETHERRACK_TRIAD,
                LapidaryBlocks.NETHERRACK_TRIAD,
                LapidaryBlocks.END_STONE_TRIAD
        );

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                LapidaryBlocks.PETRIFIED_LOG.get(),
                LapidaryBlocks.PETRIFIED_WOOD.get(),
                LapidaryBlocks.PETRIFIED_PLANKS.get(),
                LapidaryBlocks.PETRIFIED_STAIRS.get(),

                LapidaryBlocks.STONE_WALL.get(),

                LapidaryBlocks.POLISHED_GRANITE_WALL.get(),
                LapidaryBlocks.POLISHED_GRANITE_BRICKS.get(),

                LapidaryBlocks.POLISHED_DIORITE_WALL.get(),
                LapidaryBlocks.POLISHED_DIORITE_BRICKS.get(),

                LapidaryBlocks.POLISHED_ANDESITE_WALL.get(),
                LapidaryBlocks.POLISHED_ANDESITE_BRICKS.get(),

                LapidaryBlocks.PERIDOTITE.get(),
                LapidaryBlocks.POLISHED_PERIDOTITE.get(),
                LapidaryBlocks.POLISHED_PERIDOTITE_BRICKS.get(),

                LapidaryBlocks.CRACKED_BRICKS.get(),

                LapidaryBlocks.MOSSY_MUD_BRICKS.get(),
                LapidaryBlocks.CRACKED_MUD_BRICKS.get(),

                LapidaryBlocks.CRACKED_END_STONE_BRICKS.get(),

                LapidaryBlocks.PURPUR_WALL.get(),
                LapidaryBlocks.CRACKED_PURPUR_BLOCK.get(),

                LapidaryBlocks.JADE.get(),
                LapidaryBlocks.POLISHED_JADE.get()
        );
        this.tag(BlockTags.WALLS).add(
                LapidaryBlocks.STONE_WALL.get(),
                LapidaryBlocks.POLISHED_GRANITE_WALL.get(),
                LapidaryBlocks.POLISHED_DIORITE_WALL.get(),
                LapidaryBlocks.POLISHED_ANDESITE_WALL.get(),
                LapidaryBlocks.PURPUR_WALL.get()
        );
        this.tag(Tags.Blocks.STONE).add(
                LapidaryBlocks.PERIDOTITE.get(),
                LapidaryBlocks.POLISHED_PERIDOTITE.get()
        );
        this.tag(BlockTags.BASE_STONE_OVERWORLD).add(
                LapidaryBlocks.PERIDOTITE.get()
        );
        this.tag(BlockTags.OVERWORLD_CARVER_REPLACEABLES).add(
                LapidaryBlocks.PERIDOTITE.get()
        );
        this.tag(BlockTags.NETHER_CARVER_REPLACEABLES).add(
                LapidaryBlocks.PERIDOTITE.get()
        );
        this.tag(BlockTags.DEEPSLATE_ORE_REPLACEABLES).add(
                LapidaryBlocks.PERIDOTITE.get()
        );
        this.tag(BlockTags.DRIPSTONE_REPLACEABLE).add(
                LapidaryBlocks.PERIDOTITE.get()
        );
        this.tag(BlockTags.MOSS_REPLACEABLE).add(
                LapidaryBlocks.PERIDOTITE.get()
        );
        this.tag(BlockTags.LUSH_GROUND_REPLACEABLE).add(
                LapidaryBlocks.PERIDOTITE.get()
        );
        this.tag(BlockTags.AZALEA_ROOT_REPLACEABLE).add(
                LapidaryBlocks.PERIDOTITE.get()
        );
        this.tag(BlockTags.SCULK_REPLACEABLE_WORLD_GEN).add(
                LapidaryBlocks.PERIDOTITE.get()
        );
        this.tag(BlockTags.SCULK_REPLACEABLE).add(
                LapidaryBlocks.PERIDOTITE.get()
        );
        this.tag(BlockTags.INFINIBURN_OVERWORLD).add(
                LapidaryBlocks.NETHERRACK_TRIAD.STAIR.get(),
                LapidaryBlocks.NETHERRACK_TRIAD.SLAB.get()
        );
        this.tag(BlockTags.INFINIBURN_NETHER).add(
                LapidaryBlocks.NETHERRACK_TRIAD.STAIR.get(),
                LapidaryBlocks.NETHERRACK_TRIAD.SLAB.get()
        );
        this.tag(BlockTags.INFINIBURN_END).add(
                LapidaryBlocks.NETHERRACK_TRIAD.STAIR.get(),
                LapidaryBlocks.NETHERRACK_TRIAD.SLAB.get()
        );
        this.tag(BlockTags.DRAGON_IMMUNE).add(
                Blocks.END_STONE_BRICKS,
                Blocks.END_STONE_BRICK_STAIRS,
                Blocks.END_STONE_BRICK_SLAB,
                Blocks.END_STONE_BRICK_WALL,
                LapidaryBlocks.END_STONE_TRIAD.STAIR.get(),
                LapidaryBlocks.END_STONE_TRIAD.SLAB.get(),
                LapidaryBlocks.END_STONE_TRIAD.WALL.get(),
                LapidaryBlocks.CRACKED_END_STONE_BRICKS.get()
        );
        this.tag(LapidaryTags.Blocks.BARK_BLOCKS).add(
                Blocks.OAK_WOOD,
                Blocks.STRIPPED_OAK_WOOD,
                Blocks.SPRUCE_WOOD,
                Blocks.STRIPPED_SPRUCE_WOOD,
                Blocks.BIRCH_WOOD,
                Blocks.STRIPPED_BIRCH_WOOD,
                Blocks.JUNGLE_WOOD,
                Blocks.STRIPPED_JUNGLE_WOOD,
                Blocks.ACACIA_WOOD,
                Blocks.STRIPPED_ACACIA_WOOD,
                Blocks.DARK_OAK_WOOD,
                Blocks.STRIPPED_DARK_OAK_WOOD,
                Blocks.MANGROVE_WOOD,
                Blocks.STRIPPED_MANGROVE_WOOD,
                Blocks.CRIMSON_HYPHAE,
                Blocks.STRIPPED_CRIMSON_HYPHAE,
                Blocks.WARPED_HYPHAE,
                Blocks.STRIPPED_CRIMSON_HYPHAE
        );
        this.tag(LapidaryTags.Blocks.PETRIFIABLE).addTags(
                BlockTags.LOGS,
                BlockTags.PLANKS,
                BlockTags.WOODEN_STAIRS,
                BlockTags.WOODEN_SLABS
        ).add(
                Blocks.BAMBOO_BLOCK,
                Blocks.STRIPPED_BAMBOO_BLOCK,
                Blocks.BAMBOO_MOSAIC,
                Blocks.BAMBOO_MOSAIC_STAIRS,
                Blocks.BAMBOO_MOSAIC_SLAB
        );
        this.tag(LapidaryTags.Blocks.COPPER_BLOCKS).add(
                Blocks.COPPER_BLOCK,
                Blocks.WAXED_COPPER_BLOCK,
                Blocks.CUT_COPPER,
                Blocks.WAXED_CUT_COPPER,
                Blocks.CUT_COPPER_STAIRS,
                Blocks.WAXED_CUT_COPPER_STAIRS,
                Blocks.CUT_COPPER_SLAB,
                Blocks.WAXED_CUT_COPPER_SLAB,

                Blocks.EXPOSED_COPPER,
                Blocks.WAXED_EXPOSED_COPPER,
                Blocks.EXPOSED_CUT_COPPER,
                Blocks.WAXED_EXPOSED_CUT_COPPER,
                Blocks.EXPOSED_CUT_COPPER_STAIRS,
                Blocks.WAXED_EXPOSED_CUT_COPPER_STAIRS,
                Blocks.EXPOSED_CUT_COPPER_SLAB,
                Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB,

                Blocks.WEATHERED_COPPER,
                Blocks.WAXED_WEATHERED_COPPER,
                Blocks.WEATHERED_CUT_COPPER,
                Blocks.WAXED_WEATHERED_CUT_COPPER,
                Blocks.WEATHERED_CUT_COPPER_STAIRS,
                Blocks.WAXED_WEATHERED_CUT_COPPER_STAIRS,
                Blocks.WEATHERED_CUT_COPPER_SLAB,
                Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB,

                Blocks.OXIDIZED_COPPER,
                Blocks.WAXED_OXIDIZED_COPPER,
                Blocks.OXIDIZED_CUT_COPPER,
                Blocks.WAXED_OXIDIZED_CUT_COPPER,
                Blocks.OXIDIZED_CUT_COPPER_STAIRS,
                Blocks.WAXED_OXIDIZED_CUT_COPPER_STAIRS,
                Blocks.OXIDIZED_CUT_COPPER_SLAB,
                Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB
        );
    }

    private void addTriadKindTags(BlockTriad ... triads) {
        for (BlockTriad triad : triads) {
            this.tag(BlockTags.STAIRS).add(triad.STAIR.get());
            this.tag(BlockTags.SLABS).add(triad.SLAB.get());
            this.tag(BlockTags.WALLS).add(triad.WALL.get());
        }
    }

    private void addTriadTag(TagKey<Block> blockTag, BlockTriad ... triads) {
        for (BlockTriad triad : triads) {
            this.tag(blockTag).add(triad.STAIR.get());
            this.tag(blockTag).add(triad.SLAB.get());
            this.tag(blockTag).add(triad.WALL.get());
        }
    }
}
