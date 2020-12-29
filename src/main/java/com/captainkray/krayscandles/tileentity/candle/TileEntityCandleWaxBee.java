package com.captainkray.krayscandles.tileentity.candle;

import com.captainkray.krayscandles.init.InitTileEntityTypes;
import com.captainkray.krayscandles.tileentity.base.TileEntityCandleBase;
import net.minecraft.potion.EffectInstance;

public class TileEntityCandleWaxBee extends TileEntityCandleBase {

    public TileEntityCandleWaxBee() {
        super(InitTileEntityTypes.CANDLE_WAX_BEE.get());
    }

    @Override
    public EffectInstance[] getCandleEffects() {
        return new EffectInstance[] {};
    }
}
