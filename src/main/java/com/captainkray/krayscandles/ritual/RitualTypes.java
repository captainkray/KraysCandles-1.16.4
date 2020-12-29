package com.captainkray.krayscandles.ritual;

import com.captainkray.krayscandles.init.InitItems;
import net.minecraft.item.ItemStack;

public class RitualTypes {

    public static final Ritual RITUAL_GREATER_SOUL_ESSENCE = new Ritual();
    public static final Ritual RITUAL_BLESSED_SOUL_ESSENCE = new Ritual();

    public static final Ritual RITUAL_CANDLE = new Ritual();

    static {
        RITUAL_GREATER_SOUL_ESSENCE.addIngredient(new RitualIngredient(InitItems.STONE_ALTAR_TILE.get(), 0, 0, 0));
        RITUAL_GREATER_SOUL_ESSENCE.addSymmetricalIngredient(new RitualIngredient(InitItems.CANDLE_CURSED.get(), 1, 0, 1));

        RITUAL_BLESSED_SOUL_ESSENCE.addIngredient(new RitualIngredient(InitItems.STATUE.get(), 0, 0, 0));
        RITUAL_BLESSED_SOUL_ESSENCE.addSymmetricalIngredient(new RitualIngredient(InitItems.CANDLE_PURGED.get(), 0, 0, 2));
        RITUAL_BLESSED_SOUL_ESSENCE.addSymmetricalIngredient(new RitualIngredient(InitItems.CANDLE_PURGED.get(), 1, 0, 1));

        RITUAL_CANDLE.addIngredient(new RitualIngredient(InitItems.CANDLE_SOY.get(), 0, 0, 0));
        RITUAL_CANDLE.addSymmetricalIngredient(new RitualAlterIngredient(new ItemStack(InitItems.WAX_CHUNK_CURSED_SMALL.get()), 3, 0, 0));
        RITUAL_CANDLE.addSymmetricalIngredient(new RitualAlterIngredient(new ItemStack(InitItems.WAX_CHUNK_CURSED_SMALL.get()), 2, 0, 2));
        RITUAL_CANDLE.addSymmetricalIngredient(new RitualCandleIngredient(3, 0, 1));
        RITUAL_CANDLE.addSymmetricalIngredient(new RitualCandleIngredient(1, 0, 3));
    }
}
