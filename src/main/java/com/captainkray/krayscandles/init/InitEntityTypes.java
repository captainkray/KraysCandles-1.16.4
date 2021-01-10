package com.captainkray.krayscandles.init;

import com.captainkray.krayscandles.entity.*;
import com.captainkray.krayscandles.main.KCReference;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitEntityTypes {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, KCReference.MOD_ID);

    public static final RegistryObject<EntityType<EntityWraithFire>> WRAITH_FIRE = regWraith("fire", EntityWraithFire::new);
    public static final RegistryObject<EntityType<EntityWraithWater>> WRAITH_WATER = regWraith("water", EntityWraithWater::new);
    public static final RegistryObject<EntityType<EntityWraithAir>> WRAITH_AIR = regWraith("air", EntityWraithAir::new);
    public static final RegistryObject<EntityType<EntityWraithExplosion>> WRAITH_EXPLOSION = regWraith("explosion", EntityWraithExplosion::new);
    public static final RegistryObject<EntityType<EntityWraithMagic>> WRAITH_MAGIC = regWraith("magic", EntityWraithMagic::new);
    public static final RegistryObject<EntityType<EntityWraithMob>> WRAITH_MOB = regWraith("mob", EntityWraithMob::new);

    public static <T extends EntityWraith> RegistryObject<EntityType<T>> regWraith(String type, EntityType.IFactory<T> sup) {
        return ENTITY_TYPES.register("wraith_" + type, () -> EntityType.Builder.create(sup, EntityClassification.MONSTER).size(0.8F, 1.8F).build(new ResourceLocation(KCReference.MOD_ID, "wraith_" + type).toString()));
    }
}
