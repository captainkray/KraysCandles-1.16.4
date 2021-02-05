package com.captainkray.krayscandles.block;

import com.captainkray.krayscandles.block.base.BlockCandleBase;
import com.captainkray.krayscandles.init.InitItems;
import com.captainkray.krayscandles.init.InitTileEntityTypes;
import com.captainkray.krayscandles.ritual.RitualRecipe;
import com.captainkray.krayscandles.tileentity.TileEntityCandleMount;
import com.captainkray.krayscandles.util.ItemHelper;
import com.captainkray.krayscandles.util.Location;
import com.captainkray.krayscandles.util.ParticleHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.List;
import java.util.Optional;

public class BlockSoyCandleMount extends BlockCandleBase {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final VoxelShape SHAPE_N = Optional.of(Block.makeCuboidShape(5.5D, 2.0D, 11.0D, 10.5D, 12.0D, 16.0D)).get();
    public static final VoxelShape SHAPE_E = Optional.of(Block.makeCuboidShape(0.0D, 2.0D, 5.5D, 5.0D, 12.0D, 10.5D)).get();
    public static final VoxelShape SHAPE_S = Optional.of(Block.makeCuboidShape(5.5D, 2.0D, 0.0D, 10.5D, 12.0D, 5.0D)).get();
    public static final VoxelShape SHAPE_W = Optional.of(Block.makeCuboidShape(11.0D, 2.0D, 5.5D, 16.0D, 12.0D, 10.5D)).get();

    public BlockSoyCandleMount() {
        super(Block.Properties.create(Material.IRON).hardnessAndResistance(1F).harvestTool(ToolType.PICKAXE).sound(SoundType.LANTERN).setLightLevel(value -> 14).notSolid());
        setDefaultState(this.stateContainer.getBaseState().with(LIT, false).with(FACING, Direction.NORTH));
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return null;
    }

    @Override
    public VoxelShape getCandleShape(BlockState state) {
        switch (state.get(FACING)) {
            case EAST: return SHAPE_E;
            case SOUTH: return SHAPE_S;
            case WEST: return SHAPE_W;
            default: return SHAPE_N;
        }
    }

    @Override
    public void renderFlame(World world, BlockPos pos, BlockState state, Vector3d particlePos) {

        double xOffset = 0.0D;
        double zOffset = 0.2D;

        switch (state.get(FACING)) {
            case EAST: {
                xOffset = -0.2D;
                zOffset = 0.0D;
                break;
            }
            case SOUTH: {
                xOffset = 0.0D;
                zOffset = -0.2D;
                break;
            }
            case WEST: {
                xOffset = 0.2D;
                zOffset = 0.0D;
            }
        }

        ParticleHelper.renderFlame(world, pos,  particlePos.x + xOffset, particlePos.y + 0.3D, particlePos.z + zOffset);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {

        Location location = new Location(world, pos);
        ItemStack heldStack = player.getHeldItem(Hand.MAIN_HAND);

        if (location.getTileEntity() != null && location.getTileEntity() instanceof TileEntityCandleMount) {

            TileEntityCandleMount mount = (TileEntityCandleMount) location.getTileEntity();

            if (!mount.getCandleStack().isEmpty()) {

                if (heldStack.isEmpty()) {
                    ItemHelper.spawnStackAtLocation(world, location, mount.getCandleStack());
                    mount.takeCandle();
                    setLit(location, false);
                    mount.setSoul("", "");
                    player.playSound(SoundEvents.ENTITY_ITEM_FRAME_REMOVE_ITEM, 1, 1);
                    return ActionResultType.SUCCESS;
                }

                return super.onBlockActivated(state, world, pos, player, hand, hit);
            }

            else if (mount.getCandleStack().isEmpty() && heldStack.getItem() == InitItems.CANDLE_SOY_COLORED_ITEM.get()) {
                mount.placeCandle(heldStack.getDamage());
                heldStack.shrink(1);
                player.playSound(SoundEvents.ENTITY_ITEM_FRAME_ADD_ITEM, 1, 1);
                return ActionResultType.SUCCESS;
            }
        }

        return ActionResultType.FAIL;
    }

    @Override
    public void addDrops(BlockState state, World world, BlockPos pos, List<ItemStack> list) {
        super.addDrops(state, world, pos, list);

        Location location = new Location(world, pos);
        TileEntity tileEntity = location.getTileEntity();

        if (tileEntity instanceof TileEntityCandleMount) {

            TileEntityCandleMount mount = (TileEntityCandleMount) location.getTileEntity();

            if (!mount.getCandleStack().isEmpty()) {
                list.add(mount.getCandleStack());
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return InitTileEntityTypes.CANDLE_SOY_MOUNT.get().create();
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING);
    }

    public PushReaction getPushReaction() {
        return PushReaction.BLOCK;
    }
}

