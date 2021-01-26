package com.captainkray.krayscandles.tileentity.candle;

import com.captainkray.krayscandles.init.InitEffects;
import com.captainkray.krayscandles.init.InitTileEntityTypes;
import com.captainkray.krayscandles.tileentity.base.TileEntityCandleBase;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class TileEntityCandleBlessed extends TileEntityCandleBase {

    public TileEntityCandleBlessed() {
        super(InitTileEntityTypes.CANDLE_BLESSED.get());
    }

    @Override
    public EffectInstance[] getCandleEffects() {
        return new EffectInstance[] {new EffectInstance(InitEffects.FLIGHT.get())};
    }
}
