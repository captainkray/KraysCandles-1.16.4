package com.captainkray.krayscandles.block.candle;

import com.captainkray.krayscandles.block.base.BlockCandleBase;
import com.captainkray.krayscandles.init.InitTileEntityTypes;
import com.captainkray.krayscandles.util.ParticleHelper;
import com.captainkray.krayscandles.util.ShapeBundle;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockCandleBlessed extends BlockCandleBase {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final ShapeBundle SHAPE = new ShapeBundle();

    static {
        SHAPE.addShape(Block.makeCuboidShape(7, 0, 7, 9, 11, 9)); //Candle
        SHAPE.addShape(Block.makeCuboidShape(7.75, 11, 7.75, 8.25, 13, 8.25)); //Wick
    }

    public BlockCandleBlessed() {
        setDefaultState(stateContainer.getBaseState().with(LIT, false).with(FACING, Direction.NORTH));
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
    public VoxelShape getCandleShape(BlockState state) {
        return SHAPE.getCombinedShape();
    }

    @Override
    public void renderFlame(World world, BlockState state, Vector3d pos) {
        ParticleHelper.renderFlame(world, pos.x, pos.y + 0.4D, pos.z);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        placer.playSound(SoundEvents.AMBIENT_UNDERWATER_LOOP_ADDITIONS_ULTRA_RARE, 1, 12);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return InitTileEntityTypes.CANDLE_BLESSED.get().create();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING);
    }
}
