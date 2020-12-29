package com.captainkray.krayscandles.recipe;

import com.captainkray.krayscandles.init.InitItems;
import com.captainkray.krayscandles.init.InitRecipes;
import com.google.common.collect.Lists;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.List;

public class SmallWaxChunkRecipe extends SpecialRecipe {

    public SmallWaxChunkRecipe(ResourceLocation id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inv, World world) {

        int emptyCount = 0;
        int itemCount = 0;

        for (int i = 0; i < inv.getSizeInventory(); i++) {

            ItemStack stackInSlot = inv.getStackInSlot(i);

            if (stackInSlot.getItem() == InitItems.WAX_CHUNK_SOY_COLORED.get()) {
                itemCount++;
            }

            else if (stackInSlot.isEmpty()) {
                emptyCount++;
            }
        }

        return emptyCount == 8 && itemCount == 1;
    }

    @Override
    public ItemStack getCraftingResult(CraftingInventory inv) {

        List<DyeItem> list = Lists.newArrayList();
        int colorID = 0;

        for (int i = 0; i < inv.getSizeInventory(); i++) {

            ItemStack stackInSlot = inv.getStackInSlot(i);

            if (stackInSlot.getItem() == InitItems.WAX_CHUNK_SOY_COLORED.get()) {
                colorID = stackInSlot.getDamage();
            }
        }

        ItemStack result = new ItemStack(InitItems.WAX_CHUNK_SOY_SMALL_COLORED.get());
        result.setDamage(colorID);

        return result;
    }

    @Override
    public boolean canFit(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return InitRecipes.WAX_CHUNK_SOY_SMALL_COLORED.get();
    }
}