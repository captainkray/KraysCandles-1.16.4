package com.captainkray.krayscandles.ritual;

import com.captainkray.krayscandles.init.InitItems;
import com.captainkray.krayscandles.tileentity.TileEntityStoneAltarTile;
import com.captainkray.krayscandles.util.Location;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class RitualBlockAltar extends RitualBlock {

    public RitualBlockAltar(int x, int y, int z) {
        super(InitItems.STONE_ALTAR_TILE.get(), x, y, z);
    }

    public RitualBlockAltar() {
        super(InitItems.STONE_ALTAR_TILE.get(), 0, 0, 0);
    }

    public RitualBlockAltar(Block block, BlockPos offset) {
        super(block, offset);
    }

    public RitualBlockAltar rotate(Rotation rotation) {
        return new RitualBlockAltar(getState().getBlock(), getOffset().rotate(rotation));
    }

    @Override
    public void onRitualComplete(World world, BlockPos pos, PlayerEntity player) {

        Random rand = new Random();

        Location location = getLocation(world, pos);

        if (location.getTileEntity() instanceof TileEntityStoneAltarTile) {
            ((TileEntityStoneAltarTile) location.getTileEntity()).takeRitualStack();
        }

        for (int i = 0; i < 5; i++) {
            world.addParticle(ParticleTypes.SOUL, location.x + rand.nextDouble(), location.y + 0.2F, location.z + rand.nextDouble(), 0, 0, 0);
            world.addParticle(ParticleTypes.SMOKE, location.x + rand.nextDouble(), location.y + 0.2F, location.z + rand.nextDouble(), 0, 0, 0);
        }

        location.playSound(SoundEvents.ENTITY_ITEM_FRAME_REMOVE_ITEM, 0.05F, 1);
    }
}
