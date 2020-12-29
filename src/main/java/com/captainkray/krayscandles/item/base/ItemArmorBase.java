package com.captainkray.krayscandles.item.base;

import com.captainkray.krayscandles.main.KraysCandles;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;

public class ItemArmorBase extends ArmorItem {

    public ItemArmorBase(IArmorMaterial materialIn, EquipmentSlotType slot) {
        super(materialIn, slot, new Item.Properties().group(KraysCandles.TAB_TOOL));
    }
}
