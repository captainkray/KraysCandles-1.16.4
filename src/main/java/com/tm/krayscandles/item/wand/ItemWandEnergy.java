package com.tm.krayscandles.item.wand;

import com.tm.api.calemicore.util.Location;
import com.tm.api.calemicore.util.helper.EffectHelper;
import com.tm.api.calemicore.util.helper.RayTraceHelper;
import com.tm.api.calemicore.util.helper.WorldEffectHelper;
import com.tm.krayscandles.item.base.ItemWandBase;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.ritual.RitualRecipes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ItemWandEnergy extends ItemWandBase {

    private static final int MAX_DISTANCE = 40;

    @Override
    public int getCooldown() {
        return 4;
    }

    @Override
    public boolean castWand(World world, PlayerEntity player) {

        RayTraceHelper.BlockTrace rayTrace = RayTraceHelper.rayTraceBlock(world, player, Hand.MAIN_HAND, MAX_DISTANCE);

        if (rayTrace != null) {

            Location location = rayTrace.getHit();

            WorldEffectHelper.spawnLightning(world, location.x + 0.5D, location.y + 0.5D, location.z + 0.5D, false);

            player.playSound(SoundEvents.BLOCK_CONDUIT_AMBIENT, 75, 10);
            EffectHelper.addPotionEffect(Effects.JUMP_BOOST, 80, 3, player);
            EffectHelper.addPotionEffect(Effects.SLOW_FALLING, 80, 1, player);

            return true;
        }

        return false;
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return RitualRecipes.WAND_ENERGY;
    }
}





