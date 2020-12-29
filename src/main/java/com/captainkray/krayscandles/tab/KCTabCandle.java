package com.captainkray.krayscandles.tab;

import com.captainkray.krayscandles.main.KCReference;
import com.captainkray.krayscandles.init.InitItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class KCTabCandle extends ItemGroup {

    public KCTabCandle() {
        super(KCReference.MOD_ID + ".tabCandle");
    }

    @Override
    public ItemStack createIcon () {
        ItemStack stack = new ItemStack(InitItems.CANDLE_SOY_COLORED_ITEM.get());
        stack.setDamage(14);
        return stack;
    }
}
