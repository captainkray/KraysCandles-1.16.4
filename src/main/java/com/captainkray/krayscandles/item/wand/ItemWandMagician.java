package com.captainkray.krayscandles.item.wand;

import com.captainkray.krayscandles.item.base.ItemWandBase;
import com.captainkray.krayscandles.ritual.RitualRecipe;
import com.captainkray.krayscandles.ritual.RitualRecipes;
import com.captainkray.krayscandles.util.EffectHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ItemWandMagician extends ItemWandBase {

    @Override
    public int getCooldown() {
        return 60;
    }

    @Override
    public boolean castWand(World world, PlayerEntity player) {

        RabbitEntity bugs = new RabbitEntity(EntityType.RABBIT, world);
        bugs.setPosition(player.getPosX(), player.getPosY(), player.getPosZ());
        bugs.setRabbitType(0);
        world.addEntity(bugs);
        player.playSound(SoundEvents.BLOCK_CONDUIT_AMBIENT, 1, 5);
        player.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1, 12);
        EffectHelper.addPotionEffect(Effects.INVISIBILITY, 120*20, 1, player);
        EffectHelper.addPotionEffect(Effects.LUCK, 120*20, 1, player);
        return true;
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return RitualRecipes.WAND_INVIS;
    }
}


