package com.captainkray.krayscandles.util;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;

public class ParticleHelper {

    public static void renderFlame(World world, double x, double y, double z) {
        world.addParticle(ParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
        renderSmoke(world, x, y, z);
    }

    public static void renderSmoke(World world, double x, double y, double z) {
        world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
    }
}
