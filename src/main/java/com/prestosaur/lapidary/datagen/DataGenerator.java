package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.Lapidary;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Lapidary.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerator
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        net.minecraft.data.DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(true, new LapidaryBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(true, new LapidaryBlockTagsProvider(packOutput, lookupProvider, Lapidary.MODID, existingFileHelper));

        generator.addProvider(true, new LootTableProvider(
                packOutput,
                Set.of(),
                List.of(new LootTableProvider.SubProviderEntry(
                        LapidaryBlockLootTables::new,
                        LootContextParamSets.BLOCK))));
    }
}
