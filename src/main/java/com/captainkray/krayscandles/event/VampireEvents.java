package com.captainkray.krayscandles.event;

import com.captainkray.krayscandles.entity.EntityVampire;
import com.captainkray.krayscandles.init.InitItems;
import com.captainkray.krayscandles.init.InitSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class VampireEvents {

    @SubscribeEvent
    public void onVampireGarlicHit(LivingDamageEvent event) {

        if (event.getSource().getImmediateSource() instanceof PlayerEntity) {

            PlayerEntity player = (PlayerEntity) event.getSource().getImmediateSource();

            ItemStack offItem = player.getHeldItemOffhand();

            if (event.getEntity() instanceof EntityVampire) {

                EntityVampire VampEntity = (EntityVampire) event.getEntity();

                if (offItem.getItem() == InitItems.GARLIC.get()) {
                    VampEntity.playSound(InitSounds.VAMPIRE_WEAKENED.get(), 2, 1);
                    VampEntity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100));
                    VampEntity.setHealth(VampEntity.getHealth() - 10);
                    VampEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 300));
                }
            }
        }
    }
}
