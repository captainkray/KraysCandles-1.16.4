package com.captainkray.krayscandles.block.base;

import com.captainkray.krayscandles.util.ItemHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * The base class for Blocks.
 */
public class BlockBase extends Block {

    /**
     * @param properties The specific properties for the Block. (Creative Tab, hardness, material, etc.)
     */
    public BlockBase (Properties properties) {
        super(properties);
    }

    public void addDrops(BlockState state, World world, BlockPos pos, List<ItemStack> list) {
        list.add(new ItemStack(asItem()));
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {

        if (!player.isCreative() && !player.isSpectator()) {

            List<ItemStack> drops = new ArrayList<>();
            addDrops(state, world, pos, drops);

            for (ItemStack stack : drops) {
                ItemHelper.spawnStack(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, stack);
            }
        }

        super.onBlockHarvested(world, pos, state, player);
    }

    @Override
    public void onReplaced (BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {

        super.onReplaced(state, world, pos, newState, isMoving);

        /*if (state.getBlock() != newState.getBlock()) {

            if (isMoving && PistonBlock.canPush(state, world, pos, Direction.NORTH, false, Direction.NORTH)) {
                return;
            }

            List<ItemStack> drops = new ArrayList<>();
            addDrops(state, world, pos, drops);

            for (ItemStack stack : drops) {
                ItemHelper.spawnStack(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, stack);
            }

            super.onReplaced(state, world, pos, newState, isMoving);
        }*/
    }
}
