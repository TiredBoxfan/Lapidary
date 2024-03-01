package com.prestosaur.lapidary.blockstate;

import com.prestosaur.lapidary.block.diode.DestabilizerMode;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class LapidaryBlockStateProperties {
    public static final EnumProperty<DestabilizerMode> MODE_DESTABILIZER = EnumProperty.create("mode", DestabilizerMode.class);
}
