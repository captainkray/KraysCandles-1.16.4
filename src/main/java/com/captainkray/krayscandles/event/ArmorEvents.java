package com.captainkray.krayscandles.event;

import com.captainkray.krayscandles.init.InitItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ArmorEvents {

    @SubscribeEvent
    public void onWearBlessedNightHelmet(LivingEquipmentChangeEvent event) {

        LivingEntity entity = event.getEntityLiving();

        ItemStack helmetStack = entity.getItemStackFromSlot(EquipmentSlotType.HEAD);

        if (helmetStack.getItem() == InitItems.HELMET_BLESSED_NIGHT.get()) {
            entity.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 2000000));
            entity.setGlowing(true);
        }

        else {
            entity.setGlowing(false);
            entity.removePotionEffect(Effects.NIGHT_VISION);
        }
    }
}
