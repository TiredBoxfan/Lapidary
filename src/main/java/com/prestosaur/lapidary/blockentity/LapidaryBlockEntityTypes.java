package com.prestosaur.lapidary.blockentity;

import com.prestosaur.lapidary.Lapidary;
import com.prestosaur.lapidary.block.LapidaryBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LapidaryBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Lapidary.MODID);

    public static final RegistryObject<BlockEntityType<DestabilizerBlockEntity>> DESTABILIZER = BLOCK_ENTITY_TYPES.register("destabilizer", () ->
            BlockEntityType.Builder.of(DestabilizerBlockEntity::new, LapidaryBlocks.DESTABILIZER_BLOCK.get()).build(null));
}
