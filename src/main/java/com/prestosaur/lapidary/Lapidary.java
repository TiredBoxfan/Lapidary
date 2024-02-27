package com.prestosaur.lapidary;

import com.mojang.logging.LogUtils;
import com.prestosaur.lapidary.block.LapidaryBlockEntityTypes;
import com.prestosaur.lapidary.block.LapidaryBlockStateProperties;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import com.prestosaur.lapidary.enchantment.LapidaryEnchantments;
import com.prestosaur.lapidary.item.LapidaryCreativeTabs;
import com.prestosaur.lapidary.item.LapidaryItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Lapidary.MODID)
public class Lapidary {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "lapidary";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public Lapidary() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        LapidaryItems.ITEMS.register(modEventBus);
        LapidaryCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        LapidaryBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
        LapidaryBlocks.BLOCKS.register(modEventBus);
        LapidaryEnchantments.ENCHANTMENTS.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
