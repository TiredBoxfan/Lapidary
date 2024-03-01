package com.prestosaur.lapidary.block.diode;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum DestabilizerMode implements StringRepresentable {
    BOUNDS("bounds"), // Sides act as bounds, regardless of input.
    CHOICE("choice"); // Sides act as options, regardless of input.

    private final String name;

    private DestabilizerMode(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public @NotNull String getSerializedName() {
        return this.name;
    }
}
