package com.captainkray.krayscandles.event;

import com.captainkray.krayscandles.init.InitItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WeaponKillEvents {

    @SubscribeEvent
    public void onScalpelKill(AttackEntityEvent event) {

        ItemStack heldItem = event.getEntityLiving().getHeldItemMainhand();
        Entity entity = event.getTarget();

        if (entity instanceof ZombieEntity) {

            ZombieEntity target = (ZombieEntity) event.getTarget();

            if (heldItem.getItem() == InitItems.SCALPEL.get()) {

                if (!target.isSilent()) {

                    target.entityDropItem(InitItems.ZOMBIE_EAR.get());
                    target.entityDropItem(InitItems.ZOMBIE_EAR.get());
                    target.setSilent(true);
                }
            }
        }

        else if (entity instanceof BatEntity) {

            BatEntity target = (BatEntity) event.getTarget();

            if (heldItem.getItem() == InitItems.SCALPEL.get()) {

                if (!target.isSilent()) {

                    target.entityDropItem(InitItems.FLYING_BAT_EYEBALL.get());
                    target.setSilent(true);
                }
            }
        }
    }

    @SubscribeEvent
    public void onBladeOfCursedNightKill(LivingDeathEvent event) {

        if (event.getSource().getImmediateSource() instanceof LivingEntity) {

            LivingEntity sourceEntity = (LivingEntity) event.getSource().getImmediateSource();
            ItemStack heldItem = sourceEntity.getHeldItemMainhand();

            if (event.getEntity() instanceof LivingEntity) {

                LivingEntity killedEntity = (LivingEntity) event.getEntity();

                if (heldItem.getItem() == InitItems.BLADE_NIGHT.get()) {

                    killedEntity.entityDropItem(InitItems.SOUL_ESSENCE_LESSER.get());
                    killedEntity.playSound(SoundEvents.AMBIENT_CAVE, 1, 1);
                    killedEntity.playSound(SoundEvents.ENTITY_GHAST_DEATH, 1, -7);
                    event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.BLINDNESS, 300));
                }
            }
        }
    }
}
