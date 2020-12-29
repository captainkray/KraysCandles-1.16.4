package com.captainkray.krayscandles.recipe;

import com.captainkray.krayscandles.init.InitItems;
import com.captainkray.krayscandles.init.InitRecipes;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;

public class WaxChunkColorRecipe extends ColoredRecipe {

    public WaxChunkColorRecipe(ResourceLocation id) {
        super(id);
    }

    @Override
    public Item getItemToColor() {
        return InitItems.WAX_CHUNK_SOY.get();
    }

    @Override
    public Item getResultItem() {
        return InitItems.WAX_CHUNK_SOY_COLORED.get();
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return InitRecipes.WAX_CHUNK_SOY_COLORED.get();
    }
}
