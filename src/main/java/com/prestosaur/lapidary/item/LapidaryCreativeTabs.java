package com.prestosaur.lapidary.item;

import com.prestosaur.lapidary.Lapidary;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Lapidary.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LapidaryCreativeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Lapidary.MODID);

    public static RegistryObject<CreativeModeTab> LAPIDARY_BUILDING_BLOCKS = CREATIVE_MODE_TABS.register("building_blocks", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(LapidaryBlocks.CRACKED_END_STONE_BRICKS.get()))
            .title(Component.translatable("itemGroup." + Lapidary.MODID + ".building_blocks"))
            .displayItems((features, output) -> {
                output.accept(LapidaryBlocks.NETHERRACK_STAIRS.get());
                output.accept(LapidaryBlocks.NETHERRACK_SLAB.get());

                output.accept(LapidaryBlocks.END_STONE_STAIRS.get());
                output.accept(LapidaryBlocks.END_STONE_SLAB.get());
                output.accept(LapidaryBlocks.CRACKED_END_STONE_BRICKS.get());
            }).build());

    @SubscribeEvent
    public static void buildCreativeTabs(BuildCreativeModeTabContentsEvent event)
    {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();
        if(tab == CreativeModeTabs.BUILDING_BLOCKS)
        {
            putAfter(event, LapidaryBlocks.NETHERRACK_STAIRS.get(), Items.NETHERRACK);
            putAfter(event, LapidaryBlocks.NETHERRACK_SLAB.get(), LapidaryBlocks.NETHERRACK_STAIRS.get());

            putAfter(event, LapidaryBlocks.END_STONE_STAIRS.get(), Items.END_STONE);
            putAfter(event, LapidaryBlocks.END_STONE_SLAB.get(), LapidaryBlocks.END_STONE_STAIRS.get());
            putAfter(event, LapidaryBlocks.CRACKED_END_STONE_BRICKS.get(), Items.END_STONE_BRICKS);
        }
    }

    private static void putAfter(BuildCreativeModeTabContentsEvent event, ItemLike item, ItemLike after)
    {
        event.getEntries().putAfter(new ItemStack(after), new ItemStack(item), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
}
