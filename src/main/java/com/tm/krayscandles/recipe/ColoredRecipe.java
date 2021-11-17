package com.tm.krayscandles.recipe;

import com.google.common.collect.Lists;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.List;

public abstract class ColoredRecipe extends SpecialRecipe {

    public ColoredRecipe(ResourceLocation id) {
        super(id);
    }

    public abstract Item getItemToColor();
    public abstract Item getResultItem();

    @Override
    public boolean matches(CraftingInventory inv, World world) {

        int itemCount = 0;
        int dyeCount = 0;

        for (int i = 0; i < inv.getSizeInventory(); i++) {

            ItemStack stackInSlot = inv.getStackInSlot(i);

            if (stackInSlot.getItem() == getItemToColor()) {
                itemCount++;
            }

            else if (stackInSlot.getItem() instanceof DyeItem) {
                dyeCount++;
            }

            else if (!stackInSlot.isEmpty()) {
                return false;
            }
        }

        return itemCount == 1 && dyeCount == 1;
    }

    @Override
    public ItemStack getCraftingResult(CraftingInventory inv) {

        List<DyeItem> list = Lists.newArrayList();
        int colorID = 0;

        for (int i = 0; i < inv.getSizeInventory(); i++) {

            ItemStack stackInSlot = inv.getStackInSlot(i);

            if (stackInSlot.getItem() instanceof DyeItem) {
                DyeItem dye = (DyeItem) stackInSlot.getItem();
                colorID = dye.getDyeColor().getId();
            }
        }

        ItemStack result = new ItemStack(getResultItem());
        result.setDamage(colorID);

        return result;
    }

    @Override
    public boolean canFit(int width, int height) {
        return width * height >= 2;
    }
}