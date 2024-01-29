package com.prestosaur.lapidary.block;

import com.prestosaur.lapidary.Lapidary;
import com.prestosaur.lapidary.item.LapidaryItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class LapidaryBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Lapidary.MODID);

    public static final RegistryObject<Block> CRACKED_END_STONE_BRICKS = registerBlock("cracked_end_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE_BRICKS)));

    public static final RegistryObject<StairBlock> NETHERRACK_STAIRS = registerBlock("netherrack_stairs",
            () -> new StairBlock(Blocks.NETHERRACK.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.NETHERRACK)));
    public static final RegistryObject<SlabBlock> NETHERRACK_SLAB = registerBlock("netherrack_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK)));

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
