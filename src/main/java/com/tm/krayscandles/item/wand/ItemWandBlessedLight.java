package com.tm.krayscandles.item.wand;

import com.tm.api.calemicore.util.helper.EffectHelper;
import com.tm.krayscandles.init.InitEffects;
import com.tm.krayscandles.item.base.ItemWandBase;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.ritual.RitualRecipes;
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

            if (player.getActivePotionEffect(InitEffects.FLIGHT.get()) == null) {
                player.playSound(SoundEvents.BLOCK_CONDUIT_AMBIENT, 1, 10);
                EffectHelper.addPotionEffect(InitEffects.FLIGHT.get(), 20*60*8, 0, player);
                player.removePotionEffect(Effects.SLOW_FALLING);
            }

            else {
                player.removePotionEffect(InitEffects.FLIGHT.get());
                EffectHelper.addPotionEffect(Effects.SLOW_FALLING, 20*30, 0, player);
            }

            player.playSound(SoundEvents.AMBIENT_UNDERWATER_ENTER, 1,10);
            player.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1,-5);

            return true;
        }

        return false;
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return RitualRecipes.WAND_BLESSED;
    }
}



