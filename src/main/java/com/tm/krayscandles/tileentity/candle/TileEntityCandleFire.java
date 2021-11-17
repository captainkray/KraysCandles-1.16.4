package com.tm.krayscandles.tileentity.candle;

import com.tm.krayscandles.init.InitTileEntityTypes;
import com.tm.krayscandles.tileentity.base.TileEntityCandleBase;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class TileEntityCandleFire extends TileEntityCandleBase {

    public TileEntityCandleFire() {
        super(InitTileEntityTypes.CANDLE_FIRE.get());
    }

    @Override
    public EffectInstance[] getCandleEffects() {
        return new EffectInstance[] {new EffectInstance(Effects.FIRE_RESISTANCE)};
    }
}
