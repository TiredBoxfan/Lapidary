package com.prestosaur.lapidary.worldgen;

import com.prestosaur.lapidary.Lapidary;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class LapidaryBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_ORE_PERIDOTITE_UPPER = registerKey("add_ore_peridotite_upper");
    public static final ResourceKey<BiomeModifier> ADD_ORE_PERIDOTITE_LOWER = registerKey("add_ore_peridotite_lower");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_ORE_PERIDOTITE_UPPER, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(LapidaryPlacedFeatures.ORE_PERIDOTITE_UPPER)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORE_PERIDOTITE_LOWER, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(LapidaryPlacedFeatures.ORE_PERIDOTITE_LOWER)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    public static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Lapidary.MODID, name));
    }
}
