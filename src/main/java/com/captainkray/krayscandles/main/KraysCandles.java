package com.captainkray.krayscandles.main;

import com.captainkray.krayscandles.client.render.RenderWraithDamned;
import com.captainkray.krayscandles.entity.EntityWraith;
import com.captainkray.krayscandles.entity.EntityWraithDamned;
import com.captainkray.krayscandles.init.*;
import com.captainkray.krayscandles.client.render.RenderCandleMount;
import com.captainkray.krayscandles.client.render.RenderStoneAlterTile;
import com.captainkray.krayscandles.client.render.RenderWraith;
import com.captainkray.krayscandles.ritual.RitualRecipes;
import com.captainkray.krayscandles.ritual.RitualStructureTypes;
import com.captainkray.krayscandles.tab.KCTabCandle;
import com.captainkray.krayscandles.tab.KCTabMain;
import com.captainkray.krayscandles.tab.KCTabTool;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
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
        InitSounds.SOUNDS.register(MOD_EVENT_BUS);
        InitEffects.POTION_TYPES.register(MOD_EVENT_BUS);
        InitTileEntityTypes.TILE_ENTITY_TYPES.register(MOD_EVENT_BUS);
        InitRecipes.RECIPES.register(MOD_EVENT_BUS);
        InitEntityTypes.ENTITY_TYPES.register(MOD_EVENT_BUS);
        InitParticles.PARTICLES.register(MOD_EVENT_BUS);
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        InitEvents.initCommon();

        DeferredWorkQueue.runLater(() -> {

            DispenserBlock.registerDispenseBehavior(Items.FLINT_AND_STEEL, new DispenserLightBehavior());

            RitualStructureTypes.init();
            RitualRecipes.init();

            GlobalEntityTypeAttributes.put(InitEntityTypes.WRAITH_FIRE.get(), EntityWraith.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(InitEntityTypes.WRAITH_WATER.get(), EntityWraith.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(InitEntityTypes.WRAITH_AIR.get(), EntityWraith.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(InitEntityTypes.WRAITH_EXPLOSION.get(), EntityWraith.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(InitEntityTypes.WRAITH_MAGIC.get(), EntityWraith.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(InitEntityTypes.WRAITH_MOB.get(), EntityWraith.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(InitEntityTypes.WRAITH_DAMNED.get(), EntityWraithDamned.setCustomAttributes().create());
        });
    }

    private void onClientSetup(final FMLClientSetupEvent event) {

        InitEvents.initClient();

        InitRenderLayers.init();

        ClientRegistry.bindTileEntityRenderer(InitTileEntityTypes.CANDLE_SOY_MOUNT.get(), RenderCandleMount::new);
        ClientRegistry.bindTileEntityRenderer(InitTileEntityTypes.STONE_ALTER_TILE.get(), RenderStoneAlterTile::new);

        ItemModelsProperties.registerProperty(InitItems.CANDLE_SOY_COLORED_ITEM.get(), new ResourceLocation("color"), (stack, world, player) -> stack.getDamage());

        RenderingRegistry.registerEntityRenderingHandler(InitEntityTypes.WRAITH_FIRE.get(), RenderWraith::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntityTypes.WRAITH_WATER.get(), RenderWraith::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntityTypes.WRAITH_AIR.get(), RenderWraith::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntityTypes.WRAITH_EXPLOSION.get(), RenderWraith::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntityTypes.WRAITH_MAGIC.get(), RenderWraith::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntityTypes.WRAITH_MOB.get(), RenderWraith::new);
        RenderingRegistry.registerEntityRenderingHandler(InitEntityTypes.WRAITH_DAMNED.get(), RenderWraithDamned::new);
    }
}
