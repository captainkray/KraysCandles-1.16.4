package com.captainkray.krayscandles.recipe;

import com.captainkray.krayscandles.init.InitItems;
import com.captainkray.krayscandles.init.InitRecipes;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;

public class SoyCandleColorRecipe extends ColoredRecipe {

    public SoyCandleColorRecipe(ResourceLocation id) {
        super(id);
    }

    @Override
    public Item getItemToColor() {
        return InitItems.CANDLE_SOY.get().asItem();
    }

    @Override
    public Item getResultItem() {
        return InitItems.CANDLE_SOY_COLORED_ITEM.get();
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return InitRecipes.CANDLE_SOY_COLORED.get();
    }
}
