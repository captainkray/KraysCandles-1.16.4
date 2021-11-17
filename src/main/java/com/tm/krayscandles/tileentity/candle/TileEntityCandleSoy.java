package com.tm.krayscandles.tileentity.candle;

import com.tm.krayscandles.init.InitTileEntityTypes;
import com.tm.krayscandles.tileentity.base.TileEntityCandleBase;
import net.minecraft.potion.EffectInstance;

public class TileEntityCandleSoy extends TileEntityCandleBase {

    public TileEntityCandleSoy() {
        super(InitTileEntityTypes.CANDLE_SOY.get());
    }

    @Override
    public EffectInstance[] getCandleEffects() {
        return new EffectInstance[] {};
    }
}
