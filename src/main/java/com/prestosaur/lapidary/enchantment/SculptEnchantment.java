package com.prestosaur.lapidary.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.UntouchingEnchantment;

public class SculptEnchantment extends Enchantment {
    protected SculptEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[] { EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND });
    }

    public boolean checkCompatibility(Enchantment pEnch) {
        return pEnch instanceof UntouchingEnchantment ? false : super.checkCompatibility(pEnch);
    }
}
