package com.tm.krayscandles.init;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class InitRenderLayers {

    public static void init() {

        RenderTypeLookup.setRenderLayer(InitItems.SOYBEAN_CROP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(InitItems.STONE_ALTAR_TILE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(InitItems.STATUE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(InitItems.CANDLE_SOY_MOUNT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(InitItems.LANTERN_SOUL_TRAPPED.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(InitItems.CANDLE_BLESSED.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(InitItems.CANDLE_WAX_BEE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(InitItems.CANDLE_INVIS.get(), RenderType.getTranslucent());
    }
}
