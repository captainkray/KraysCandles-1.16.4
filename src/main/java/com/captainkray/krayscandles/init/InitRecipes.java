package com.captainkray.krayscandles.init;

import com.captainkray.krayscandles.main.KCReference;
import com.captainkray.krayscandles.recipe.SmallWaxChunkRecipe;
import com.captainkray.krayscandles.recipe.SoyCandleColorRecipe;
import com.captainkray.krayscandles.recipe.WaxChunkColorRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitRecipes {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, KCReference.MOD_ID);

    public static final RegistryObject<IRecipeSerializer<WaxChunkColorRecipe>> WAX_CHUNK_SOY_COLORED = RECIPES.register("wax_chunk_soy_colored", () -> new SpecialRecipeSerializer<>(WaxChunkColorRecipe::new));
    public static final RegistryObject<IRecipeSerializer<SmallWaxChunkRecipe>> WAX_CHUNK_SOY_SMALL_COLORED = RECIPES.register("wax_chunk_soy_small_colored", () -> new SpecialRecipeSerializer<>(SmallWaxChunkRecipe::new));
    public static final RegistryObject<IRecipeSerializer<SoyCandleColorRecipe>> CANDLE_SOY_COLORED = RECIPES.register("candle_soy_colored", () -> new SpecialRecipeSerializer<>(SoyCandleColorRecipe::new));

}
