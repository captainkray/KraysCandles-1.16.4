package com.tm.krayscandles.main;

import com.tm.api.calemicore.util.Location;
import com.tm.krayscandles.block.base.BlockCandleBase;
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

        if (location.getBlock() instanceof BlockCandleBase) {

            if (location.getBlockState().get(BlockCandleBase.LIT)) {

                BlockCandleBase.extinguishCandle(location);
            }

            else BlockCandleBase.lightCandle(location, null, stack);
        }

        return stack;
    }
}
