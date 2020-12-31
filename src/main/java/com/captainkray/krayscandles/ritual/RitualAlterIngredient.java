package com.captainkray.krayscandles.ritual;

import com.captainkray.krayscandles.init.InitItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class RitualAlterIngredient extends RitualIngredient {

    private final ItemStack ritualItem;

    public RitualAlterIngredient(ItemStack ritualItem, int x, int y, int z) {
        super(InitItems.STONE_ALTAR_TILE.get(), x, y, z);
        this.ritualItem = ritualItem;
    }

    public RitualAlterIngredient(ItemStack ritualItem, BlockPos offset) {
        super(InitItems.STONE_ALTAR_TILE.get().getDefaultState(), offset);
        this.ritualItem = ritualItem;
    }

    public ItemStack getRitualItem() {
        return ritualItem;
    }

    @Override
    public RitualAlterIngredient rotate(Rotation rotation) {
        return new RitualAlterIngredient(ritualItem, getOffset().rotate(rotation));
    }
}
