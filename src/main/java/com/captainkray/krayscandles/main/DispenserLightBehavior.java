package com.captainkray.krayscandles.main;

import com.captainkray.krayscandles.block.base.BlockCandleBase;
import com.captainkray.krayscandles.util.Location;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IDispenseItemBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;

public class DispenserLightBehavior implements IDispenseItemBehavior {

    @Override
    public ItemStack dispense(IBlockSource source, ItemStack stack) {

        Direction direction = source.getBlockState().get(DispenserBlock.FACING);

        Location location = new Location(source.getWorld(), source.getBlockPos().offset(direction));

        System.out.println(location.getBlock());

        if (location.getBlock() instanceof BlockCandleBase) {
            BlockCandleBase.setLit(location, true);
        }

        return stack;
    }
}
