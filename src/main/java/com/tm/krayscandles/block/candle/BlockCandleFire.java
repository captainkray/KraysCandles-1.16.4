package com.tm.krayscandles.block.candle;

import com.tm.api.calemicore.util.ShapeBundle;
import com.tm.krayscandles.block.base.BlockCandleBase;
import com.tm.krayscandles.init.InitTileEntityTypes;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.ritual.RitualRecipes;
import com.tm.krayscandles.util.ParticleHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
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
    public RitualRecipe getRitualRecipe() {
        return RitualRecipes.CANDLE_FIRE;
    }

    @Override
    public VoxelShape getCandleShape(BlockState state) {
        return SHAPE.getCombinedShape();
    }

    @Override
    public void renderFlame(World world, BlockPos pos, BlockState state, Vector3d particlePos) {

        double midY = particlePos.y + 0.35D;
        double topY = particlePos.y + 1.125D;

        double midOffset = 0.1D;

        ParticleHelper.renderFlame(world, pos, particlePos.x, topY, particlePos.z);
        ParticleHelper.renderFlame(world, pos,  particlePos.x + midOffset, midY, particlePos.z);
        ParticleHelper.renderFlame(world, pos,  particlePos.x - midOffset, midY, particlePos.z);
        ParticleHelper.renderFlame(world, pos,  particlePos.x, midY, particlePos.z + midOffset);
        ParticleHelper.renderFlame(world, pos,  particlePos.x, midY, particlePos.z - midOffset);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return InitTileEntityTypes.CANDLE_FIRE.get().create();
    }
}
