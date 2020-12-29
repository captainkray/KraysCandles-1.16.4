package com.captainkray.krayscandles.block.candle;

import com.captainkray.krayscandles.block.base.BlockCandleBase;
import com.captainkray.krayscandles.init.InitTileEntityTypes;
import com.captainkray.krayscandles.util.ParticleHelper;
import com.captainkray.krayscandles.util.ShapeBundle;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockCandleCavern extends BlockCandleBase {

    private static final ShapeBundle SHAPE = new ShapeBundle();

    static {
        SHAPE.addShape(Block.makeCuboidShape(10.75, 9, 2.75, 11.25, 11, 3.25));
        SHAPE.addShape(Block.makeCuboidShape(7, 0, 7, 9, 8, 9));
        SHAPE.addShape(Block.makeCuboidShape(10, 0, 2, 12, 9, 4));
        SHAPE.addShape(Block.makeCuboidShape(12, 0, 9, 14, 7, 11));
        SHAPE.addShape(Block.makeCuboidShape(3, 0, 10, 5, 6, 12));
        SHAPE.addShape(Block.makeCuboidShape(1, 0, 1, 3, 5, 3));
        SHAPE.addShape(Block.makeCuboidShape(8, 0, 13, 9, 4, 14));
        SHAPE.addShape(Block.makeCuboidShape(13, 0, 5, 14, 4, 6));
        SHAPE.addShape(Block.makeCuboidShape(2, 0, 6, 3, 3, 7));
        SHAPE.addShape(Block.makeCuboidShape(2, 0, 14, 3, 5, 15));
        SHAPE.addShape(Block.makeCuboidShape(6, 0, 1, 7, 5, 2));
        SHAPE.addShape(Block.makeCuboidShape(14, 0, 13, 15, 5, 14));
        SHAPE.addShape(Block.makeCuboidShape(7.75, 8, 7.75, 8.25, 10, 8.25));
        SHAPE.addShape(Block.makeCuboidShape(12.75, 7, 9.75, 13.25, 9, 10.25));
        SHAPE.addShape(Block.makeCuboidShape(3.75, 6, 10.75, 4.25, 8, 11.25));
        SHAPE.addShape(Block.makeCuboidShape(1.75, 5, 1.75, 2.25, 7, 2.25));
        SHAPE.addShape(Block.makeCuboidShape(6.25, 5, 1.25, 6.75, 6, 1.75));
        SHAPE.addShape(Block.makeCuboidShape(13.25, 4, 5.25, 13.75, 5, 5.75));
        SHAPE.addShape(Block.makeCuboidShape(2.25, 3, 6.25, 2.75, 4, 6.75));
        SHAPE.addShape(Block.makeCuboidShape(2.25, 5, 14.25, 2.75, 6, 14.75));
        SHAPE.addShape(Block.makeCuboidShape(8.25, 4, 13.25, 8.75, 5, 13.75));
        SHAPE.addShape(Block.makeCuboidShape(14.25, 5, 13.25, 14.75, 6, 13.75));
    }

    @Override
    public VoxelShape getCandleShape(BlockState state) {
        return SHAPE.getCombinedShape();
    }

    @Override
    public void renderFlame(World world, BlockState state, Vector3d pos) {

        ParticleHelper.renderFlame(world, pos.x + 0.2D, pos.y + 0.3D, pos.z - 0.3D);
        ParticleHelper.renderFlame(world, pos.x, pos.y + 0.2D, pos.z);
        ParticleHelper.renderFlame(world, pos.x + 0.3D, pos.y + 0.15D, pos.z + 0.125D);
        ParticleHelper.renderFlame(world, pos.x - 0.25D, pos.y + 0.1D, pos.z + 0.2D);
        ParticleHelper.renderFlame(world, pos.x - 0.375D, pos.y, pos.z - 0.375D);
        ParticleHelper.renderFlame(world, pos.x - 0.35D, pos.y, pos.z + 0.4D);
        ParticleHelper.renderFlame(world, pos.x + 0.4D, pos.y, pos.z + 0.35D);
        ParticleHelper.renderFlame(world, pos.x - 0.1D, pos.y, pos.z - 0.4D);
        ParticleHelper.renderFlame(world, pos.x + 0.35D, pos.y - 0.1D, pos.z - 0.15D);
        ParticleHelper.renderFlame(world, pos.x + 0.025D, pos.y - 0.1D, pos.z + 0.35D);
        ParticleHelper.renderFlame(world, pos.x - 0.35D, pos.y - 0.15D, pos.z - 0.075D);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        placer.playSound(SoundEvents.BLOCK_STONE_STEP, 1, 5);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return InitTileEntityTypes.CANDLE_CAVERN.get().create();
    }
}
