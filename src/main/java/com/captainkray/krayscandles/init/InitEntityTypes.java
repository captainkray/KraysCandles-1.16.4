package com.captainkray.krayscandles.init;

import com.captainkray.krayscandles.entity.EntityWraith;
import com.captainkray.krayscandles.main.KCReference;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitEntityTypes {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, KCReference.MOD_ID);

    public static final RegistryObject<EntityType<EntityWraith>> WRAITH = ENTITY_TYPES.register("wraith",
            () -> EntityType.Builder.<EntityWraith>create(EntityWraith::new, EntityClassification.MONSTER)
                    .size(0.8F, 1.8F)
                    .build(new ResourceLocation(KCReference.MOD_ID, "wraith").toString()));
}
