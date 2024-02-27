package com.prestosaur.lapidary.block;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum DestabilizerMode implements StringRepresentable {
    UNBOUND("unbound"),
    BOUND("bound");

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
