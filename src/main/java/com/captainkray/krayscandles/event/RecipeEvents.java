package com.captainkray.krayscandles.event;

import com.captainkray.krayscandles.init.InitItems;
import com.captainkray.krayscandles.item.base.ItemBase;
import com.captainkray.krayscandles.util.ItemHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RecipeEvents {

    @SubscribeEvent
    public void onCrafted(PlayerEvent.ItemCraftedEvent event) {

        for (int i = 0; i < event.getInventory().getSizeInventory(); i++) {

            ItemStack stackInSlot = event.getInventory().getStackInSlot(i);

            if (!ItemBase.hasTag(event.getCrafting().getItem(), "iron_press")) {

                if (ItemBase.hasTag(stackInSlot.getItem(), "iron_press")) {
                    ItemHelper.spawnStackAtEntity(event.getEntity().world, event.getEntity(), new ItemStack(InitItems.IRON_PRESS.get()));
                    return;
                }
            }
        }
    }
}
