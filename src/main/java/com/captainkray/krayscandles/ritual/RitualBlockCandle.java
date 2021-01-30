package com.captainkray.krayscandles.ritual;

import com.captainkray.krayscandles.block.base.BlockCandleBase;
import com.captainkray.krayscandles.util.Location;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualBlockCandle extends RitualBlock {

    public RitualBlockCandle(Block block, int x, int y, int z) {
        super(block, x, y, z);
    }

    public RitualBlockCandle(Block block) {
        super(block, 0, 0, 0);
    }

    public RitualBlockCandle(Block block, BlockPos offset) {
        super(block, offset);
    }

    public RitualBlockCandle rotate(Rotation rotation) {
        return new RitualBlockCandle(getState().getBlock(), getOffset().rotate(rotation));
    }

    @Override
    public boolean isValid(World world, BlockPos pos) {

        Location location = new Location(world, pos.add(getOffset()));

        if (location.getBlock() instanceof BlockCandleBase) {
            return location.getBlockState().get(BlockCandleBase.LIT);
        }

        return false;
    }

    @Override
    public void onRitualComplete(World world, BlockPos pos, PlayerEntity player) {

        Location location = getLocation(world, pos);

        if (location.getBlock() instanceof BlockCandleBase) {
            BlockCandleBase.setLit(location, false);
        }

        world.addParticle(ParticleTypes.LARGE_SMOKE, location.x + 0.5F, location.y + 0.4F, location.z + 0.5F, 0, 0, 0);

        player.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 0.05F, 1);
    }
}
