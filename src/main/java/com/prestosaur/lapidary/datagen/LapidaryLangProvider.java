package com.prestosaur.lapidary.datagen;

import com.prestosaur.lapidary.Lapidary;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import com.prestosaur.lapidary.block.groups.BlockTriad;
import com.prestosaur.lapidary.block.groups.CustomStoneGroup;
import com.prestosaur.lapidary.enchantment.LapidaryEnchantments;
import com.prestosaur.lapidary.item.LapidaryCreativeTabs;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.data.LanguageProvider;

public class LapidaryLangProvider extends LanguageProvider {
    public LapidaryLangProvider(PackOutput output, String locale) {
        super(output, Lapidary.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        this.addCreativeTab(LapidaryCreativeTabs.LAPIDARY_BUILDING_BLOCKS.get(), "Lapidary Building Blocks");

        this.addEnchantment(LapidaryEnchantments.SCULPT, "Sculpting");

        this.addBlock(LapidaryBlocks.PETRIFIED_LOG, "Petrified Log");
        this.addBlock(LapidaryBlocks.PETRIFIED_WOOD, "Petrified Wood");
        this.addBlock(LapidaryBlocks.PETRIFIED_PLANKS, "Petrified Planks");
        this.addBlock(LapidaryBlocks.PETRIFIED_STAIRS, "Petrified Stairs");

        this.addBlock(LapidaryBlocks.STONE_WALL, "Stone Wall");

        this.addBlock(LapidaryBlocks.POLISHED_GRANITE_WALL, "Polished Granite Wall");
        this.addBlock(LapidaryBlocks.POLISHED_GRANITE_BRICKS, "Polished Granite Bricks");
        this.addTriad(LapidaryBlocks.POLISHED_GRANITE_BRICK_TRIAD, "Polished Granite Brick");

        this.addBlock(LapidaryBlocks.POLISHED_DIORITE_WALL, "Polished Diorite Wall");
        this.addBlock(LapidaryBlocks.POLISHED_DIORITE_BRICKS, "Polished Diorite Bricks");
        this.addTriad(LapidaryBlocks.POLISHED_DIORITE_BRICK_TRIAD, "Polished Diorite Brick");

        this.addBlock(LapidaryBlocks.POLISHED_ANDESITE_WALL, "Polished Andesite Wall");
        this.addBlock(LapidaryBlocks.POLISHED_ANDESITE_BRICKS, "Polished Andesite Bricks");
        this.addTriad(LapidaryBlocks.POLISHED_ANDESITE_BRICK_TRIAD, "Polished Andesite Brick");

        this.addCustomStoneGroup(LapidaryBlocks.RHYOLITE_GROUP, "Rhyolite");

        this.addBlock(LapidaryBlocks.CRACKED_BRICKS, "Cracked Bricks");

        this.addBlock(LapidaryBlocks.MOSSY_MUD_BRICKS, "Mossy Mud Bricks");
        this.addTriad(LapidaryBlocks.MOSSY_MUD_BRICK_TRIAD, "Mossy Mud Brick");
        this.addBlock(LapidaryBlocks.CRACKED_MUD_BRICKS, "Cracked Mud Bricks");

        this.addTriad(LapidaryBlocks.NETHERRACK_TRIAD, "Netherrack");

        this.addTriad(LapidaryBlocks.END_STONE_TRIAD, "End Stone");
        this.addBlock(LapidaryBlocks.CRACKED_END_STONE_BRICKS, "Cracked End Stone Bricks");

        this.addBlock(LapidaryBlocks.PURPUR_WALL, "Purpur Wall");
        this.addBlock(LapidaryBlocks.CRACKED_PURPUR_BLOCK, "Cracked Purpur Block");

        this.addBlock(LapidaryBlocks.JADE, "Jade");
        this.addBlock(LapidaryBlocks.POLISHED_JADE, "Polished Jade");
    }

    protected void addCreativeTab(CreativeModeTab tab, String name) {
        this.add(tab.getDisplayName().getString(), name);
    }

    protected void addTriad(BlockTriad triad, String baseName) {
        this.addBlock(triad.SLAB, baseName + " Slab");
        this.addBlock(triad.STAIR, baseName + " Stairs");
        this.addBlock(triad.WALL, baseName + " Wall");
    }

    protected void addCustomStoneGroup(CustomStoneGroup group, String baseName) {
        this.addBlock(group.BASE_BLOCK, baseName);
        this.addTriad(group.BASE_TRIAD, baseName);
        this.addBlock(group.POLISHED_BLOCK, "Polished " + baseName);
        this.addTriad(group.POLISHED_TRIAD, "Polished " + baseName);
        this.addBlock(group.BRICK_BLOCK, "Polished " + baseName + " Bricks");
        this.addTriad(group.BRICK_TRIAD, "Polished " + baseName + " Brick");
    }
}
