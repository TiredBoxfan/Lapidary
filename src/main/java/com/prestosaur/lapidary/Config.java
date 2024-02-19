package com.prestosaur.lapidary;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = Lapidary.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue DISABLE_SILK_TOUCH_SCULPT = BUILDER
            .comment("Whether to disable right-click sculpting when a tool has silk touch.")
            .define("disableSilkTouchSculpt", true);

    private static final ForgeConfigSpec.IntValue SCULPT_TIME = BUILDER
            .comment("How long a right-click with a tool needs to last without the Sculpting enchantment.")
            .defineInRange("sculptTime", 16, 0, Integer.MAX_VALUE);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean disableSilkTouchSculpt;
    public static int sculptTime;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        disableSilkTouchSculpt = DISABLE_SILK_TOUCH_SCULPT.get();
        sculptTime = SCULPT_TIME.get();
    }
}
