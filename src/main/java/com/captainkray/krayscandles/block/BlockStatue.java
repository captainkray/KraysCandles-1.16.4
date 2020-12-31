package com.captainkray.krayscandles.block;

import com.captainkray.krayscandles.util.ParticleHelper;
import com.captainkray.krayscandles.util.ShapeBundle;
import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class BlockStatue extends Block {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final ShapeBundle SHAPE = new ShapeBundle();

    static {
        SHAPE.addShape(Block.makeCuboidShape(1, 0, 1, 15, 4, 15));
        SHAPE.addShape(Block.makeCuboidShape(2, 4, 2, 14, 6, 14));
    }

    public BlockStatue() {
        super(Block.Properties.from(Blocks.STONE).sound(SoundType.STONE).notSolid().setLightLevel(value -> 1));
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {

        double xOffset = 0.25D;
        double zOffset = -0.4D;

        switch (state.get(FACING)) {
            case EAST: {
                xOffset = 0.4D;
                zOffset = 0.25D;
                break;
            }
            case SOUTH: {
                xOffset = -0.25D;
                zOffset = 0.4D;
                break;
            }
            case WEST: {
                xOffset = -0.4D;
                zOffset = -0.25D;
            }
        }

        ParticleHelper.renderFlame(world, pos.getX() + 0.5D + xOffset, pos.getY() + 1.7D, pos.getZ() + 0.5D + zOffset);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){
        return SHAPE.getCombinedShape();
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return facing.getOpposite() == stateIn.get(FACING) && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : stateIn;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());

    }
    @Override
    public BlockState rotate(BlockState state, Rotation rot){
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn){
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer (StateContainer.Builder<Block, BlockState> builder){
        builder.add(FACING);
    }
}
