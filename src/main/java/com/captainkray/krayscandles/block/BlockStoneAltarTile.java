package com.captainkray.krayscandles.block;

import com.captainkray.krayscandles.block.base.BlockBase;
import com.captainkray.krayscandles.init.InitTileEntityTypes;
import com.captainkray.krayscandles.tileentity.TileEntityStoneAltarTile;
import com.captainkray.krayscandles.util.ItemHelper;
import com.captainkray.krayscandles.util.Location;
import com.captainkray.krayscandles.util.ShapeBundle;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.List;

public class BlockStoneAltarTile extends BlockBase implements ITileEntityProvider {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final ShapeBundle SHAPE = new ShapeBundle();

    static {
        SHAPE.addShape(Block.makeCuboidShape(0, 0, 0, 16, 1, 16));
    }

    public BlockStoneAltarTile() {
        super(Block.Properties.from(Blocks.STONE).sound(SoundType.STONE));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {

        Location location = new Location(world, pos);
        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);

        if (location.getTileEntity() != null && location.getTileEntity() instanceof TileEntityStoneAltarTile) {

            TileEntityStoneAltarTile alterTile = (TileEntityStoneAltarTile) location.getTileEntity();

            if (alterTile.getRitualStack().isEmpty() && !stack.isEmpty()) {
                alterTile.placeRitualStack(stack.copy());
                stack.shrink(1);
                player.playSound(SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, 1, 1);
                return ActionResultType.SUCCESS;
            }

            else if (!alterTile.getRitualStack().isEmpty()) {

                ItemStack copiedStack = alterTile.getRitualStack().copy();
                copiedStack.setCount(1);

                ItemHelper.spawnStackAtEntity(world, player, copiedStack);
                alterTile.takeRitualStack();

                player.playSound(SoundEvents.ENTITY_ITEM_FRAME_REMOVE_ITEM, 1, 1);
                return ActionResultType.SUCCESS;
            }
        }

        return super.onBlockActivated(state, world, pos, player, hand, hit);
    }

    @Override
    public void addDrops(BlockState state, World world, BlockPos pos, List<ItemStack> list) {
        super.addDrops(state, world, pos, list);

        Location location = new Location(world, pos);
        TileEntity tileEntity = location.getTileEntity();

        if (tileEntity instanceof TileEntityStoneAltarTile) {

            TileEntityStoneAltarTile alterTile = (TileEntityStoneAltarTile) location.getTileEntity();

            if (!alterTile.getRitualStack().isEmpty()) {
                list.add(alterTile.getRitualStack());
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return InitTileEntityTypes.STONE_ALTAR_TILE.get().create();
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

    @Override
    public VoxelShape getShape (BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){
        return SHAPE.getCombinedShape();
    }

    @Override
    public VoxelShape getCollisionShape (BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return SHAPE.getCombinedShape();
    }
}
