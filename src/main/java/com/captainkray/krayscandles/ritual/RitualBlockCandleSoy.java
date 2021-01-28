package com.captainkray.krayscandles.ritual;

import com.captainkray.krayscandles.block.base.BlockCandleBase;
import com.captainkray.krayscandles.block.candle.BlockCandleSoy;
import com.captainkray.krayscandles.block.candle.BlockCandleSoyColored;
import com.captainkray.krayscandles.init.InitItems;
import com.captainkray.krayscandles.util.Location;
import net.minecraft.block.Block;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualBlockCandleSoy extends RitualBlockCandle {

    public RitualBlockCandleSoy(int x, int y, int z) {
        super(InitItems.CANDLE_SOY.get(), x, y, z);
    }

    public RitualBlockCandleSoy() {
        super(InitItems.CANDLE_SOY.get(), 0, 0, 0);
    }

    public RitualBlockCandleSoy(Block block, BlockPos offset) {
        super(block, offset);
    }

    public RitualBlockCandleSoy rotate(Rotation rotation) {
        return new RitualBlockCandleSoy(getState().getBlock(), getOffset().rotate(rotation));
    }

    @Override
    public boolean isValid(World world, BlockPos pos) {

        Location location = getLocation(world, pos);

        if (location.getBlock() instanceof BlockCandleSoy || location.getBlock() instanceof BlockCandleSoyColored) {
            return location.getBlockState().get(BlockCandleBase.LIT);
        }

        return false;
    }
}