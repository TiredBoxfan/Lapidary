package com.prestosaur.lapidary.mixin;

import com.prestosaur.lapidary.accessors.PlayerSculptAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Player.class)
public class PlayerMixin implements PlayerSculptAccessor {
    @Unique
    @Nullable
    public BlockPos lapidary$lastSculptLocation = null;

    @Override
    public BlockPos getLastSculptLocation() {
        return lapidary$lastSculptLocation;
    }

    @Override
    public void setLastSculptLocation(BlockPos blockpos) {
        lapidary$lastSculptLocation = blockpos;
    }
}
