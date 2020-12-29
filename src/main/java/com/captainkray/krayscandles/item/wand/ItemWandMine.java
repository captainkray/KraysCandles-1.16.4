package com.captainkray.krayscandles.item.wand;

import com.captainkray.krayscandles.item.base.ItemWandBase;
import com.captainkray.krayscandles.util.EffectHelper;
import com.captainkray.krayscandles.util.Location;
import com.captainkray.krayscandles.util.RayTraceHelper;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
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

        RayTraceHelper.BlockTrace rayTrace = RayTraceHelper.RayTraceBlock(world, player, MAX_DISTANCE);

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
}



