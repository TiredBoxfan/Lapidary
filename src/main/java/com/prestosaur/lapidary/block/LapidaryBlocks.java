package com.prestosaur.lapidary.block;

import com.prestosaur.lapidary.Lapidary;
import com.prestosaur.lapidary.item.LapidaryItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class LapidaryBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Lapidary.MODID);

    public static final RegistryObject<RotatedPillarBlock> PETRIFIED_LOG = registerBlock("petrified_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<RotatedPillarBlock> PETRIFIED_WOOD = registerBlock("petrified_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(PETRIFIED_LOG.get())));
    public static final RegistryObject<Block> PETRIFIED_PLANKS = registerBlock("petrified_planks",
            () -> new Block(BlockBehaviour.Properties.copy(PETRIFIED_LOG.get())));
    public static final RegistryObject<StairBlock> PETRIFIED_STAIRS = registerBlock("petrified_stairs",
            () -> new StairBlock(() -> PETRIFIED_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(PETRIFIED_PLANKS.get())));

    public static final RegistryObject<WallBlock> STONE_WALL = registerBlock("stone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<WallBlock> POLISHED_GRANITE_WALL = registerBlock("polished_granite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_GRANITE)));
    public static final RegistryObject<Block> POLISHED_GRANITE_BRICKS = registerBlock("polished_granite_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.POLISHED_GRANITE)));
    public static final BlockTriad POLISHED_GRANITE_BRICK_TRIAD = new BlockTriad(
            LapidaryBlocks.POLISHED_GRANITE_BRICKS, "polished_granite_brick");

    public static final RegistryObject<WallBlock> POLISHED_DIORITE_WALL = registerBlock("polished_diorite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_DIORITE)));
    public static final RegistryObject<Block> POLISHED_DIORITE_BRICKS = registerBlock("polished_diorite_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.POLISHED_DIORITE)));
    public static final BlockTriad POLISHED_DIORITE_BRICK_TRIAD = new BlockTriad(
            LapidaryBlocks.POLISHED_DIORITE_BRICKS, "polished_diorite_brick");

    public static final RegistryObject<WallBlock> POLISHED_ANDESITE_WALL = registerBlock("polished_andesite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_ANDESITE)));
    public static final RegistryObject<Block> POLISHED_ANDESITE_BRICKS = registerBlock("polished_andesite_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.POLISHED_ANDESITE)));
    public static final BlockTriad POLISHED_ANDESITE_BRICK_TRIAD = new BlockTriad(
            LapidaryBlocks.POLISHED_ANDESITE_BRICKS, "polished_andesite_brick");

    public static final RegistryObject<Block> PERIDOTITE = registerBlock("peridotite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRANITE).mapColor(MapColor.COLOR_GREEN)));
    public static final BlockTriad PERIDOTITE_TRIAD = new BlockTriad(
            PERIDOTITE, "peridotite");

    public static final RegistryObject<Block> CRACKED_BRICKS = registerBlock("cracked_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));

    public static final RegistryObject<Block> CRACKED_MUD_BRICKS = registerBlock("cracked_mud_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS)));
    public static final RegistryObject<Block> MOSSY_MUD_BRICKS = registerBlock("mossy_mud_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS)));
    public static final BlockTriad MOSSY_MUD_BRICK_TRIAD = new BlockTriad(
            MOSSY_MUD_BRICKS, "mossy_mud_brick");

    public static final BlockTriad NETHERRACK_TRIAD = new BlockTriad(
            () -> Blocks.NETHERRACK, "netherrack");

    public static final BlockTriad END_STONE_TRIAD = new BlockTriad(
            () -> Blocks.END_STONE, "end_stone");
    public static final RegistryObject<Block> CRACKED_END_STONE_BRICKS = registerBlock("cracked_end_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICKS)));

    public static final RegistryObject<WallBlock> PURPUR_WALL = registerBlock("purpur_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK)));
    public static final RegistryObject<Block> CRACKED_PURPUR_BLOCK = registerBlock("cracked_purpur_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK)));

    public static final RegistryObject<Block> JADE = registerBlock("jade",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRANITE).mapColor(MapColor.COLOR_GREEN)));
    public static final RegistryObject<Block> POLISHED_JADE = registerBlock("polished_jade",
            () -> new Block(BlockBehaviour.Properties.copy(JADE.get())));

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return LapidaryItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
}
