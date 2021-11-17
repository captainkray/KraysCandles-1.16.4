package com.tm.krayscandles.ritual;

import com.tm.api.calemicore.util.Location;
import com.tm.api.calemicore.util.helper.SoundHelper;
import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.tileentity.TileEntityStoneAltarTile;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
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

        SoundHelper.playSoundAtLocation(location, SoundEvents.ENTITY_ITEM_FRAME_REMOVE_ITEM, SoundCategory.BLOCKS, 0.05F, 1.0F);
    }
}
