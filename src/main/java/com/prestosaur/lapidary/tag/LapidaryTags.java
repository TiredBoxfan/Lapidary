package com.prestosaur.lapidary.tag;

import com.prestosaur.lapidary.Lapidary;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class LapidaryTags
{
    public static class Blocks
    {
        public static final TagKey<Block> BARK_BLOCKS = tag("bark_blocks");
        public static final TagKey<Block> PETRIFIABLE = tag("petrifiable");

        private static TagKey<Block> tag(String name)
        {
            return BlockTags.create(new ResourceLocation(Lapidary.MODID, name));
        }
    }
}
