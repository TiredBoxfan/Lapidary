package com.prestosaur.lapidary.enchantment;

import com.prestosaur.lapidary.Lapidary;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LapidaryEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Lapidary.MODID);

    public static final RegistryObject<Enchantment> SCULPT = ENCHANTMENTS.register("sculpting", SculptEnchantment::new);
}
