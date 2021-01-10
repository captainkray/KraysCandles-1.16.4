package com.captainkray.krayscandles.block;

import com.captainkray.krayscandles.init.InitTileEntityTypes;
import com.captainkray.krayscandles.tileentity.TileEntityLanternSoulTrapped;
import com.captainkray.krayscandles.util.ItemHelper;
import com.captainkray.krayscandles.util.Location;
import com.captainkray.krayscandles.util.ParticleHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.LanternBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockLanternSoulTrapped extends LanternBlock implements ITileEntityProvider {

    public BlockLanternSoulTrapped() {
        super(Properties.from(Blocks.SOUL_LANTERN));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CompoundNBT nbt = ItemHelper.getNBT(stack);

        if (!nbt.getString("soul_name").isEmpty()) {
            tooltip.add(new StringTextComponent(TextFormatting.DARK_PURPLE + "Trapped Soul: " + TextFormatting.LIGHT_PURPLE + nbt.getString("soul_name")));
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        ParticleHelper.renderFlame(world, pos, pos.getX() + 0.5D, pos.getY() + (state.get(HANGING) ? 0.3D : 0.25D), pos.getZ() + 0.5D);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {

        CompoundNBT nbt = ItemHelper.getNBT(stack);
        Location location = new Location(world, pos);

        if (location.getTileEntity() instanceof TileEntityLanternSoulTrapped) {
            TileEntityLanternSoulTrapped lantern = (TileEntityLanternSoulTrapped) location.getTileEntity();
            lantern.setSoul(nbt.getString("soul_type"), nbt.getString("soul_name"));
        }
    }

    @Override
    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {

        Location location = new Location(world, pos);

        if (location.getTileEntity() instanceof TileEntityLanternSoulTrapped) {

            TileEntityLanternSoulTrapped lantern = (TileEntityLanternSoulTrapped) location.getTileEntity();

            ItemEntity itemEntity = ItemHelper.spawnStack(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, new ItemStack((this.asItem())));
            ItemStack stack = itemEntity.getItem();
            BlockLanternSoulTrappedItem.setSoul(stack, lantern.getEntityTypeFromSoul());
            itemEntity.setItem(stack);
        }

        super.onReplaced(state, world, pos, newState, isMoving);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return InitTileEntityTypes.LANTERN_SOUL_TRAPPED.get().create();
    }
}
