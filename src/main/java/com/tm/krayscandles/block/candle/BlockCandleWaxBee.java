package com.tm.krayscandles.block.candle;

import com.tm.api.calemicore.util.ShapeBundle;
import com.tm.krayscandles.block.base.BlockCandleBase;
import com.tm.krayscandles.init.InitTileEntityTypes;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.util.ParticleHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockCandleWaxBee extends BlockCandleBase {

    private static final ShapeBundle SHAPE = new ShapeBundle();

    static {
        SHAPE.addShape(Block.makeCuboidShape(6, 0, 6, 10, 6, 10));//Candle
        SHAPE.addShape(Block.makeCuboidShape(7.625, 5.2, 7.625, 8.375, 8.2, 8.375)); //Wick
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return null;
    }

    @Override
    public VoxelShape getCandleShape(BlockState state) {
        return SHAPE.getCombinedShape();
    }

    @Override
    public void renderFlame(World world, BlockPos pos, BlockState state, Vector3d particlePos) {
        ParticleHelper.renderFlame(world, pos,  particlePos.x, particlePos.y + 0.1D, particlePos.z);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return InitTileEntityTypes.CANDLE_WAX_BEE.get().create();
    }
}
