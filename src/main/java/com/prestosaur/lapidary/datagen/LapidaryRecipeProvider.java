package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.Lapidary;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class LapidaryRecipeProvider extends RecipeProvider implements IConditionBuilder
{
    public LapidaryRecipeProvider(PackOutput pOutput)
    {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer)
    {
        addTriadRecipies(consumer, LapidaryBlocks.NETHERRACK_STAIRS, LapidaryBlocks.NETHERRACK_SLAB, LapidaryBlocks.NETHERRACK_WALL, Blocks.NETHERRACK);
        addTriadRecipies(consumer, LapidaryBlocks.END_STONE_STAIRS, LapidaryBlocks.END_STONE_SLAB, LapidaryBlocks.END_STONE_WALL, Blocks.END_STONE);
        smeltingResultFromBase(consumer, LapidaryBlocks.CRACKED_END_STONE_BRICKS.get(), Blocks.END_STONE_BRICKS);
    }

    private <T extends Block> void addStoneCutting(Consumer<FinishedRecipe> consumer, RegistryObject<T> result, ItemLike material, int count)
    {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), RecipeCategory.BUILDING_BLOCKS, result.get(), count)
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, result.getKey().location() + "_stonecutting");
    }

    private void addStairRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<StairBlock> result, ItemLike material)
    {
        stairBuilder(result.get(), Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, result.getKey().location());

        addStoneCutting(consumer, result, material, 1);
    }

    private void addSlabRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<SlabBlock> result, ItemLike material)
    {
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, result.get(), Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, result.getKey().location());

        addStoneCutting(consumer, result, material, 2);
    }

    private void addWallRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<WallBlock> result, ItemLike material)
    {
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, result.get(), Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, result.getKey().location());

        addStoneCutting(consumer, result, material, 1);
    }

    private void addTriadRecipies(Consumer<FinishedRecipe> consumer, RegistryObject<StairBlock> stair, RegistryObject<SlabBlock> slab, RegistryObject<WallBlock> wall, ItemLike material)
    {
        addStairRecipes(consumer, stair, material);
        addSlabRecipes(consumer, slab, material);
        addWallRecipes(consumer, wall, material);
    }
}
