package com.captainkray.krayscandles.event;

import com.captainkray.krayscandles.ritual.RitualRecipe;
import com.captainkray.krayscandles.util.IRitualItem;
import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class LoreEvents {

    @SubscribeEvent
    public void onRitual(ItemTooltipEvent event) {

        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();

        if (item instanceof IRitualItem || (item instanceof BlockItem && Block.getBlockFromItem(item) instanceof IRitualItem)) {

            IRitualItem ritual;

            if (item instanceof BlockItem) {
                ritual = (IRitualItem) Block.getBlockFromItem(item);
            }

            else ritual = (IRitualItem) item;

            RitualRecipe recipe = ritual.getRitualRecipe();

            if (recipe != null) {

                if (Screen.hasShiftDown()) {

                    event.getToolTip().add(new StringTextComponent(TextFormatting.GRAY + "Made in the " + TextFormatting.LIGHT_PURPLE + recipe.getRitualStructure().getName() + " Ritual"));
                    event.getToolTip().add(new StringTextComponent(TextFormatting.GRAY + "Use item in center: "));
                    event.getToolTip().add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + recipe.getHandItem().getItem().getName().getString()));
                    if (!recipe.getAlterItems().isEmpty()) event.getToolTip().add(new StringTextComponent(TextFormatting.GRAY + "Place in alters:"));

                    List<Item> passedItems = new ArrayList<>();

                    for (Item alterItem : recipe.getAlterItems()) {

                        if (passedItems.contains(alterItem)) {
                            continue;
                        }

                        passedItems.add(alterItem);

                        int count = 0;

                        for (Item other : recipe.getAlterItems()) {

                            if (alterItem.equals(other)) {
                                count++;
                            }
                        }

                        event.getToolTip().add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + alterItem.getName().getString() + TextFormatting.DARK_PURPLE + " x" + count));
                    }
                }

                else {
                    event.getToolTip().add(new StringTextComponent(TextFormatting.GRAY + "[" + TextFormatting.LIGHT_PURPLE + "Shift" + TextFormatting.GRAY + "] Recipe"));
                }
            }
        }
    }
}















