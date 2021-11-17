package com.tm.krayscandles.ritual;

import com.tm.api.calemicore.util.Location;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualBlock {

    private final BlockState state;
    private final BlockPos offset;

    public RitualBlock(Block block) {
        this(block, new BlockPos(0, 0, 0));
    }

    public RitualBlock(Block block, int x, int y, int z) {
        this(block, new BlockPos(x, y, z));
    }

    public RitualBlock(Block block, BlockPos offset) {
        this.state = block.getDefaultState();
        this.offset = offset;
    }

    public BlockState getState() {
        return state;
    }

    public BlockPos getOffset() {
        return offset;
    }

    public Location getLocation(World world, BlockPos pos) {
        return new Location(world, pos.add(getOffset()));
    }

    public RitualBlock rotate(Rotation rotation) {
        return new RitualBlock(state.getBlock(), offset.rotate(rotation));
    }

    public boolean isValid(World world, BlockPos pos) {
        return world.getBlockState(pos.add(offset)).getBlock().getDefaultState() == state;
    }

    public void onRitualComplete(World world, BlockPos pos, PlayerEntity player) {}
}
