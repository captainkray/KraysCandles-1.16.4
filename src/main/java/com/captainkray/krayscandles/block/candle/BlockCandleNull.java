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
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockCandleNull extends BlockCandleBase {

    private static final ShapeBundle SHAPE = new ShapeBundle();

    static {
        SHAPE.addShape(Block.makeCuboidShape(7, 0, 7, 9, 22, 9));
        SHAPE.addShape(Block.makeCuboidShape(7.825D, 22, 7.825D, 8.225D, 23.75D, 8.225D));
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return RitualRecipes.CANDLE_NULL;
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
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        new Location(world, pos).playSound(SoundEvents.BLOCK_BEACON_DEACTIVATE, 1, 10);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return InitTileEntityTypes.CANDLE_NULL.get().create();
    }
}
