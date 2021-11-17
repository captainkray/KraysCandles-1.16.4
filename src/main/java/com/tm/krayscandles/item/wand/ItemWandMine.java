package com.tm.krayscandles.item.wand;

import com.tm.api.calemicore.util.Location;
import com.tm.api.calemicore.util.helper.EffectHelper;
import com.tm.api.calemicore.util.helper.RayTraceHelper;
import com.tm.krayscandles.item.base.ItemWandBase;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.ritual.RitualRecipes;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ItemWandMine extends ItemWandBase {

    private static final int MAX_DISTANCE = 30;

    @Override
    public int getCooldown() {
        return 4;
    }

    @Override
    public boolean castWand(World world, PlayerEntity player) {

        RayTraceHelper.BlockTrace rayTrace = RayTraceHelper.rayTraceBlock(world, player, Hand.MAIN_HAND, MAX_DISTANCE);

        if (rayTrace != null) {

            Location location = rayTrace.getHit();

            for (int i = 0; i < 3; i++) {
                world.addEntity(new TNTEntity(world, location.x + 0.5D, location.y + 0.5D, location.z + 0.5D, player));
            }

            player.playSound(SoundEvents.BLOCK_CONDUIT_AMBIENT, 1, 10);

            EffectHelper.addPotionEffect(Effects.MINING_FATIGUE, 20*4, 1, player);
            EffectHelper.addPotionEffect(Effects.SLOWNESS, 20*5, 1, player);

            return true;
        }

        return false;
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return RitualRecipes.WAND_MINING;
    }
}



