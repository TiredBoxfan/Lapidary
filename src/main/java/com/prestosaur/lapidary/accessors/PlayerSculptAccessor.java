package com.prestosaur.lapidary.accessors;

import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;

public interface PlayerSculptAccessor {
    public BlockPos getLastSculptLocation();
    public void setLastSculptLocation(BlockPos blockpos);
}
