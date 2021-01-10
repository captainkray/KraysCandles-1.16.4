package com.captainkray.krayscandles.ritual;

import com.captainkray.krayscandles.init.InitItems;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class RitualSoulCandleIngredient extends RitualIngredient {

    private final EntityType<?> soulType;

    public RitualSoulCandleIngredient(EntityType<?> soulType, int x, int y, int z) {
        super(InitItems.CANDLE_SOY.get(), x, y, z);
        this.soulType = soulType;
    }

    public RitualSoulCandleIngredient(EntityType<?> soulType, BlockPos offset) {
        super(InitItems.CANDLE_SOY.get().getDefaultState(), offset);
        this.soulType = soulType;
    }

    public EntityType<?> getSoulType() {
        return soulType;
    }

    @Override
    public RitualSoulCandleIngredient rotate(Rotation rotation) {
        return new RitualSoulCandleIngredient(soulType, getOffset().rotate(rotation));
    }
}
