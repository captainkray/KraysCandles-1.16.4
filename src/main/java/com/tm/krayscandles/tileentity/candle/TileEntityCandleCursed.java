package com.tm.krayscandles.tileentity.candle;

import com.tm.krayscandles.init.InitTileEntityTypes;
        import com.tm.krayscandles.tileentity.base.TileEntityCandleBase;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class TileEntityCandleCursed extends TileEntityCandleBase {

    public TileEntityCandleCursed() {
        super(InitTileEntityTypes.CANDLE_CURSED.get());
    }

    @Override
    public EffectInstance[] getCandleEffects() {
        return new EffectInstance[] {new EffectInstance(Effects.BLINDNESS)};
    }
}
