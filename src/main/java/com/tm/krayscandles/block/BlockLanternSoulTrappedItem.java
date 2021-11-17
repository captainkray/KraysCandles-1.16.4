package com.tm.krayscandles.block;

import com.tm.api.calemicore.util.helper.ItemHelper;
import com.tm.krayscandles.init.InitItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class BlockLanternSoulTrappedItem extends BlockItem {

    public BlockLanternSoulTrappedItem(Block block) {
        super(block, new Item.Properties());
    }

    public static String getSoulType(ItemStack stack) {
        CompoundNBT nbt = ItemHelper.getNBT(stack);
        return nbt.getString("soul_type");
    }

    public static String getSoulName(ItemStack stack) {
        CompoundNBT nbt = ItemHelper.getNBT(stack);
        return nbt.getString("soul_name");
    }

    public static void trapSoul(PlayerEntity player, ItemStack stack, EntityType<?> entityType) {

        ItemStack lanternSoulTrapped = new ItemStack(InitItems.LANTERN_SOUL_TRAPPED.get().asItem());

        stack.shrink(1);
        ItemHelper.spawnStackAtEntity(player.world, player, lanternSoulTrapped);

        setSoul(lanternSoulTrapped, entityType);
    }

    public static void setSoul(ItemStack stack, EntityType<?> entityType) {

        if (entityType != null) {

            CompoundNBT nbt = ItemHelper.getNBT(stack);

            nbt.putString("soul_type", EntityType.getKey(entityType).toString());
            nbt.putString("soul_name", entityType.getName().getString());
        }
    }
}
