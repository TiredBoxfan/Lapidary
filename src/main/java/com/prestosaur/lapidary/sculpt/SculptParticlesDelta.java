package com.prestosaur.lapidary.sculpt;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

// Borrowed from BrushItem.
public record SculptParticlesDelta(double xd, double yd, double zd) {
    private static final double ALONG_SIDE_DELTA = 1.0D;
    private static final double OUT_FROM_SIDE_DELTA = 0.1D;

    public static SculptParticlesDelta fromDirection(Vec3 pPos, Direction pDirection) {

        return switch (pDirection) {
            case DOWN, UP -> new SculptParticlesDelta(pPos.z(), 0.0D, -pPos.x());
            case NORTH -> new SculptParticlesDelta(ALONG_SIDE_DELTA, 0.0D, -OUT_FROM_SIDE_DELTA);
            case SOUTH -> new SculptParticlesDelta(-ALONG_SIDE_DELTA, 0.0D, OUT_FROM_SIDE_DELTA);
            case WEST -> new SculptParticlesDelta(-OUT_FROM_SIDE_DELTA, 0.0D, -ALONG_SIDE_DELTA);
            case EAST -> new SculptParticlesDelta(OUT_FROM_SIDE_DELTA, 0.0D, ALONG_SIDE_DELTA);
            default -> throw new IncompatibleClassChangeError();
        };
    }
}
