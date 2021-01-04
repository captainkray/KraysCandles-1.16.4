package com.captainkray.krayscandles.util;

import com.captainkray.krayscandles.init.InitParticles;
import com.captainkray.krayscandles.tileentity.base.ISoulFlame;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ParticleHelper {

    public static void renderFlame(World world, BlockPos pos, double x, double y, double z) {

        Location blockLocation = new Location(world, pos);

        BasicParticleType flameType = ParticleTypes.FLAME;

        if (blockLocation.getTileEntity() instanceof ISoulFlame) {
            ISoulFlame soulFlame = (ISoulFlame) blockLocation.getTileEntity();

            if (soulFlame.getEntityTypeFromSoul() != null) {
                flameType = InitParticles.SOUL_FLAME_NORMAL.get();
            }
        }

        world.addParticle(flameType, x, y, z, 0.0D, 0.0D, 0.0D);
        renderSmoke(world, x, y, z);
    }

    public static void renderSmoke(World world, double x, double y, double z) {
        world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
    }
}
