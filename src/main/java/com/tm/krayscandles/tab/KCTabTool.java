package com.tm.krayscandles.tab;

import com.tm.krayscandles.main.KCReference;
import com.tm.krayscandles.init.InitItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class KCTabTool extends ItemGroup {

    public KCTabTool() {
        super(KCReference.MOD_ID + ".tabTool");
    }

    @Override
    public ItemStack createIcon () {
        ItemStack stack = new ItemStack(InitItems.WAND_LEVITATION.get());
        stack.setDamage(14);
        return stack;
    }
}
