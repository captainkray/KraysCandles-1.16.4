package com.captainkray.krayscandles.item.base;

import com.captainkray.krayscandles.main.KraysCandles;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBase extends Item {

    private String tag = "";

    public ItemBase(Properties properties) {
        super(properties);
    }

    public ItemBase(ItemGroup tab) {
        super(new Item.Properties().group(tab));
    }

    public ItemBase() {
        super(new Item.Properties().group(KraysCandles.TAB_MAIN));
    }

    public ItemBase setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public static boolean hasTag(Item item, String tag) {

        if (item instanceof ItemBase) {
            return ((ItemBase)item).tag.equalsIgnoreCase(tag);
        }

        return false;
    }
}
