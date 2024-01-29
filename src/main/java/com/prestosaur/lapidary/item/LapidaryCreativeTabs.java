package com.prestosaur.lapidary.item;

import com.prestosaur.lapidary.Lapidary;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
                output.accept(LapidaryBlocks.CRACKED_END_STONE_BRICKS.get());
                output.accept(LapidaryBlocks.NETHERRACK_STAIRS.get());
                output.accept(LapidaryBlocks.NETHERRACK_SLAB.get());
            }).build());

    @SubscribeEvent
    public static void buildCreativeTabs(BuildCreativeModeTabContentsEvent event)
    {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();
        if(tab == CreativeModeTabs.BUILDING_BLOCKS)
        {
            event.getEntries().putAfter(new ItemStack(Items.END_STONE_BRICKS), new ItemStack(LapidaryBlocks.CRACKED_END_STONE_BRICKS.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(Items.NETHERRACK), new ItemStack(LapidaryBlocks.NETHERRACK_STAIRS.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(LapidaryBlocks.NETHERRACK_STAIRS.get()), new ItemStack(LapidaryBlocks.NETHERRACK_SLAB.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}
