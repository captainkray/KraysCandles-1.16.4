package com.tm.krayscandles.block.candle;

import com.tm.api.calemicore.util.Location;
import com.tm.api.calemicore.util.ShapeBundle;
import com.tm.api.calemicore.util.helper.SoundHelper;
import com.tm.krayscandles.block.base.BlockCandleBase;
import com.tm.krayscandles.init.InitTileEntityTypes;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.ritual.RitualRecipes;
import com.tm.krayscandles.util.ParticleHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockCandleLevitate extends BlockCandleBase {

    private static final ShapeBundle SHAPE = new ShapeBundle();

    static {
        SHAPE.addShape(Block.makeCuboidShape(7, 5, 7, 9, 11, 9)); //Candle
        SHAPE.addShape(Block.makeCuboidShape(7.75, 11, 7.75, 8.25, 13, 8.25)); //Wick
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return RitualRecipes.CANDLE_LEVITATE;
    }

    @Override
    public VoxelShape getCandleShape(BlockState state) {
        return SHAPE.getCombinedShape();
    }

    @Override
    public void renderFlame(World world, BlockPos pos, BlockState state, Vector3d particlePos) {
        ParticleHelper.renderFlame(world, pos,  particlePos.x, particlePos.y + 0.4D, particlePos.z);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        SoundHelper.playSoundAtLocation(new Location(world, pos), SoundEvents.ENTITY_PHANTOM_SWOOP, SoundCategory.BLOCKS, 1, 12);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return InitTileEntityTypes.CANDLE_LEVITATE.get().create();
    }
}
