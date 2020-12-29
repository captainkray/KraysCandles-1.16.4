package com.captainkray.krayscandles.item.wand;

import com.captainkray.krayscandles.item.base.ItemWandBase;
import com.captainkray.krayscandles.util.EffectHelper;
import com.captainkray.krayscandles.util.RayTraceHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ItemWandLevitation extends ItemWandBase {

    private static final int MAX_DISTANCE = 30;

    @Override
    public int getCooldown() {
        return 1;
    }

    @Override
    public boolean castWand(World world, PlayerEntity player) {

        Entity entityHit = RayTraceHelper.rayTraceEntity(world, player, MAX_DISTANCE);

        if (entityHit != null) {

            ShulkerBulletEntity bill = new ShulkerBulletEntity(world, player, entityHit, Direction.Axis.getRandomAxis(random));
            world.addEntity(bill);

            player.playSound(SoundEvents.BLOCK_CONDUIT_AMBIENT, 1, 10);

            EffectHelper.addPotionEffect(Effects.LEVITATION, 120 * 20, 1, player);
            EffectHelper.addPotionEffect(Effects.SLOW_FALLING, 140 * 20, 1, player);

            return true;
        }

        return false;
    }
}



