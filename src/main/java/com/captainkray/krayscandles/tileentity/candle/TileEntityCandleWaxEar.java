package com.captainkray.krayscandles.tileentity.candle;

import com.captainkray.krayscandles.init.InitTileEntityTypes;
import com.captainkray.krayscandles.tileentity.base.TileEntityCandleBase;
import net.minecraft.potion.EffectInstance;

public class TileEntityCandleWaxEar extends TileEntityCandleBase {

    public TileEntityCandleWaxEar() {
        super(InitTileEntityTypes.CANDLE_WAX_EAR.get());
    }

    @Override
    public EffectInstance[] getCandleEffects() {
        return new EffectInstance[] {};
    }
}
