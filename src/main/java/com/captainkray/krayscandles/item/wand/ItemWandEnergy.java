package com.captainkray.krayscandles.item.wand;

import com.captainkray.krayscandles.item.base.ItemWandBase;
import com.captainkray.krayscandles.util.EffectHelper;
import com.captainkray.krayscandles.util.Location;
import com.captainkray.krayscandles.util.RayTraceHelper;
import com.captainkray.krayscandles.util.WorldEffectHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
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

        RayTraceHelper.BlockTrace rayTrace = RayTraceHelper.RayTraceBlock(world, player, MAX_DISTANCE);

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
}





