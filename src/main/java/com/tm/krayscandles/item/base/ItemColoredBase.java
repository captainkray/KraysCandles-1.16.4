package com.tm.krayscandles.item.base;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;

public class ItemColoredBase implements IItemColor {

    private final int layer;

    public ItemColoredBase(int layer) {
        this.layer = layer;
    }

    @Override
    public int getColor(ItemStack stack, int layer) {

        if (layer == this.layer) {

            DyeColor dye = DyeColor.byId(stack.getDamage());
            return dye.getColorValue();
        }

        return 0xFFFFFF;
    }
}
