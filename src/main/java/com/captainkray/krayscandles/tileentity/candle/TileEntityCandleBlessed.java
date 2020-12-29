package com.captainkray.krayscandles.tileentity.candle;

import com.captainkray.krayscandles.init.InitTileEntityTypes;
import com.captainkray.krayscandles.tileentity.base.TileEntityCandleBase;
import net.minecraft.potion.EffectInstance;

public class TileEntityCandleBlessed extends TileEntityCandleBase {

    public TileEntityCandleBlessed() {
        super(InitTileEntityTypes.CANDLE_BLESSED.get());
    }

    @Override
    public EffectInstance[] getCandleEffects() {
        return new EffectInstance[] {};
    }
}
