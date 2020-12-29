package com.captainkray.krayscandles.tileentity.candle;

import com.captainkray.krayscandles.init.InitTileEntityTypes;
import com.captainkray.krayscandles.tileentity.base.TileEntityCandleBase;
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
