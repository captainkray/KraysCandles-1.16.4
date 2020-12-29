package com.captainkray.krayscandles.ritual;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualIngredient {

    private final BlockState state;
    private final BlockPos offset;

    public RitualIngredient(Block block, int x, int y, int z) {
        this.state = block.getDefaultState();
        this.offset = new BlockPos(x, y, z);
    }

    public RitualIngredient(BlockState state, BlockPos offset) {
        this.state = state;
        this.offset = offset;
    }

    public BlockState getState() {
        return state;
    }

    public BlockPos getOffset() {
        return offset;
    }

    public RitualIngredient rotate(Rotation rotation) {
        return new RitualIngredient(state, offset.rotate(rotation));
    }

    public boolean isValid(World world, BlockPos pos) {
        return world.getBlockState(pos.add(offset)).getBlock().getDefaultState() == state;
    }
}
