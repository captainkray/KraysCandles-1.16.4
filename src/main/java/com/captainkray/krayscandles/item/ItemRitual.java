package com.captainkray.krayscandles.item;

import com.captainkray.krayscandles.item.base.ItemBase;
import com.captainkray.krayscandles.ritual.RitualRecipe;
import com.captainkray.krayscandles.util.IRitualItem;

public class ItemRitual extends ItemBase implements IRitualItem {

    private RitualRecipe ritualRecipe;

    public ItemRitual(RitualRecipe ritualRecipe) {
        this.ritualRecipe = ritualRecipe;
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return ritualRecipe;
    }
}
