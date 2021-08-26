package com.captainkray.krayscandles.init;

import com.captainkray.krayscandles.event.*;
import net.minecraftforge.common.MinecraftForge;

public class InitEvents {

    public static void initCommon() {
        MinecraftForge.EVENT_BUS.register(new RitualEvent());
        MinecraftForge.EVENT_BUS.register(new WeaponKillEvents());
        MinecraftForge.EVENT_BUS.register(new WraithSpawnEvents());
        MinecraftForge.EVENT_BUS.register(new RecipeEvents());
        MinecraftForge.EVENT_BUS.register(new BlockDropEvents());
        MinecraftForge.EVENT_BUS.register(new EntityDropEvents());
        MinecraftForge.EVENT_BUS.register(new EffectEvents());
        MinecraftForge.EVENT_BUS.register(new VampireSpawnEvents());
        MinecraftForge.EVENT_BUS.register(new VampireEvents());


    }

    public static void initClient() {
        MinecraftForge.EVENT_BUS.register(new LoreEvents());
    }
}
