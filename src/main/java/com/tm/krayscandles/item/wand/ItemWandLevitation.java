package com.tm.krayscandles.item.wand;

import com.tm.api.calemicore.util.helper.RayTraceHelper;
import com.tm.krayscandles.item.base.ItemWandBase;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.ritual.RitualRecipes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
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

            return true;
        }

        return false;
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return RitualRecipes.WAND_LEVITATE;
    }
}



