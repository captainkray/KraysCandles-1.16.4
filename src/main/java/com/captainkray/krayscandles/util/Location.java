package com.captainkray.krayscandles.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeBlockState;

public class Location {

    public final World world;
    public int x, y, z;
    private BlockPos blockPos;

    public Location(World world, BlockPos pos) {
        this(world, pos.getX(), pos.getY(), pos.getZ());
    }

    public Location(World world, int x, int y, int z) {

        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;

        blockPos = new BlockPos(x, y, z);
    }

    public Location(TileEntity tileEntity) {
        this(tileEntity.getWorld(), tileEntity.getPos().getX(), tileEntity.getPos().getY(), tileEntity.getPos().getZ());
    }

    public Location(Entity entity) {
        this(entity.world, entity.getPosition().getX(), entity.getPosition().getY(), entity.getPosition().getZ());
    }

    public Location translate (Direction dir, int distance) {

        this.x += (dir.getXOffset() * distance);
        this.y += (dir.getYOffset() * distance);
        this.z += (dir.getZOffset() * distance);
        blockPos = new BlockPos(x, y, z);

        return this;
    }

    public Location translate (Location location) {

        this.x += location.x;
        this.y += location.y;
        this.z += location.z;
        blockPos = new BlockPos(x, y, z);
        return this;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public IForgeBlockState getForgeBlockState() {

        if (getBlockPos() == null) {
            return null;
        }

        return world.getBlockState(getBlockPos());
    }

    public BlockState getBlockState() {

        if (getForgeBlockState() == null) {
            return null;
        }

        return getForgeBlockState().getBlockState();
    }

    public Block getBlock() {

        if (getBlockState() == null) {
            return null;
        }

        return getBlockState().getBlock();
    }

    public TileEntity getTileEntity() {
        return world.getTileEntity(getBlockPos());
    }

    public void setBlock (Block block) {
        world.setBlockState(getBlockPos(), block.getDefaultState());
    }

    public void setBlock (BlockState state) {
        world.setBlockState(getBlockPos(), state.getBlock().getDefaultState());
        world.setBlockState(getBlockPos(), state);
    }

    public void setBlock (BlockState state, PlayerEntity placer) {
        world.setBlockState(getBlockPos(), state, 2);
        state.getBlock().onBlockPlacedBy(world, getBlockPos(), state, placer, new ItemStack(state.getBlock()));
    }

    public void setBlockToAir () {
        setBlock(Blocks.AIR);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Location) {
            Location newLoc = (Location) obj;
            return world == newLoc.world && x == newLoc.x && y == newLoc.y && z == newLoc.z;
        }

        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + ", " + z + "]";
    }
}