package com.captainkray.krayscandles.block.candle;

import com.captainkray.krayscandles.block.base.BlockCandleBase;
import com.captainkray.krayscandles.init.InitTileEntityTypes;
import com.captainkray.krayscandles.ritual.RitualRecipe;
import com.captainkray.krayscandles.ritual.RitualRecipes;
import com.captainkray.krayscandles.util.Location;
import com.captainkray.krayscandles.util.ParticleHelper;
import com.captainkray.krayscandles.util.ShapeBundle;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockCandleLuck extends BlockCandleBase {

    private static final ShapeBundle SHAPE = new ShapeBundle();

    static {
        SHAPE.addShape(Block.makeCuboidShape(7, 0, 7, 9, 5, 9));
        SHAPE.addShape(Block.makeCuboidShape(7.75, 5, 7.75, 8.25, 7, 8.25));
        SHAPE.addShape(Block.makeCuboidShape(4.5, 4.8, 4.5, 11.5, 5.25, 11.5));
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return RitualRecipes.CANDLE_LUCK;
    }

    @Override
    public VoxelShape getCandleShape(BlockState state) {
        return SHAPE.getCombinedShape();
    }

    @Override
    public void renderFlame(World world, BlockPos pos, BlockState state, Vector3d particlePos) {
        ParticleHelper.renderFlame(world, pos,  particlePos.x, particlePos.y + 0.05D, particlePos.z);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return InitTileEntityTypes.CANDLE_LUCK.get().create();
    }
}