package com.captainkray.krayscandles.init;

import com.captainkray.krayscandles.main.KCReference;
import com.captainkray.krayscandles.tileentity.TileEntityCandleMount;
import com.captainkray.krayscandles.tileentity.TileEntityLanternSoulTrapped;
import com.captainkray.krayscandles.tileentity.TileEntityStoneAltarTile;
import com.captainkray.krayscandles.tileentity.candle.*;
import com.google.common.collect.Sets;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitTileEntityTypes {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, KCReference.MOD_ID);

    public static final RegistryObject<TileEntityType<TileEntityCandleSoy>> IRON_CHEST = TILE_ENTITY_TYPES.register(
            "iron_chest", () -> new TileEntityType<>(TileEntityCandleSoy::new, Sets.newHashSet(InitItems.CANDLE_SOY.get()), null));

    public static final RegistryObject<TileEntityType<TileEntityCandleSoy>> CANDLE_SOY = TILE_ENTITY_TYPES.register(
            "candle_soy", () -> new TileEntityType<>(TileEntityCandleSoy::new, Sets.newHashSet(InitItems.CANDLE_SOY.get(), InitItems.CANDLE_SOY_COLORED.get()), null));
    public static final RegistryObject<TileEntityType<TileEntityCandleBlessed>> CANDLE_BLESSED = TILE_ENTITY_TYPES.register(
            "candle_blessed", () -> new TileEntityType<>(TileEntityCandleBlessed::new, Sets.newHashSet(InitItems.CANDLE_BLESSED.get()), null));
    public static final RegistryObject<TileEntityType<TileEntityCandleCursed>> CANDLE_CURSED = TILE_ENTITY_TYPES.register(
            "candle_cursed", () -> new TileEntityType<>(TileEntityCandleCursed::new, Sets.newHashSet(InitItems.CANDLE_CURSED.get()), null));
    public static final RegistryObject<TileEntityType<TileEntityCandlePurged>> CANDLE_PURGED = TILE_ENTITY_TYPES.register(
            "candle_purged", () -> new TileEntityType<>(TileEntityCandlePurged::new, Sets.newHashSet(InitItems.CANDLE_PURGED.get()), null));
    public static final RegistryObject<TileEntityType<TileEntityCandleFire>> CANDLE_FIRE = TILE_ENTITY_TYPES.register(
            "candle_fire", () -> new TileEntityType<>(TileEntityCandleFire::new, Sets.newHashSet(InitItems.CANDLE_FIRE.get()), null));
    public static final RegistryObject<TileEntityType<TileEntityCandleLevitate>> CANDLE_LEVITATE = TILE_ENTITY_TYPES.register(
            "candle_levitate", () -> new TileEntityType<>(TileEntityCandleLevitate::new, Sets.newHashSet(InitItems.CANDLE_LEVITATE.get()), null));
    public static final RegistryObject<TileEntityType<TileEntityCandleZen>> CANDLE_ZEN = TILE_ENTITY_TYPES.register(
            "candle_zen", () -> new TileEntityType<>(TileEntityCandleZen::new, Sets.newHashSet(InitItems.CANDLE_ZEN.get()), null));
    public static final RegistryObject<TileEntityType<TileEntityCandleInvis>> CANDLE_INVIS = TILE_ENTITY_TYPES.register(
            "candle_invis", () -> new TileEntityType<>(TileEntityCandleInvis::new, Sets.newHashSet(InitItems.CANDLE_INVIS.get()), null));
    public static final RegistryObject<TileEntityType<TileEntityCandleLuck>> CANDLE_LUCK = TILE_ENTITY_TYPES.register(
            "candle_luck", () -> new TileEntityType<>(TileEntityCandleLuck::new, Sets.newHashSet(InitItems.CANDLE_LUCK.get()), null));
    public static final RegistryObject<TileEntityType<TileEntityCandleEnergy>> CANDLE_ENERGY = TILE_ENTITY_TYPES.register(
            "candle_energy", () -> new TileEntityType<>(TileEntityCandleEnergy::new, Sets.newHashSet(InitItems.CANDLE_ENERGY.get()), null));
    public static final RegistryObject<TileEntityType<TileEntityCandleCavern>> CANDLE_CAVERN = TILE_ENTITY_TYPES.register(
            "candle_cavern", () -> new TileEntityType<>(TileEntityCandleCavern::new, Sets.newHashSet(InitItems.CANDLE_CAVERN.get()), null));
    public static final RegistryObject<TileEntityType<TileEntityCandleNull>> CANDLE_NULL = TILE_ENTITY_TYPES.register(
            "candle_null", () -> new TileEntityType<>(TileEntityCandleNull::new, Sets.newHashSet(InitItems.CANDLE_NULL.get()), null));
    public static final RegistryObject<TileEntityType<TileEntityCandleWaxBee>> CANDLE_WAX_BEE = TILE_ENTITY_TYPES.register(
            "candle_wax_bee", () -> new TileEntityType<>(TileEntityCandleWaxBee::new, Sets.newHashSet(InitItems.CANDLE_WAX_BEE.get()), null));
    public static final RegistryObject<TileEntityType<TileEntityCandleWaxEar>> CANDLE_WAX_EAR = TILE_ENTITY_TYPES.register(
            "candle_wax_ear", () -> new TileEntityType<>(TileEntityCandleWaxEar::new, Sets.newHashSet(InitItems.CANDLE_WAX_EAR.get()), null));

    public static final RegistryObject<TileEntityType<TileEntityCandleMount>> CANDLE_SOY_MOUNT = TILE_ENTITY_TYPES.register(
            "candle_soy_mount", () -> new TileEntityType<>(TileEntityCandleMount::new, Sets.newHashSet(InitItems.CANDLE_SOY_MOUNT.get()), null));

    public static final RegistryObject<TileEntityType<TileEntityLanternSoulTrapped>> LANTERN_SOUL_TRAPPED = TILE_ENTITY_TYPES.register(
            "lantern_soul_trapped", () -> new TileEntityType<>(TileEntityLanternSoulTrapped::new, Sets.newHashSet(InitItems.LANTERN_SOUL_TRAPPED.get()), null));

    public static final RegistryObject<TileEntityType<TileEntityStoneAltarTile>> STONE_ALTAR_TILE = TILE_ENTITY_TYPES.register(
            "stone_alter_tile", () -> new TileEntityType<>(TileEntityStoneAltarTile::new, Sets.newHashSet(InitItems.STONE_ALTAR_TILE.get()), null));
}

