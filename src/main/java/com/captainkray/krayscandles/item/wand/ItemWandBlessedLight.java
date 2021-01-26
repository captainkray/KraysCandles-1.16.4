package com.captainkray.krayscandles.item.wand;

import com.captainkray.krayscandles.init.InitEffects;
import com.captainkray.krayscandles.item.base.ItemWandBase;
import com.captainkray.krayscandles.util.EffectHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ItemWandBlessedLight extends ItemWandBase {

    @Override
    public int getCooldown() {
        return 1;
    }

    @Override
    public boolean castWand(World world, PlayerEntity player) {

        if (!player.isCreative()) {

            if (player.getActivePotionEffect(InitEffects.FLIGHT.get()) == null) {
                player.playSound(SoundEvents.BLOCK_CONDUIT_AMBIENT, 1, 10);
                EffectHelper.addPotionEffect(InitEffects.FLIGHT.get(), 20*60*8, 0, player);
            }

            else {
                player.playSound(SoundEvents.BLOCK_BEACON_DEACTIVATE, 1, 10);
                player.removePotionEffect(InitEffects.FLIGHT.get());
            }

            player.playSound(SoundEvents.AMBIENT_UNDERWATER_ENTER, 1,10);
            player.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1,-5);

            return true;
        }

        return false;
    }
}



