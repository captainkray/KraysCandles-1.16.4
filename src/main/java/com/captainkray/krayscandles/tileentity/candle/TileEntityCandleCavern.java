package com.captainkray.krayscandles.tileentity.candle;

import com.captainkray.krayscandles.init.InitTileEntityTypes;
import com.captainkray.krayscandles.tileentity.base.TileEntityCandleBase;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class TileEntityCandleCavern extends TileEntityCandleBase {

    public TileEntityCandleCavern() {
        super(InitTileEntityTypes.CANDLE_CAVERN.get());
    }

    @Override
    public EffectInstance[] getCandleEffects() {
        return new EffectInstance[] {new EffectInstance(Effects.HASTE)};
    }
}
