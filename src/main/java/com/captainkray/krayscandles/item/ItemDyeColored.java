package com.captainkray.krayscandles.item;

import com.captainkray.krayscandles.main.KraysCandles;
import com.captainkray.krayscandles.item.base.ItemBase;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ItemDyeColored extends ItemBase {

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        return new TranslationTextComponent(this.getTranslationKey(stack) + "_" + DyeColor.byId(stack.getDamage()).getTranslationKey());
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {

        if (group == KraysCandles.TAB_MAIN) {

            for (int i = 0; i < DyeColor.values().length; i++) {

                ItemStack stack = new ItemStack(this);
                stack.setDamage(i);

                items.add(stack);
            }
        }
    }
}
