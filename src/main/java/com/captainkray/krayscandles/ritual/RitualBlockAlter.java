package com.captainkray.krayscandles.ritual;

import com.captainkray.krayscandles.init.InitItems;
import com.captainkray.krayscandles.tileentity.TileEntityStoneAlterTile;
import com.captainkray.krayscandles.util.Location;
import net.minecraft.block.Block;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualBlockAlter extends RitualBlock {

    public RitualBlockAlter(int x, int y, int z) {
        super(InitItems.STONE_ALTAR_TILE.get(), x, y, z);
    }

    public RitualBlockAlter() {
        super(InitItems.STONE_ALTAR_TILE.get(), 0, 0, 0);
    }

    public RitualBlockAlter(Block block, BlockPos offset) {
        super(block, offset);
    }

    public RitualBlockAlter rotate(Rotation rotation) {
        return new RitualBlockAlter(getState().getBlock(), getOffset().rotate(rotation));
    }

    @Override
    public void onRitualComplete(World world, BlockPos pos) {

        Location location = getLocation(world, pos);

        if (location.getTileEntity() instanceof TileEntityStoneAlterTile) {
            ((TileEntityStoneAlterTile) location.getTileEntity()).takeRitualStack();
        }
    }
}
