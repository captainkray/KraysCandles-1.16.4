package com.tm.krayscandles.event;

import com.tm.krayscandles.init.InitEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EffectEvents {

    @SubscribeEvent
    public void onEffectAdded(PotionEvent.PotionAddedEvent event) {

        if (event.getPotionEffect().getPotion() == InitEffects.FLIGHT.get()) {

            event.getEntityLiving().removePotionEffect(Effects.SLOW_FALLING);

            if (event.getEntityLiving() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) event.getEntityLiving();
                player.abilities.allowFlying = true;
                player.sendPlayerAbilities();
            }
        }
    }

    @SubscribeEvent
    public void onEffectDropped(PotionEvent.PotionExpiryEvent event) {
        if (event.getPotionEffect() != null && event.getPotionEffect().getPotion() == InitEffects.FLIGHT.get()) removeFlight(event.getPotionEffect().getPotion(), event.getEntityLiving());
    }

    @SubscribeEvent
    public void onEffectDropped(PotionEvent.PotionRemoveEvent event) {
        if (event.getPotionEffect() != null && event.getPotionEffect().getPotion() == InitEffects.FLIGHT.get()) removeFlight(event.getPotionEffect().getPotion(), event.getEntityLiving());
    }



    private void removeFlight(Effect effect, LivingEntity entity) {

        if (entity instanceof PlayerEntity) {

            PlayerEntity player = (PlayerEntity) entity;

            player.playSound(SoundEvents.BLOCK_BEACON_DEACTIVATE, 1, 10);

            player.abilities.allowFlying = false;
            player.abilities.isFlying = false;
            player.sendPlayerAbilities();
        }
    }
}
