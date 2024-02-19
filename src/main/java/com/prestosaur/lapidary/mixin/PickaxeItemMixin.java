package com.prestosaur.lapidary.mixin;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PickaxeItem.class)
public class PickaxeItemMixin extends SculptMixin {
    public PickaxeItemMixin(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext pContext) {
        var result = lapidary$useOnSculpt(pContext);
        if (result != null)
            return result;
        return InteractionResult.PASS;
    }
}
