package com.tm.krayscandles.init;

import com.tm.krayscandles.effect.FlightEffect;
import com.tm.krayscandles.main.KCReference;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitEffects {

    public static final DeferredRegister<Effect> POTION_TYPES = DeferredRegister.create(ForgeRegistries.POTIONS, KCReference.MOD_ID);

    public static final RegistryObject<Effect> FLIGHT = POTION_TYPES.register("flight", FlightEffect::new);
}
