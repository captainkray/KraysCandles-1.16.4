package com.captainkray.krayscandles.block;


import com.captainkray.krayscandles.init.InitItems;
import net.minecraft.block.*;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class BlockSoybeanCrop extends CropsBlock {

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(0, 0, 0, 16, 2, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 4, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 6, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 8, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 1, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 1, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 1, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 1, 16)};

    public BlockSoybeanCrop() {
        super(Block.Properties.from(Blocks.WHEAT).sound(SoundType.CROP));
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return InitItems.SOYBEAN_CROP.get().asItem();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }
}
