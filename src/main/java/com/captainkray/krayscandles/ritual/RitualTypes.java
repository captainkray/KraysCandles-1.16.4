package com.captainkray.krayscandles.ritual;

import com.captainkray.krayscandles.init.InitItems;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class RitualTypes {

    public static final Ritual RITUAL_ESSENCE = new Ritual();

    public static final Ritual RITUAL_RUNE = new Ritual();
    public static final Ritual RITUAL_BLESSED = new Ritual();
    public static final Ritual RITUAL_CURSED = new Ritual();
    public static final Ritual RITUAL_PURGED = new Ritual();
    public static final Ritual RITUAL_FIRE = new Ritual();
    public static final Ritual RITUAL_LEVITATE = new Ritual();
    public static final Ritual RITUAL_ZEN = new Ritual();
    public static final Ritual RITUAL_INVIS = new Ritual();
    public static final Ritual RITUAL_LUCK = new Ritual();
    public static final Ritual RITUAL_ENERGY = new Ritual();
    public static final Ritual RITUAL_MINING = new Ritual();

    public static final Ritual RITUAL_CANDLE = new Ritual();

    static {

        RITUAL_ESSENCE.addIngredient(new RitualIngredient(InitItems.CANDLE_WAX_BEE.get(), 0, 0, 0));
        RITUAL_ESSENCE.addSymmetricalIngredient(new RitualCandleIngredient(1, 0, 1));

        RITUAL_RUNE.addIngredient(new RitualIngredient(InitItems.STATUE.get(), 0, 0, 0));
        RITUAL_RUNE.addSymmetricalIngredient(new RitualCandleIngredient(2, 0, 2));
        RITUAL_RUNE.addIngredient(new RitualAlterIngredient(new ItemStack(Items.DIAMOND), 2, 0, 0));
        RITUAL_RUNE.addIngredient(new RitualAlterIngredient(new ItemStack(InitItems.SOUL_ESSENCE_GREATER.get()), 0, 0, 2));
        RITUAL_RUNE.addIngredient(new RitualAlterIngredient(new ItemStack(InitItems.SOUL_ESSENCE_GREATER.get()), 0, 0, -2));

        RITUAL_BLESSED.addRitual(RITUAL_RUNE);
        RITUAL_BLESSED.addIngredient(new RitualAlterIngredient(new ItemStack(Items.ELYTRA), -2, 0, 0));
        RITUAL_CURSED.addRitual(RITUAL_RUNE);
        RITUAL_CURSED.addIngredient(new RitualAlterIngredient(new ItemStack(InitItems.FLYING_BAT_EYEBALL.get()), -2, 0, 0));
        RITUAL_PURGED.addRitual(RITUAL_RUNE);
        RITUAL_PURGED.addIngredient(new RitualAlterIngredient(new ItemStack(Items.PRISMARINE_CRYSTALS), -2, 0, 0));
        RITUAL_FIRE.addRitual(RITUAL_RUNE);
        RITUAL_FIRE.addIngredient(new RitualAlterIngredient(new ItemStack(Items.BLAZE_POWDER), -2, 0, 0));
        RITUAL_LEVITATE.addRitual(RITUAL_RUNE);
        RITUAL_LEVITATE.addIngredient(new RitualAlterIngredient(new ItemStack(Items.SHULKER_SHELL), -2, 0, 0));
        RITUAL_ZEN.addRitual(RITUAL_RUNE);
        RITUAL_ZEN.addIngredient(new RitualAlterIngredient(new ItemStack(Items.GOLDEN_APPLE), -2, 0, 0));
        RITUAL_INVIS.addRitual(RITUAL_RUNE);
        RITUAL_INVIS.addIngredient(new RitualAlterIngredient(new ItemStack(Items.ENDER_EYE), -2, 0, 0));
        RITUAL_LUCK.addRitual(RITUAL_RUNE);
        RITUAL_LUCK.addIngredient(new RitualAlterIngredient(new ItemStack(Items.RABBIT_FOOT), -2, 0, 0));
        RITUAL_ENERGY.addRitual(RITUAL_RUNE);
        RITUAL_ENERGY.addIngredient(new RitualAlterIngredient(new ItemStack(Blocks.REDSTONE_BLOCK), -2, 0, 0));
        RITUAL_MINING.addRitual(RITUAL_RUNE);
        RITUAL_MINING.addIngredient(new RitualAlterIngredient(new ItemStack(Blocks.TNT), -2, 0, 0));

        RITUAL_CANDLE.addIngredient(new RitualIngredient(InitItems.CANDLE_SOY.get(), 0, 0, 0));
        RITUAL_CANDLE.addSymmetricalIngredient(new RitualAlterIngredient(new ItemStack(InitItems.WAX_CHUNK_CURSED_SMALL.get()), 3, 0, 0));
        RITUAL_CANDLE.addSymmetricalIngredient(new RitualAlterIngredient(new ItemStack(InitItems.WAX_CHUNK_CURSED_SMALL.get()), 2, 0, 2));
        RITUAL_CANDLE.addSymmetricalIngredient(new RitualCandleIngredient(3, 0, 1));
        RITUAL_CANDLE.addSymmetricalIngredient(new RitualCandleIngredient(1, 0, 3));
    }
}
