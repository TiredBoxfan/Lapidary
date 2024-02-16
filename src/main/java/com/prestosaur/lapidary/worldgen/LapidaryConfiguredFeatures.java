package com.prestosaur.lapidary.worldgen;

import com.prestosaur.lapidary.Lapidary;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class LapidaryConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PERIDOTITE = registerKey("ore_peridotite");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);

        register(context, ORE_PERIDOTITE, Feature.ORE, new OreConfiguration(stoneReplaceable, LapidaryBlocks.PERIDOTITE.get().defaultBlockState(), 64));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Lapidary.MODID, name));
    }

    private static <C extends FeatureConfiguration, FC extends Feature<C>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, FC feature, C configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
