package com.captainkray.krayscandles.tileentity.candle;

import com.captainkray.krayscandles.init.InitTileEntityTypes;
        import com.captainkray.krayscandles.tileentity.base.TileEntityCandleBase;
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
