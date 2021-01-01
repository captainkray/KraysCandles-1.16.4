package com.captainkray.krayscandles.init;

import com.captainkray.krayscandles.main.KCReference;
import com.captainkray.krayscandles.particle.ParticleSoulFlame;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = KCReference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class InitParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, KCReference.MOD_ID);

    public static final RegistryObject<BasicParticleType> SOUL_FLAME = PARTICLES.register("soul_flame", () -> new BasicParticleType(true));

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticles(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particles.registerFactory(SOUL_FLAME.get(), ParticleSoulFlame.Factory::new);

    }
}
