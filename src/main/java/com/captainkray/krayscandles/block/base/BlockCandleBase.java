package com.captainkray.krayscandles.block.base;

import com.captainkray.krayscandles.block.BlockLanternSoulTrappedItem;
import com.captainkray.krayscandles.init.InitItems;
import com.captainkray.krayscandles.tileentity.base.ISoulFlame;
import com.captainkray.krayscandles.tileentity.base.TileEntityCandleBase;
import com.captainkray.krayscandles.util.IRitualItem;
import com.captainkray.krayscandles.util.Location;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public abstract class BlockCandleBase extends BlockBase implements ITileEntityProvider, IRitualItem {

    public static BooleanProperty LIT = BlockStateProperties.LIT;

    public BlockCandleBase() {
        super(Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.5F).sound(SoundType.SLIME).setLightLevel(value -> 14).notSolid());
        setDefaultState(this.stateContainer.getBaseState().with(LIT, false));
    }

    public BlockCandleBase(Properties properties) {
        super(properties);
        setDefaultState(this.stateContainer.getBaseState().with(LIT, false));
    }

    public abstract VoxelShape getCandleShape(BlockState state);
    public abstract void renderFlame(World world, BlockPos pos, BlockState state, Vector3d particlePos);

    public static void setLit(Location location, boolean value) {
        location.setBlock(location.getBlockState().with(LIT, value));
    }

    public static void attachSoul(Location location, String soulType, String soulName) {

        if (location.getTileEntity() instanceof TileEntityCandleBase) {

            TileEntityCandleBase candle = (TileEntityCandleBase) location.getTileEntity();
            candle.setSoul(soulType, soulName);
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {

        Location location = new Location(world, pos);
        ItemStack stack = player.getHeldItem(hand);

        boolean flintAndSteel = stack.getItem() == Items.FLINT_AND_STEEL;
        boolean lantern = stack.getItem() == Items.LANTERN;
        boolean soulLantern = stack.getItem() == Items.SOUL_LANTERN;
        boolean trappedSoulLantern = stack.getItem() == InitItems.LANTERN_SOUL_TRAPPED.get().asItem();

        if (!state.get(LIT)) {

            if (flintAndSteel || lantern || soulLantern || trappedSoulLantern) {

                if (flintAndSteel) {
                    stack.damageItem(1, player, (i) -> i.sendBreakAnimation(hand));
                    player.playSound(SoundEvents.ITEM_FLINTANDSTEEL_USE, 1, 1);
                }

                if (lantern || soulLantern || trappedSoulLantern) {

                    player.playSound(SoundEvents.ITEM_FIRECHARGE_USE, 1, 2);

                    if (trappedSoulLantern) {
                        String soulType = BlockLanternSoulTrappedItem.getSoulType(stack);
                        String soulName = BlockLanternSoulTrappedItem.getSoulName(stack);
                        attachSoul(location, soulType, soulName);
                    }
                }

                setLit(location, true);
                return ActionResultType.SUCCESS;
            }
        }

        else {

            if (soulLantern) {

                BlockLanternSoulTrappedItem.trapSoul(player, stack, ((ISoulFlame)location.getTileEntity()).getEntityTypeFromSoul());
                return ActionResultType.SUCCESS;
            }
        }

        return ActionResultType.PASS;
    }

    @Override
    public void onBlockClicked(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        Location location = new Location(world, pos);

        if (state.get(LIT)) {
            setLit(location, false);
            // Caleb Helped do a quick fix.
            attachSoul(new Location(world , pos) , "" ,"");
            player.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1, 2);
        }
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return state.get(LIT) ? super.getLightValue(state, world, pos) : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {

        if (state.get(LIT)) {
            renderFlame(world, pos, state, new Vector3d(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D));
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    public VoxelShape getShape (BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){
        return getCandleShape(state);
    }

    @Override
    public VoxelShape getCollisionShape (BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return getCandleShape(state);
    }
}
