package com.captainkray.krayscandles.event;

import com.captainkray.krayscandles.init.InitItems;
import com.captainkray.krayscandles.item.base.ItemColoredBase;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ColorEvent {

    /**
     * Registers the coloring of the Items.
     */
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onColorRegister(final ColorHandlerEvent.Item event) {
        event.getItemColors().register(new ItemColoredBase(0), InitItems.WAX_CHUNK_SOY_COLORED.get());
        event.getItemColors().register(new ItemColoredBase(0), InitItems.WAX_CHUNK_SOY_SMALL_COLORED.get());
    }
}
