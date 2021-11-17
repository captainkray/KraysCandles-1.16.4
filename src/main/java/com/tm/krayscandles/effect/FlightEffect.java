package com.tm.krayscandles.effect;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.ArrayList;
import java.util.List;

public class FlightEffect extends Effect {

    public FlightEffect() {
        super(EffectType.BENEFICIAL, 0);
    }

    @Override
    public String getName() {
        return "Flight";
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return new ArrayList<>();
    }
}
