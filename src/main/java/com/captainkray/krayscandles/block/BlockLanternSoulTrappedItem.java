package com.captainkray.krayscandles.block;

import com.captainkray.krayscandles.util.ItemHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class BlockLanternSoulTrappedItem extends BlockItem {

    public BlockLanternSoulTrappedItem(Block block) {
        super(block, new Item.Properties());
    }

    public static void trapSoul(ItemStack stack, LivingEntity entity) {
        CompoundNBT nbt = ItemHelper.getNBT(stack);
        nbt.putString("soul_type", EntityType.getKey(entity.getType()).toString());
        nbt.putString("soul_name", entity.getName().getString());
    }
}
