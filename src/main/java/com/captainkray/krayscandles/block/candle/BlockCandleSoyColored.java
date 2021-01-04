package com.captainkray.krayscandles.block.candle;

import com.captainkray.krayscandles.block.base.BlockCandleBase;
import com.captainkray.krayscandles.block.base.KCBlockStates;
import com.captainkray.krayscandles.init.InitItems;
import com.captainkray.krayscandles.init.InitTileEntityTypes;
import com.captainkray.krayscandles.util.Location;
import com.captainkray.krayscandles.util.ParticleHelper;
import com.captainkray.krayscandles.util.ShapeBundle;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.List;

public class BlockCandleSoyColored extends BlockCandleBase {

    public static final EnumProperty<DyeColor> COLOR = KCBlockStates.COLOR;

    private static final ShapeBundle SHAPE = new ShapeBundle();

    static {
        SHAPE.addShape(Block.makeCuboidShape(7.75, 8, 7.75, 8.25, 10, 8.25));
        SHAPE.addShape(Block.makeCuboidShape(7, 0, 7, 9, 8, 9));
    }

    public BlockCandleSoyColored() {
        setDefaultState(stateContainer.getBaseState().with(LIT, false).with(COLOR, DyeColor.WHITE));
    }

    @Override
    public VoxelShape getCandleShape(BlockState state) {
        return SHAPE.getCombinedShape();
    }

    @Override
    public void renderFlame(World world, BlockPos pos, BlockState state, Vector3d particlePos) {
        ParticleHelper.renderFlame(world, pos,  particlePos.x, particlePos.y + 0.2D, particlePos.z);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        world.setBlockState(pos, state.with(COLOR, DyeColor.byId(stack.getDamage())));
    }

    @Override
    public void addDrops(BlockState state, World world, BlockPos pos, List<ItemStack> list) {
        ItemStack stack = new ItemStack(InitItems.CANDLE_SOY_COLORED_ITEM.get());
        stack.setDamage(state.get(COLOR).getId());
        list.add(stack);
    }

    @Override
    protected void fillStateContainer (StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(COLOR);
    }


    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return InitTileEntityTypes.CANDLE_SOY.get().create();
    }
}
