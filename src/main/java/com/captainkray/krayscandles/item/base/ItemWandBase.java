package com.captainkray.krayscandles.item.base;

import com.captainkray.krayscandles.main.KraysCandles;
import com.captainkray.krayscandles.util.IRitualItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public abstract class ItemWandBase extends ItemBase implements IRitualItem {

    public ItemWandBase() {
        super(new Item.Properties().group(KraysCandles.TAB_TOOL).maxStackSize(1));
    }

    public abstract int getCooldown();

    /**
     * Calls when a Wand is right-clicked. Return true if something happened (starts cooldown).
     */
    public abstract boolean castWand(World world, PlayerEntity player);

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack heldStack = player.getHeldItem(hand);

        if (castWand(world, player)) {
            player.getCooldownTracker().setCooldown(this, getCooldown() * 20);
            return ActionResult.resultSuccess(heldStack);
        }

        else return ActionResult.resultFail(heldStack);
    }
}
