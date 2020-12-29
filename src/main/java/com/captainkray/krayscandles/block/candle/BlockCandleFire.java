package com.captainkray.krayscandles.block.candle;

import com.captainkray.krayscandles.block.base.BlockCandleBase;
import com.captainkray.krayscandles.init.InitTileEntityTypes;
import com.captainkray.krayscandles.util.ParticleHelper;
import com.captainkray.krayscandles.util.ShapeBundle;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockCandleFire extends BlockCandleBase {

    private static final ShapeBundle SHAPE = new ShapeBundle();

    static {
        SHAPE.addShape(Block.makeCuboidShape(7, 0, 7, 9, 22, 9));
        SHAPE.addShape(Block.makeCuboidShape(7.825D, 22, 7.825D, 8.225D, 23.75D, 8.225D));
    }

    @Override
    public VoxelShape getCandleShape(BlockState state) {
        return SHAPE.getCombinedShape();
    }

    @Override
    public void renderFlame(World world, BlockState state, Vector3d pos) {

        double midY = pos.y + 0.35D;
        double topY = pos.y + 1.125D;

        double midOffset = 0.1D;

        ParticleHelper.renderFlame(world, pos.x, topY, pos.z);
        ParticleHelper.renderFlame(world, pos.x + midOffset, midY, pos.z);
        ParticleHelper.renderFlame(world, pos.x - midOffset, midY, pos.z);
        ParticleHelper.renderFlame(world, pos.x, midY, pos.z + midOffset);
        ParticleHelper.renderFlame(world, pos.x, midY, pos.z - midOffset);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return InitTileEntityTypes.CANDLE_FIRE.get().create();
    }
}