package com.captainkray.krayscandles.event;

import com.captainkray.krayscandles.block.candle.BlockCandleSoyColored;
import com.captainkray.krayscandles.init.InitItems;
import net.minecraft.block.Block;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BlockDropEvents {

    @SubscribeEvent
    public void onColorBreakEvent(BlockEvent.BreakEvent event) {

        Block block = event.getState().getBlock();

        if (!event.getPlayer().isCreative() && block instanceof BlockCandleSoyColored) {

            World world = (World) event.getWorld();

            int colorID = event.getState().get(BlockCandleSoyColored.COLOR).getId();
            ItemStack stack = new ItemStack(InitItems.CANDLE_SOY_COLORED_ITEM.get());
            stack.setDamage(colorID);
            world.addEntity(new ItemEntity((World) event.getWorld(), event.getPos().getX() + 0.5D, event.getPos().getY() + 0.5D, event.getPos().getZ() + 0.5D, stack));
        }
    }
}
