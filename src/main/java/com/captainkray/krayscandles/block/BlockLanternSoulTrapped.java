package com.captainkray.krayscandles.block;

import com.captainkray.krayscandles.block.base.BlockBase;
import com.captainkray.krayscandles.util.ItemHelper;
import com.captainkray.krayscandles.util.ParticleHelper;
import com.captainkray.krayscandles.util.ShapeBundle;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.PushReaction;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockLanternSoulTrapped extends BlockBase implements IWaterLoggable {

    public static final BooleanProperty HANGING = BlockStateProperties.HANGING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final ShapeBundle GROUNDED_SHAPE = new ShapeBundle();
    private static final ShapeBundle HANGING_SHAPE = new ShapeBundle();

    static {
        GROUNDED_SHAPE.addShape(Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 7.0D, 11.0D));
        GROUNDED_SHAPE.addShape(Block.makeCuboidShape(6.0D, 7.0D, 6.0D, 10.0D, 9.0D, 10.0D));

        HANGING_SHAPE.addShape(Block.makeCuboidShape(5.0D, 1.0D, 5.0D, 11.0D, 8.0D, 11.0D));
        HANGING_SHAPE.addShape(Block.makeCuboidShape(6.0D, 8.0D, 6.0D, 10.0D, 10.0D, 10.0D));
    }

    public BlockLanternSoulTrapped() {
        super(Properties.from(Blocks.SOUL_LANTERN));
        setDefaultState(stateContainer.getBaseState().with(HANGING, Boolean.FALSE).with(WATERLOGGED, Boolean.FALSE));
    }

    public static String getTrappedSoulString(ItemStack stack) {
        CompoundNBT nbt = ItemHelper.getNBT(stack);
        return nbt.getString("soul_type");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CompoundNBT nbt = ItemHelper.getNBT(stack);

        if (!nbt.getString("soul_name").isEmpty()) {
            tooltip.add(new StringTextComponent(TextFormatting.DARK_PURPLE + "Trapped Soul: " + TextFormatting.LIGHT_PURPLE + nbt.getString("soul_name")));
            System.out.println((nbt.getString("soul_type")));
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        ParticleHelper.renderSoulFlame(world, pos.getX() + 0.5D, pos.getY() + (state.get(HANGING) ? 0.3D : 0.25D), pos.getZ() + 0.5D);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {

        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());

        for(Direction direction : context.getNearestLookingDirections()) {

            if (direction.getAxis() == Direction.Axis.Y) {

                BlockState blockstate = this.getDefaultState().with(HANGING, direction == Direction.UP);

                if (blockstate.isValidPosition(context.getWorld(), context.getPos())) {
                    return blockstate.with(WATERLOGGED, fluidstate.getFluid() == Fluids.WATER);
                }
            }
        }

        return null;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return state.get(HANGING) ? HANGING_SHAPE.getCombinedShape() : GROUNDED_SHAPE.getCombinedShape();
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HANGING, WATERLOGGED);
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        Direction direction = getBlockConnected(state).getOpposite();
        return Block.hasEnoughSolidSide(worldIn, pos.offset(direction), direction.getOpposite());
    }

    protected static Direction getBlockConnected(BlockState state) {
        return state.get(HANGING) ? Direction.DOWN : Direction.UP;
    }

    public PushReaction getPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {

        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }

        return getBlockConnected(stateIn).getOpposite() == facing && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }
}
