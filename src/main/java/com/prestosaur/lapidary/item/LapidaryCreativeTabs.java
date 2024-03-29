package com.prestosaur.lapidary.item;

import com.prestosaur.lapidary.Lapidary;
import com.prestosaur.lapidary.block.BlockTriad;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Lapidary.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LapidaryCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Lapidary.MODID);

    public static RegistryObject<CreativeModeTab> LAPIDARY_BUILDING_BLOCKS = CREATIVE_MODE_TABS.register("building_blocks", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(LapidaryBlocks.CRACKED_BRICKS.get()))
            .title(Component.translatable("itemGroup." + Lapidary.MODID + ".building_blocks"))
            .displayItems((features, output) -> {
                output.accept(LapidaryBlocks.PETRIFIED_LOG.get());
                output.accept(LapidaryBlocks.PETRIFIED_WOOD.get());
                output.accept(LapidaryBlocks.PETRIFIED_PLANKS.get());
                output.accept(LapidaryBlocks.PETRIFIED_STAIRS.get());

                output.accept(LapidaryBlocks.STONE_WALL.get());

                output.accept(LapidaryBlocks.POLISHED_GRANITE_WALL.get());
                output.accept(LapidaryBlocks.POLISHED_GRANITE_BRICKS.get());
                output.accept(LapidaryBlocks.POLISHED_GRANITE_BRICK_TRIAD.STAIR.get());
                output.accept(LapidaryBlocks.POLISHED_GRANITE_BRICK_TRIAD.SLAB.get());
                output.accept(LapidaryBlocks.POLISHED_GRANITE_BRICK_TRIAD.WALL.get());

                output.accept(LapidaryBlocks.POLISHED_DIORITE_WALL.get());
                output.accept(LapidaryBlocks.POLISHED_DIORITE_BRICKS.get());
                output.accept(LapidaryBlocks.POLISHED_DIORITE_BRICK_TRIAD.STAIR.get());
                output.accept(LapidaryBlocks.POLISHED_DIORITE_BRICK_TRIAD.SLAB.get());
                output.accept(LapidaryBlocks.POLISHED_DIORITE_BRICK_TRIAD.WALL.get());

                output.accept(LapidaryBlocks.POLISHED_ANDESITE_WALL.get());
                output.accept(LapidaryBlocks.POLISHED_ANDESITE_BRICKS.get());
                output.accept(LapidaryBlocks.POLISHED_ANDESITE_BRICK_TRIAD.STAIR.get());
                output.accept(LapidaryBlocks.POLISHED_ANDESITE_BRICK_TRIAD.SLAB.get());
                output.accept(LapidaryBlocks.POLISHED_ANDESITE_BRICK_TRIAD.WALL.get());

                output.accept(LapidaryBlocks.PERIDOTITE.get());
                output.accept(LapidaryBlocks.PERIDOTITE_TRIAD.STAIR.get());
                output.accept(LapidaryBlocks.PERIDOTITE_TRIAD.SLAB.get());
                output.accept(LapidaryBlocks.PERIDOTITE_TRIAD.WALL.get());
                output.accept(LapidaryBlocks.POLISHED_PERIDOTITE.get());
                output.accept(LapidaryBlocks.POLISHED_PERIDOTITE_TRIAD.STAIR.get());
                output.accept(LapidaryBlocks.POLISHED_PERIDOTITE_TRIAD.SLAB.get());
                output.accept(LapidaryBlocks.POLISHED_PERIDOTITE_TRIAD.WALL.get());

                output.accept(LapidaryBlocks.CRACKED_BRICKS.get());

                output.accept(LapidaryBlocks.MOSSY_MUD_BRICKS.get());
                output.accept(LapidaryBlocks.MOSSY_MUD_BRICK_TRIAD.STAIR.get());
                output.accept(LapidaryBlocks.MOSSY_MUD_BRICK_TRIAD.SLAB.get());
                output.accept(LapidaryBlocks.MOSSY_MUD_BRICK_TRIAD.WALL.get());
                output.accept(LapidaryBlocks.CRACKED_MUD_BRICKS.get());

                output.accept(LapidaryBlocks.NETHERRACK_TRIAD.STAIR.get());
                output.accept(LapidaryBlocks.NETHERRACK_TRIAD.SLAB.get());
                output.accept(LapidaryBlocks.NETHERRACK_TRIAD.WALL.get());

                output.accept(LapidaryBlocks.CRACKED_END_STONE_BRICKS.get());
                output.accept(LapidaryBlocks.END_STONE_TRIAD.STAIR.get());
                output.accept(LapidaryBlocks.END_STONE_TRIAD.SLAB.get());
                output.accept(LapidaryBlocks.END_STONE_TRIAD.WALL.get());

                output.accept(LapidaryBlocks.PURPUR_WALL.get());
                output.accept(LapidaryBlocks.CRACKED_PURPUR_BLOCK.get());

                output.accept(LapidaryBlocks.JADE.get());
                output.accept(LapidaryBlocks.POLISHED_JADE.get());
            }).build());

    @SubscribeEvent
    public static void buildCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();
        if (tab == CreativeModeTabs.BUILDING_BLOCKS) {
            putBefore(event, LapidaryBlocks.PETRIFIED_LOG.get(), Items.STONE);
            putAfterChain(event, LapidaryBlocks.PETRIFIED_LOG.get(),
                    LapidaryBlocks.PETRIFIED_WOOD.get(),
                    LapidaryBlocks.PETRIFIED_PLANKS.get(),
                    LapidaryBlocks.PETRIFIED_STAIRS.get(),
                    Items.PETRIFIED_OAK_SLAB);

            putAfter(event, LapidaryBlocks.STONE_WALL.get(), Items.STONE_SLAB);

            putAfterChain(event, Items.POLISHED_GRANITE_SLAB,
                    LapidaryBlocks.POLISHED_GRANITE_WALL.get(),
                    LapidaryBlocks.POLISHED_GRANITE_BRICKS.get());
            putTriadAfter(event, LapidaryBlocks.POLISHED_GRANITE_BRICK_TRIAD, LapidaryBlocks.POLISHED_GRANITE_BRICKS.get());

            putAfterChain(event, Items.POLISHED_DIORITE_SLAB,
                    LapidaryBlocks.POLISHED_DIORITE_WALL.get(),
                    LapidaryBlocks.POLISHED_DIORITE_BRICKS.get());
            putTriadAfter(event, LapidaryBlocks.POLISHED_DIORITE_BRICK_TRIAD, LapidaryBlocks.POLISHED_DIORITE_BRICKS.get());

            putAfterChain(event, Items.POLISHED_ANDESITE_SLAB,
                    LapidaryBlocks.POLISHED_ANDESITE_WALL.get(),
                    LapidaryBlocks.POLISHED_ANDESITE_BRICKS.get());
            putTriadAfter(event, LapidaryBlocks.POLISHED_ANDESITE_BRICK_TRIAD, LapidaryBlocks.POLISHED_ANDESITE_BRICKS.get());

            putAfterChain(event, LapidaryBlocks.POLISHED_GRANITE_BRICK_TRIAD.WALL.get(),
                    LapidaryBlocks.PERIDOTITE.get(),
                    LapidaryBlocks.POLISHED_PERIDOTITE.get());
            putTriadAfter(event, LapidaryBlocks.PERIDOTITE_TRIAD, LapidaryBlocks.PERIDOTITE.get());
            putTriadAfter(event, LapidaryBlocks.POLISHED_PERIDOTITE_TRIAD, LapidaryBlocks.POLISHED_PERIDOTITE.get());

            putAfter(event, LapidaryBlocks.CRACKED_BRICKS.get(), Items.BRICKS);

            putAfter(event, LapidaryBlocks.CRACKED_MUD_BRICKS.get(), Items.MUD_BRICKS);
            putAfterChain(event, Items.MUD_BRICK_WALL,
                    LapidaryBlocks.MOSSY_MUD_BRICKS.get());
            putTriadAfter(event, LapidaryBlocks.MOSSY_MUD_BRICK_TRIAD, LapidaryBlocks.MOSSY_MUD_BRICKS.get());

            putTriadAfter(event, LapidaryBlocks.NETHERRACK_TRIAD, Items.NETHERRACK);

            putTriadAfter(event, LapidaryBlocks.END_STONE_TRIAD, Items.END_STONE);
            putAfter(event, LapidaryBlocks.CRACKED_END_STONE_BRICKS.get(), Items.END_STONE_BRICKS);

            putAfter(event, LapidaryBlocks.PURPUR_WALL.get(), Blocks.PURPUR_SLAB);
            putAfter(event, LapidaryBlocks.CRACKED_PURPUR_BLOCK.get(), Items.PURPUR_BLOCK);

            putAfterChain(event, Items.SMOOTH_QUARTZ_SLAB,
                    LapidaryBlocks.JADE.get(),
                    LapidaryBlocks.POLISHED_JADE.get());
        }
    }

    private static void putAfter(BuildCreativeModeTabContentsEvent event, ItemLike item, ItemLike after) {
        event.getEntries().putAfter(new ItemStack(after), new ItemStack(item), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    private static void putAfterChain(BuildCreativeModeTabContentsEvent event, ItemLike origin, ItemLike... items) {
        ItemLike last = origin;
        for (ItemLike i : items) {
            event.getEntries().putAfter(new ItemStack(last), new ItemStack(i), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            last = i;
        }
    }

    private static void putBefore(BuildCreativeModeTabContentsEvent event, ItemLike item, ItemLike before) {
        event.getEntries().putBefore(new ItemStack(before), new ItemStack(item), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    private static void putTriadAfter(BuildCreativeModeTabContentsEvent event, BlockTriad triad, ItemLike start) {
        putAfterChain(event, start, triad.STAIR.get(), triad.SLAB.get(), triad.WALL.get());
    }
}
