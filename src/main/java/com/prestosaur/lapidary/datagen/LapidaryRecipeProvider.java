package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.block.groups.BlockTriad;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import com.prestosaur.lapidary.block.groups.CustomStoneGroup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
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

        // Modify Vanilla

        reverseVanillaSmelt(consumer, Items.CRACKED_STONE_BRICKS, Items.STONE_BRICKS);
        reverseVanillaSmelt(consumer, Items.CRACKED_DEEPSLATE_BRICKS, Items.DEEPSLATE_BRICKS);
        reverseVanillaSmelt(consumer, Items.CRACKED_DEEPSLATE_TILES, Items.DEEPSLATE_TILES);
        reverseVanillaSmelt(consumer, Items.CRACKED_NETHER_BRICKS, Items.NETHER_BRICKS);
        reverseVanillaSmelt(consumer, Items.CRACKED_POLISHED_BLACKSTONE_BRICKS, Items.POLISHED_BLACKSTONE_BRICKS);

        // Lapidary

        addStonecutting(consumer, LapidaryBlocks.PETRIFIED_PLANKS, 1, LapidaryBlocks.PETRIFIED_LOG.get());
        woodFromLogs(consumer, LapidaryBlocks.PETRIFIED_WOOD.get(), LapidaryBlocks.PETRIFIED_LOG.get());
        addStonecutting(consumer, LapidaryBlocks.PETRIFIED_WOOD, 1, LapidaryBlocks.PETRIFIED_WOOD.get());
        addCraftStairsAndStonecut(consumer, LapidaryBlocks.PETRIFIED_STAIRS, LapidaryBlocks.PETRIFIED_PLANKS.get(), LapidaryBlocks.PETRIFIED_LOG.get(), LapidaryBlocks.PETRIFIED_WOOD.get());

        addCraftWallAndStonecut(consumer, LapidaryBlocks.STONE_WALL, Blocks.STONE);

        addCraftWallAndStonecut(consumer, LapidaryBlocks.POLISHED_GRANITE_WALL, Blocks.POLISHED_GRANITE, Blocks.GRANITE);
        addCraftBrickSetAndStonecut(consumer, LapidaryBlocks.POLISHED_GRANITE_BRICKS, LapidaryBlocks.POLISHED_GRANITE_BRICK_TRIAD,
                Blocks.POLISHED_GRANITE, Blocks.GRANITE);

        addCraftWallAndStonecut(consumer, LapidaryBlocks.POLISHED_DIORITE_WALL, Blocks.POLISHED_DIORITE, Blocks.DIORITE);
        addCraftBrickSetAndStonecut(consumer, LapidaryBlocks.POLISHED_DIORITE_BRICKS, LapidaryBlocks.POLISHED_DIORITE_BRICK_TRIAD,
                Blocks.POLISHED_DIORITE, Blocks.DIORITE);

        addCraftWallAndStonecut(consumer, LapidaryBlocks.POLISHED_ANDESITE_WALL, Blocks.POLISHED_ANDESITE, Blocks.ANDESITE);
        addCraftBrickSetAndStonecut(consumer, LapidaryBlocks.POLISHED_ANDESITE_BRICKS, LapidaryBlocks.POLISHED_ANDESITE_BRICK_TRIAD,
                Blocks.POLISHED_ANDESITE, Blocks.ANDESITE);

        twoByTwoChecker(consumer, RecipeCategory.BUILDING_BLOCKS, LapidaryBlocks.PERIDOTITE_GROUP.BASE_BLOCK.get(),
                Items.QUARTZ, Items.COBBLED_DEEPSLATE);
        addCraftCustomStoneGroupAndStonecut(consumer, LapidaryBlocks.PERIDOTITE_GROUP);

        addCraftCustomStoneGroupAndStonecut(consumer, LapidaryBlocks.RHYOLITE_GROUP);

        smeltingResultFromBase(consumer, Blocks.BRICKS, LapidaryBlocks.CRACKED_BRICKS.get());

        addCraftMossy(consumer, LapidaryBlocks.MOSSY_MUD_BRICKS, Blocks.MUD_BRICKS);
        addCraftTriadAndStonecut(consumer, LapidaryBlocks.MOSSY_MUD_BRICK_TRIAD, LapidaryBlocks.MOSSY_MUD_BRICKS.get());
        smeltingResultFromBase(consumer, Blocks.MUD_BRICKS, LapidaryBlocks.CRACKED_MUD_BRICKS.get());

        addCraftTriadAndStonecut(consumer, LapidaryBlocks.NETHERRACK_TRIAD, Blocks.NETHERRACK);

        addCraftTriadAndStonecut(consumer, LapidaryBlocks.END_STONE_TRIAD, Blocks.END_STONE);
        smeltingResultFromBase(consumer, Blocks.END_STONE_BRICKS, LapidaryBlocks.CRACKED_END_STONE_BRICKS.get());

        addCraftWallAndStonecut(consumer, LapidaryBlocks.PURPUR_WALL, Blocks.PURPUR_BLOCK);
        smeltingResultFromBase(consumer, Blocks.PURPUR_BLOCK, LapidaryBlocks.CRACKED_PURPUR_BLOCK.get());
    }

    private void reverseVanillaSmelt(Consumer<FinishedRecipe> consumer, ItemLike input, ItemLike output) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.BUILDING_BLOCKS, output, 0.1F, 200)
                .unlockedBy(getHasName(input), has(input))
                .save(consumer, new ResourceLocation(getItemName(input)));
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

    private void addCraftTriadAndStonecut(Consumer<FinishedRecipe> consumer, BlockTriad triad, ItemLike material, ItemLike ... alts) {
        addCraftStairsAndStonecut(consumer, triad.STAIR, material, alts);
        addCraftSlabAndStonecut(consumer, triad.SLAB, material, alts);
        addCraftWallAndStonecut(consumer, triad.WALL, material, alts);
    }

    private void addCraftCustomStoneGroupAndStonecut(Consumer<FinishedRecipe> consumer, CustomStoneGroup group) {
        addCraftTriadAndStonecut(consumer, group.BASE_TRIAD, group.BASE_BLOCK.get());
        addCraftBrickSetAndStonecut(consumer, group.POLISHED_BLOCK, group.POLISHED_TRIAD, group.BASE_BLOCK.get());
        addCraftBrickSetAndStonecut(consumer, group.BRICK_BLOCK, group.BRICK_TRIAD, group.POLISHED_BLOCK.get(), group.BASE_BLOCK.get());
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

    private void addCraftBrickSetAndStonecut(Consumer<FinishedRecipe> consumer, RegistryObject<Block> block, BlockTriad triad, ItemLike material, ItemLike ... alts) {
        addCraftBricksAndStonecut(consumer, block, material, alts);
        // Allow the triad to be created like the main block.
        addCraftTriadAndStonecut(consumer, triad, material, alts);
        // Allow the triad to be created from the block.
        addStonecutting(consumer, triad.STAIR, 1, block.get());
        addStonecutting(consumer, triad.SLAB, 2, block.get());
        addStonecutting(consumer, triad.WALL, 1, block.get());
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

    private void twoByTwoChecker(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeCategory pCategory, ItemLike pResult, ItemLike pUnlockIngredient, ItemLike pOtherIngredient) {
        ShapedRecipeBuilder.shaped(pCategory, pResult, 2)
                .define('A', pUnlockIngredient)
                .define('B', pOtherIngredient)
                .pattern("BA")
                .pattern("AB")
                .unlockedBy(getHasName(Items.QUARTZ), has(Items.QUARTZ))
                .save(pFinishedRecipeConsumer);
    }
}
