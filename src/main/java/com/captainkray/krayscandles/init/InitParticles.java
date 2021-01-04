package com.captainkray.krayscandles.init;

import com.captainkray.krayscandles.main.KCReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.FlameParticle;
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

    public static final RegistryObject<BasicParticleType> SOUL_FLAME_NORMAL = PARTICLES.register("soul_flame_normal", () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> SOUL_FLAME_FIRE = PARTICLES.register("soul_flame_fire", () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> SOUL_FLAME_WATER = PARTICLES.register("soul_flame_water", () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> SOUL_FLAME_AIR = PARTICLES.register("soul_flame_air", () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> SOUL_FLAME_EXPLOSION = PARTICLES.register("soul_flame_explosion", () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> SOUL_FLAME_MAGIC = PARTICLES.register("soul_flame_magic", () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> SOUL_FLAME_MOB = PARTICLES.register("soul_flame_mob", () -> new BasicParticleType(true));

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticles(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particles.registerFactory(SOUL_FLAME_NORMAL.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(SOUL_FLAME_FIRE.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(SOUL_FLAME_WATER.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(SOUL_FLAME_AIR.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(SOUL_FLAME_EXPLOSION.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(SOUL_FLAME_MAGIC.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(SOUL_FLAME_MOB.get(), FlameParticle.Factory::new);
    }
}
