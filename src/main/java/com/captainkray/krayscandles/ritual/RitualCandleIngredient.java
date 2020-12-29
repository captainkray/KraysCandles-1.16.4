package com.captainkray.krayscandles.ritual;

import com.captainkray.krayscandles.block.base.BlockCandleBase;
import com.captainkray.krayscandles.block.candle.BlockCandleSoy;
import com.captainkray.krayscandles.block.candle.BlockCandleSoyColored;
import com.captainkray.krayscandles.init.InitItems;
import com.captainkray.krayscandles.util.Location;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualCandleIngredient extends RitualIngredient {

    public RitualCandleIngredient(int x, int y, int z) {
        super(InitItems.CANDLE_SOY.get(), x, y, z);
    }

    public RitualCandleIngredient(BlockPos offset) {
        super(InitItems.CANDLE_SOY.get().getDefaultState(), offset);
    }

    @Override
    public RitualCandleIngredient rotate(Rotation rotation) {
        return new RitualCandleIngredient(getOffset().rotate(rotation));
    }

    @Override
    public boolean isValid(World world, BlockPos pos) {

        Location location = new Location(world, pos.add(getOffset()));

        if (location.getBlock() instanceof BlockCandleSoy || location.getBlock() instanceof BlockCandleSoyColored) {
            return location.getBlockState().get(BlockCandleBase.LIT);
        }

        return false;
    }
}
