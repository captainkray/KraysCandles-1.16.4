package com.captainkray.krayscandles.item.wand;

import com.captainkray.krayscandles.item.base.ItemWandBase;
import com.captainkray.krayscandles.util.EffectHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
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

            if (player.abilities.allowFlying) {
                player.abilities.allowFlying = false;
                player.abilities.isFlying = false;
                player.sendPlayerAbilities();
                player.playSound(SoundEvents.BLOCK_BEACON_DEACTIVATE, 1, 10);
                EffectHelper.addPotionEffect(Effects.SLOW_FALLING, 20*5, 1, player);
            }

            else {
                player.abilities.allowFlying = true;
                player.sendPlayerAbilities();
                player.playSound(SoundEvents.BLOCK_CONDUIT_AMBIENT, 1, 10);
                EffectHelper.addPotionEffect(Effects.SLOW_FALLING, 20*600, 1, player);
            }

            player.playSound(SoundEvents.AMBIENT_UNDERWATER_ENTER, 1,10);
            player.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1,-5);

            return true;
        }

        return false;
    }
}



