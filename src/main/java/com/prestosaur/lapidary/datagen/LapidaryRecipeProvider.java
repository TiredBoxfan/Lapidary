package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
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
        addWallRecipes(consumer, LapidaryBlocks.STONE_WALL, Blocks.STONE);
        smeltingResultFromBase(consumer, LapidaryBlocks.CRACKED_BRICKS.get(), Blocks.BRICKS);
        addTriadRecipes(consumer, LapidaryBlocks.NETHERRACK_STAIRS, LapidaryBlocks.NETHERRACK_SLAB, LapidaryBlocks.NETHERRACK_WALL, Blocks.NETHERRACK);
        addTriadRecipes(consumer, LapidaryBlocks.END_STONE_STAIRS, LapidaryBlocks.END_STONE_SLAB, LapidaryBlocks.END_STONE_WALL, Blocks.END_STONE);
        smeltingResultFromBase(consumer, LapidaryBlocks.CRACKED_END_STONE_BRICKS.get(), Blocks.END_STONE_BRICKS);
        smeltingResultFromBase(consumer, LapidaryBlocks.CRACKED_PURPUR_BLOCK.get(), Blocks.PURPUR_BLOCK);
        addPolishedBricksRecipes(consumer, LapidaryBlocks.POLISHED_GRANITE_BRICKS, Blocks.POLISHED_GRANITE, Blocks.GRANITE);
        addPolishedBricksRecipes(consumer, LapidaryBlocks.POLISHED_DIORITE_BRICKS, Blocks.POLISHED_DIORITE, Blocks.DIORITE);
        addPolishedBricksRecipes(consumer, LapidaryBlocks.POLISHED_ANDESITE_BRICKS, Blocks.POLISHED_ANDESITE, Blocks.ANDESITE);
    }

    private <T extends Block> void addStoneCutting(Consumer<FinishedRecipe> consumer, RegistryObject<T> result, ItemLike material, int count)
    {
        String path = ForgeRegistries.ITEMS.getKey(material.asItem()).getPath();
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), RecipeCategory.BUILDING_BLOCKS, result.get(), count)
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, result.getKey().location() + "_from_" + path +"_stonecutting");
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

    private void addTriadRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<StairBlock> stair, RegistryObject<SlabBlock> slab, RegistryObject<WallBlock> wall, ItemLike material)
    {
        addStairRecipes(consumer, stair, material);
        addSlabRecipes(consumer, slab, material);
        addWallRecipes(consumer, wall, material);
    }

    private void addPolishedBricksRecipes(Consumer<FinishedRecipe> consumer, RegistryObject<Block> result, ItemLike material, ItemLike ... additionals)
    {
        polishedBuilder(RecipeCategory.BUILDING_BLOCKS, result.get(), Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, result.getKey().location());
        addStoneCutting(consumer, result, material, 1);
        for(ItemLike item : additionals)
        {
            addStoneCutting(consumer, result, item, 1);
        }
    }
}
