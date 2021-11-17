package com.tm.api.calemicore.inventory;

import com.tm.api.calemicore.tileentity.CCItemHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * CUItemHandler variant.
 * This slot can prevent certain Items from being placed in it.
 */
public class SlotFilter extends SlotItemHandler {

    private final List<Item> itemFilters;

    public SlotFilter (CCItemHandler itemHandler, int id, int x, int y, Item... filters) {
        super(itemHandler, id, x, y);
        this.itemFilters = new ArrayList<>();
        this.itemFilters.addAll(Arrays.asList(filters));
    }

    @Override
    public boolean isItemValid (ItemStack stack) {
        return isFilter(stack);
    }

    private boolean isFilter (ItemStack stack) {

        if (this.itemFilters != null) {
            for (Item itemFilter : this.itemFilters) {
                if (itemFilter == stack.getItem()) return true;
            }
        }

        return false;
    }
}
