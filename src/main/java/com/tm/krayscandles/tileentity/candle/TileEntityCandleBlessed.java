package com.tm.krayscandles.tileentity.candle;

import com.tm.krayscandles.init.InitEffects;
import com.tm.krayscandles.init.InitTileEntityTypes;
import com.tm.krayscandles.tileentity.base.TileEntityCandleBase;
import net.minecraft.potion.EffectInstance;

public class TileEntityCandleBlessed extends TileEntityCandleBase {

    public TileEntityCandleBlessed() {
        super(InitTileEntityTypes.CANDLE_BLESSED.get());
    }

    @Override
    public EffectInstance[] getCandleEffects() {
        return new EffectInstance[] {new EffectInstance(InitEffects.FLIGHT.get())};
    }
}
