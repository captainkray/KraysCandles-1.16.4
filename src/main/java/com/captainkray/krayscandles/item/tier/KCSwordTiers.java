package com.captainkray.krayscandles.item.tier;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum KCSwordTiers implements IItemTier {

    NIGHT_BLADE (200, 8F, 14, 1.2F, () -> null),
    SCALPEL     (50, 5F, 10, 0.9F, () -> null),
    WAX         (50, 2.5F, 12, 1.5F, () -> null);

    public final int durability;
    public final float efficiency;
    public final float attackDamage;
    public final int harvestLevel;
    public final int enchantability;
    public final float attackSpeed;
    public final LazyValue<Ingredient> repairMaterial;

    KCSwordTiers(int durability, float attackDamage, int enchantability, float attackSpeed, Supplier<Ingredient> repairMaterial) {
        this.durability = durability;
        this.efficiency = 5F;
        this.attackDamage = attackDamage;
        this.harvestLevel = 0;
        this.enchantability = enchantability;
        this.attackSpeed = attackSpeed;
        this.repairMaterial = new LazyValue<>(repairMaterial);
    }

    @Override
    public int getMaxUses () {
        return durability;
    }

    @Override
    public float getEfficiency () {
        return efficiency;
    }

    @Override
    public float getAttackDamage () {
        return attackDamage;
    }

    @Override
    public int getHarvestLevel () {
        return harvestLevel;
    }

    @Override
    public int getEnchantability () {
        return enchantability;
    }

    @Override
    public Ingredient getRepairMaterial () {
        return repairMaterial.getValue();
    }
}
