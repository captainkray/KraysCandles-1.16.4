package com.captainkray.krayscandles.init;

import com.captainkray.krayscandles.main.KCReference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, KCReference.MOD_ID);
    public static final RegistryObject<SoundEvent> WRAITH_DEATH = SOUNDS.register("entity.wraith_death", () -> new SoundEvent(new ResourceLocation(KCReference.MOD_ID, "entity.wraith_death")));
    public static final RegistryObject<SoundEvent> WRAITH_AMBIENT = SOUNDS.register("entity.wraith_ambient", () -> new SoundEvent(new ResourceLocation(KCReference.MOD_ID, "entity.wraith_ambient")));
    public static final RegistryObject<SoundEvent> WRAITH_HURT = SOUNDS.register("entity.wraith_hurt", () -> new SoundEvent(new ResourceLocation(KCReference.MOD_ID, "entity.wraith_hurt")));

}
