package com.captainkray.krayscandles.main;

import com.captainkray.krayscandles.init.*;
import com.captainkray.krayscandles.render.RenderCandleMount;
import com.captainkray.krayscandles.render.RenderStoneAlterTile;
import com.captainkray.krayscandles.tab.KCTabCandle;
import com.captainkray.krayscandles.tab.KCTabMain;
import com.captainkray.krayscandles.tab.KCTabTool;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(KCReference.MOD_ID)
public class KraysCandles {

    public static KraysCandles instance;

    public static IEventBus MOD_EVENT_BUS;

    public static final ItemGroup TAB_MAIN = new KCTabMain();
    public static final ItemGroup TAB_CANDLE = new KCTabCandle();
    public static final ItemGroup TAB_TOOL = new KCTabTool();

    public KraysCandles() {

        instance = this;

        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        MOD_EVENT_BUS.addListener(this::onCommonSetup);
        MOD_EVENT_BUS.addListener(this::onClientSetup);

        InitItems.init();
        InitTileEntityTypes.TILE_ENTITY_TYPES.register(MOD_EVENT_BUS);
        InitRecipes.RECIPES.register(MOD_EVENT_BUS);
        InitParticles.PARTICLES.register(MOD_EVENT_BUS);
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        InitEvents.init();
    }

    private void onClientSetup(final FMLClientSetupEvent event) {

        InitRenderLayers.init();

        ClientRegistry.bindTileEntityRenderer(InitTileEntityTypes.CANDLE_SOY_MOUNT.get(), RenderCandleMount::new);
        ClientRegistry.bindTileEntityRenderer(InitTileEntityTypes.STONE_ALTER_TILE.get(), RenderStoneAlterTile::new);

        ItemModelsProperties.registerProperty(InitItems.CANDLE_SOY_COLORED_ITEM.get(), new ResourceLocation("color"), (stack, world, player) -> stack.getDamage());
    }
}
