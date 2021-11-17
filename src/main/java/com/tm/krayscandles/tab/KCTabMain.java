package com.tm.krayscandles.tab;

import com.tm.krayscandles.main.KCReference;
import com.tm.krayscandles.init.InitItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class KCTabMain extends ItemGroup {

    public KCTabMain() {
        super(KCReference.MOD_ID + ".tabMain");
    }

    @Override
    public ItemStack createIcon () {
        ItemStack stack = new ItemStack(InitItems.RUNE_GREAT_FIRE.get());
        stack.setDamage(14);
        return stack;
    }
}
