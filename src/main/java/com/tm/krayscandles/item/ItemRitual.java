package com.tm.krayscandles.item;

import com.tm.krayscandles.item.base.ItemBase;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.util.IRitualItem;

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
