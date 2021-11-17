package com.tm.krayscandles.item.base;

import com.tm.krayscandles.main.KraysCandles;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class ItemFoodBase extends ItemBase {

    public ItemFoodBase(int hunger, float saturation) {
        super(new Item.Properties().group(KraysCandles.TAB_MAIN).food(new Food.Builder().hunger(hunger).saturation(saturation).setAlwaysEdible().build()));
    }
}
