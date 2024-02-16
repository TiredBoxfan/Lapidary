package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.block.BlockTriad;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class LapidaryRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public LapidaryRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        addStonecutting(consumer, LapidaryBlocks.PETRIFIED_PLANKS, 1, LapidaryBlocks.PETRIFIED_LOG.get());
        woodFromLogs(consumer, LapidaryBlocks.PETRIFIED_WOOD.get(), LapidaryBlocks.PETRIFIED_LOG.get());
        addStonecutting(consumer, LapidaryBlocks.PETRIFIED_WOOD, 1, LapidaryBlocks.PETRIFIED_WOOD.get());
        addCraftStairsAndStonecut(consumer, LapidaryBlocks.PETRIFIED_STAIRS, LapidaryBlocks.PETRIFIED_PLANKS.get(), LapidaryBlocks.PETRIFIED_LOG.get(), LapidaryBlocks.PETRIFIED_WOOD.get());

        addCraftWallAndStonecut(consumer, LapidaryBlocks.STONE_WALL, Blocks.STONE);

        addCraftWallAndStonecut(consumer, LapidaryBlocks.POLISHED_GRANITE_WALL, Blocks.POLISHED_GRANITE, Blocks.GRANITE);
        addCraftBrickSetAndStonecut(consumer, LapidaryBlocks.POLISHED_GRANITE_BRICKS, LapidaryBlocks.POLISHED_GRANITE_BRICK_STAIRS, LapidaryBlocks.POLISHED_GRANITE_BRICK_SLAB, LapidaryBlocks.POLISHED_GRANITE_BRICK_WALL,
                Blocks.POLISHED_GRANITE, Blocks.GRANITE);

        addCraftWallAndStonecut(consumer, LapidaryBlocks.POLISHED_DIORITE_WALL, Blocks.POLISHED_DIORITE, Blocks.DIORITE);
        addCraftBrickSetAndStonecut(consumer, LapidaryBlocks.POLISHED_DIORITE_BRICKS, LapidaryBlocks.POLISHED_DIORITE_BRICK_STAIRS, LapidaryBlocks.POLISHED_DIORITE_BRICK_SLAB, LapidaryBlocks.POLISHED_DIORITE_BRICK_WALL,
                Blocks.POLISHED_DIORITE, Blocks.DIORITE);

        addCraftWallAndStonecut(consumer, LapidaryBlocks.POLISHED_ANDESITE_WALL, Blocks.POLISHED_ANDESITE, Blocks.ANDESITE);
        addCraftBrickSetAndStonecut(consumer, LapidaryBlocks.POLISHED_ANDESITE_BRICKS, LapidaryBlocks.POLISHED_ANDESITE_BRICK_STAIRS, LapidaryBlocks.POLISHED_ANDESITE_BRICK_SLAB, LapidaryBlocks.POLISHED_ANDESITE_BRICK_WALL,
                Blocks.POLISHED_ANDESITE, Blocks.ANDESITE);

        addCraftTriadAndStonecut(consumer, LapidaryBlocks.PERIDOTITE_TRIAD, LapidaryBlocks.PERIDOTITE.get());

        smeltingResultFromBase(consumer, LapidaryBlocks.CRACKED_BRICKS.get(), Blocks.BRICKS);

        addCraftMossy(consumer, LapidaryBlocks.MOSSY_MUD_BRICKS, Blocks.MUD_BRICKS);
        addCraftTriadAndStonecut(consumer, LapidaryBlocks.MOSSY_MUD_BRICK_STAIRS, LapidaryBlocks.MOSSY_MUD_BRICK_SLAB, LapidaryBlocks.MOSSY_MUD_BRICK_WALL, LapidaryBlocks.MOSSY_MUD_BRICKS.get());
        smeltingResultFromBase(consumer, LapidaryBlocks.CRACKED_MUD_BRICKS.get(), Blocks.MUD_BRICKS);

        addCraftTriadAndStonecut(consumer, LapidaryBlocks.NETHERRACK_STAIRS, LapidaryBlocks.NETHERRACK_SLAB, LapidaryBlocks.NETHERRACK_WALL, Blocks.NETHERRACK);

        addCraftTriadAndStonecut(consumer, LapidaryBlocks.END_STONE_STAIRS, LapidaryBlocks.END_STONE_SLAB, LapidaryBlocks.END_STONE_WALL, Blocks.END_STONE);
        smeltingResultFromBase(consumer, LapidaryBlocks.CRACKED_END_STONE_BRICKS.get(), Blocks.END_STONE_BRICKS);

        addCraftWallAndStonecut(consumer, LapidaryBlocks.PURPUR_WALL, Blocks.PURPUR_BLOCK);
        smeltingResultFromBase(consumer, LapidaryBlocks.CRACKED_PURPUR_BLOCK.get(), Blocks.PURPUR_BLOCK);
    }

    private <T extends Block> void addStonecutting(Consumer<FinishedRecipe> consumer, RegistryObject<T> result, int count, ItemLike material) {
        String path = ForgeRegistries.ITEMS.getKey(material.asItem()).getPath();
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), RecipeCategory.BUILDING_BLOCKS, result.get(), count)
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, result.getKey().location() + "_from_" + path + "_stonecutting");
    }

    private <T extends Block> void addStonecutting(Consumer<FinishedRecipe> consumer, RegistryObject<T> result, int count, ItemLike... materials) {
        for (ItemLike i : materials) {
            addStonecutting(consumer, result, count, i);
        }
    }

    private void addCraftBricks(Consumer<FinishedRecipe> consumer, RegistryObject<Block> result, ItemLike material) {
        polishedBuilder(RecipeCategory.BUILDING_BLOCKS, result.get(), Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, result.getKey().location());
    }

    private void addCraftBricksAndStonecut(Consumer<FinishedRecipe> consumer, RegistryObject<Block> result, ItemLike material, ItemLike... alts) {
        addCraftBricks(consumer, result, material);
        addStonecutting(consumer, result, 1, material);
        addStonecutting(consumer, result, 1, alts);
    }

    private void addCraftStairs(Consumer<FinishedRecipe> consumer, RegistryObject<StairBlock> result, ItemLike material) {
        stairBuilder(result.get(), Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, result.getKey().location());
    }

    private void addCraftStairsAndStonecut(Consumer<FinishedRecipe> consumer, RegistryObject<StairBlock> result, ItemLike material, ItemLike... alts) {
        addCraftStairs(consumer, result, material);
        addStonecutting(consumer, result, 1, material);
        addStonecutting(consumer, result, 1, alts);
    }

    private void addCraftSlab(Consumer<FinishedRecipe> consumer, RegistryObject<SlabBlock> result, ItemLike material) {
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, result.get(), Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, result.getKey().location());
    }

    private void addCraftSlabAndStonecut(Consumer<FinishedRecipe> consumer, RegistryObject<SlabBlock> result, ItemLike material, ItemLike... alts) {
        addCraftSlab(consumer, result, material);
        addStonecutting(consumer, result, 2, material);
        addStonecutting(consumer, result, 2, alts);
    }

    private void addCraftWall(Consumer<FinishedRecipe> consumer, RegistryObject<WallBlock> result, ItemLike material) {
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, result.get(), Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, result.getKey().location());
    }

    private void addCraftWallAndStonecut(Consumer<FinishedRecipe> consumer, RegistryObject<WallBlock> result, ItemLike material, ItemLike... alts) {
        addCraftWall(consumer, result, material);
        addStonecutting(consumer, result, 1, material);
        addStonecutting(consumer, result, 1, alts);
    }

    private void addCraftTriadAndStonecut(Consumer<FinishedRecipe> consumer, RegistryObject<StairBlock> stair, RegistryObject<SlabBlock> slab, RegistryObject<WallBlock> wall, ItemLike material, ItemLike... alts) {
        addCraftStairsAndStonecut(consumer, stair, material, alts);
        addCraftSlabAndStonecut(consumer, slab, material, alts);
        addCraftWallAndStonecut(consumer, wall, material, alts);
    }

    public void addCraftTriadAndStonecut(Consumer<FinishedRecipe> consumer, BlockTriad triad, ItemLike material, ItemLike ... alts) {
        addCraftStairsAndStonecut(consumer, triad.STAIR, material, alts);
        addCraftSlabAndStonecut(consumer, triad.SLAB, material, alts);
        addCraftWallAndStonecut(consumer, triad.WALL, material, alts);
    }

    private void addCraftBrickSetAndStonecut(Consumer<FinishedRecipe> consumer, RegistryObject<Block> block, RegistryObject<StairBlock> stair, RegistryObject<SlabBlock> slab, RegistryObject<WallBlock> wall, ItemLike material, ItemLike... alts) {
        addCraftBricksAndStonecut(consumer, block, material, alts);
        // Allow the triad to be created from the block.
        addCraftTriadAndStonecut(consumer, stair, slab, wall, block.get(), alts);
        // Allow the triad to be stonecut from the material.
        addStonecutting(consumer, stair, 1, material);
        addStonecutting(consumer, slab, 2, material);
        addStonecutting(consumer, wall, 1, material);
    }

    private void addCraftMossy(Consumer<FinishedRecipe> consumer, RegistryObject<Block> result, ItemLike base) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, result.get())
                .requires(base)
                .requires(Items.VINE)
                .unlockedBy(getHasName(base), has(base))
                .group(result.getKey().location().getPath())
                .save(consumer, result.getKey().location() + "_from_vine");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, result.get())
                .requires(base)
                .requires(Items.MOSS_BLOCK)
                .unlockedBy(getHasName(base), has(base))
                .group(result.getKey().location().getPath())
                .save(consumer, result.getKey().location() + "_from_moss_block");
    }
}
