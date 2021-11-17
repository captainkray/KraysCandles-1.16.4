package com.tm.krayscandles.ritual;

import com.tm.api.calemicore.util.Location;
import com.tm.krayscandles.block.base.BlockCandleBase;
import com.tm.krayscandles.block.candle.BlockCandleSoy;
import com.tm.krayscandles.block.candle.BlockCandleSoyColored;
import com.tm.krayscandles.init.InitItems;
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