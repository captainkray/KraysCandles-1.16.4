package com.tm.krayscandles.init;

import com.tm.krayscandles.main.KCReference;
import com.tm.krayscandles.recipe.SoyCandleColorRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitRecipes {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, KCReference.MOD_ID);
    public static final RegistryObject<IRecipeSerializer<SoyCandleColorRecipe>> CANDLE_SOY_COLORED = RECIPES.register("candle_soy_colored", () -> new SpecialRecipeSerializer<>(SoyCandleColorRecipe::new));

}
