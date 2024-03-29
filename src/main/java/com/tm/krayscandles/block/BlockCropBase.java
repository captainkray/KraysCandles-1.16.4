package com.tm.krayscandles.block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import java.util.function.Supplier;

public class BlockCropBase extends CropsBlock {

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(0, 0, 0, 16, 2, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 4, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 6, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 8, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 10, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 12, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 14, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 16, 16)};

    private final Supplier<Item> cropItem;

    public BlockCropBase(Supplier<Item> cropItem) {
        super(Block.Properties.from(Blocks.WHEAT));
        this.cropItem = cropItem;
    }

    public BlockCropBase() {
        super(Block.Properties.from(Blocks.WHEAT));
        this.cropItem = null;
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return Item.getItemFromBlock(this);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_BY_AGE[state.get(getAgeProperty())];
    }
}
