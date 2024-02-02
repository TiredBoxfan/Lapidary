package com.prestosaur.lapidary.block;

import com.prestosaur.lapidary.Lapidary;
import com.prestosaur.lapidary.item.LapidaryItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class LapidaryBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Lapidary.MODID);

    public static final RegistryObject<WallBlock> STONE_WALL = registerBlock("stone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<WallBlock> POLISHED_GRANITE_WALL = registerBlock("polished_granite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_GRANITE)));
    public static final RegistryObject<Block> POLISHED_GRANITE_BRICKS = registerBlock("polished_granite_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.POLISHED_GRANITE)));
    public static final RegistryObject<StairBlock> POLISHED_GRANITE_BRICK_STAIRS = registerBlock("polished_granite_brick_stairs",
            () -> new StairBlock(POLISHED_GRANITE_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(POLISHED_GRANITE_BRICKS.get())));
    public static final RegistryObject<SlabBlock> POLISHED_GRANITE_BRICK_SLAB = registerBlock("polished_granite_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(POLISHED_GRANITE_BRICKS.get())));
    public static final RegistryObject<WallBlock> POLISHED_GRANITE_BRICK_WALL = registerBlock("polished_granite_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(POLISHED_GRANITE_BRICKS.get())));

    public static final RegistryObject<WallBlock> POLISHED_DIORITE_WALL = registerBlock("polished_diorite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_DIORITE)));
    public static final RegistryObject<Block> POLISHED_DIORITE_BRICKS = registerBlock("polished_diorite_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.POLISHED_DIORITE)));
    public static final RegistryObject<StairBlock> POLISHED_DIORITE_BRICK_STAIRS = registerBlock("polished_diorite_brick_stairs",
            () -> new StairBlock(POLISHED_DIORITE_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(POLISHED_DIORITE_BRICKS.get())));
    public static final RegistryObject<SlabBlock> POLISHED_DIORITE_BRICK_SLAB = registerBlock("polished_diorite_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(POLISHED_DIORITE_BRICKS.get())));
    public static final RegistryObject<WallBlock> POLISHED_DIORITE_BRICK_WALL = registerBlock("polished_diorite_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(POLISHED_DIORITE_BRICKS.get())));

    public static final RegistryObject<WallBlock> POLISHED_ANDESITE_WALL = registerBlock("polished_andesite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_ANDESITE)));
    public static final RegistryObject<Block> POLISHED_ANDESITE_BRICKS = registerBlock("polished_andesite_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.POLISHED_ANDESITE)));
    public static final RegistryObject<StairBlock> POLISHED_ANDESITE_BRICK_STAIRS = registerBlock("polished_andesite_brick_stairs",
            () -> new StairBlock(POLISHED_ANDESITE_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(POLISHED_ANDESITE_BRICKS.get())));
    public static final RegistryObject<SlabBlock> POLISHED_ANDESITE_BRICK_SLAB = registerBlock("polished_andesite_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(POLISHED_ANDESITE_BRICKS.get())));
    public static final RegistryObject<WallBlock> POLISHED_ANDESITE_BRICK_WALL = registerBlock("polished_andesite_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(POLISHED_ANDESITE_BRICKS.get())));

    public static final RegistryObject<Block> CRACKED_BRICKS = registerBlock("cracked_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));

    public static final RegistryObject<Block> MOSSY_MUD_BRICKS = registerBlock("mossy_mud_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS)));
    public static final RegistryObject<StairBlock> MOSSY_MUD_BRICK_STAIRS = registerBlock("mossy_mud_brick_stairs",
            () -> new StairBlock(MOSSY_MUD_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(MOSSY_MUD_BRICKS.get())));
    public static final RegistryObject<SlabBlock> MOSSY_MUD_BRICK_SLAB = registerBlock("mossy_mud_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(MOSSY_MUD_BRICKS.get())));
    public static final RegistryObject<WallBlock> MOSSY_MUD_BRICK_WALL = registerBlock("mossy_mud_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(MOSSY_MUD_BRICKS.get())));
    public static final RegistryObject<Block> CRACKED_MUD_BRICKS = registerBlock("cracked_mud_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS)));

    public static final RegistryObject<StairBlock> NETHERRACK_STAIRS = registerBlock("netherrack_stairs",
            () -> new StairBlock(Blocks.NETHERRACK.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.NETHERRACK)));
    public static final RegistryObject<SlabBlock> NETHERRACK_SLAB = registerBlock("netherrack_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK)));
    public static final RegistryObject<WallBlock> NETHERRACK_WALL = registerBlock("netherrack_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK)));

    public static final RegistryObject<StairBlock> END_STONE_STAIRS = registerBlock("end_stone_stairs",
            () -> new StairBlock(Blocks.END_STONE.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.END_STONE)));
    public static final RegistryObject<SlabBlock> END_STONE_SLAB = registerBlock("end_stone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE)));
    public static final RegistryObject<WallBlock> END_STONE_WALL = registerBlock("end_stone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE)));
    public static final RegistryObject<Block> CRACKED_END_STONE_BRICKS = registerBlock("cracked_end_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICKS)));

    public static final RegistryObject<WallBlock> PURPUR_WALL = registerBlock("purpur_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK)));
    public static final RegistryObject<Block> CRACKED_PURPUR_BLOCK = registerBlock("cracked_purpur_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.PURPUR_BLOCK)));

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block)
    {
        return LapidaryItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
}
