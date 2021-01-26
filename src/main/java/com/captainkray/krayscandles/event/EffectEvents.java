package com.captainkray.krayscandles.event;

import com.captainkray.krayscandles.init.InitEffects;
import com.captainkray.krayscandles.util.EffectHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EffectEvents {

    @SubscribeEvent
    public void onEffectAdded(PotionEvent.PotionAddedEvent event) {

        if (event.getPotionEffect().getPotion() == InitEffects.FLIGHT.get()) {

            if (event.getEntityLiving() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) event.getEntityLiving();
                player.abilities.allowFlying = true;
                player.sendPlayerAbilities();
            }
        }
    }

    @SubscribeEvent
    public void onEffectDropped(PotionEvent.PotionExpiryEvent event) {
        if (event.getPotionEffect() != null) removeFlight(event.getPotionEffect().getPotion(), event.getEntityLiving());
    }

    @SubscribeEvent
    public void onEffectDropped(PotionEvent.PotionRemoveEvent event) {
        if (event.getPotionEffect() != null) removeFlight(event.getPotionEffect().getPotion(), event.getEntityLiving());
    }

    private void removeFlight(Effect effect, LivingEntity entity) {

        if (effect == InitEffects.FLIGHT.get()) {

            if (entity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) entity;

                player.abilities.allowFlying = false;
                player.abilities.isFlying = false;
                player.sendPlayerAbilities();

                EffectHelper.addPotionEffect(Effects.SLOW_FALLING, 20*30, 0, player);
            }
        }
    }
}
