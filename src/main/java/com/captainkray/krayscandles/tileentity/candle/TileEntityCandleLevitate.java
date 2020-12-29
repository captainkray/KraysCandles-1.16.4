package com.captainkray.krayscandles.tileentity.candle;

import com.captainkray.krayscandles.init.InitTileEntityTypes;
import com.captainkray.krayscandles.tileentity.base.TileEntityCandleBase;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class TileEntityCandleLevitate extends TileEntityCandleBase {

    public TileEntityCandleLevitate() {
        super(InitTileEntityTypes.CANDLE_LEVITATE.get());
    }

    @Override
    public EffectInstance[] getCandleEffects() {
        return new EffectInstance[] {new EffectInstance(Effects.LEVITATION)};
    }
}
