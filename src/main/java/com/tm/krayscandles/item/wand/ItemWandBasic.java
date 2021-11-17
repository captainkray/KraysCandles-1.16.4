package com.tm.krayscandles.item.wand;

import com.tm.api.calemicore.util.helper.EffectHelper;
import com.tm.krayscandles.item.base.ItemWandBase;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.ritual.RitualRecipes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class ItemWandBasic extends ItemWandBase {

    @Override
    public int getCooldown() {
        return 1;
    }

    @Override
    public boolean castWand(World world, PlayerEntity player) {

        Vector3d aim = player.getLookVec();
        SmallFireballEntity fireball = new SmallFireballEntity(world, player, 0, 0, 0);
        fireball.setPosition(player.getPosX() + aim.x * 2D, player.getPosY() + aim.y * 2D + 1.5D, player.getPosZ() + aim.z * 2D);
        fireball.accelerationX = aim.x * .1;
        fireball.accelerationY = aim.y * .1;
        fireball.accelerationZ = aim.z * .1;
        world.addEntity(fireball);
        player.playSound(SoundEvents.BLOCK_CONDUIT_AMBIENT, 1, 10);
        EffectHelper.addPotionEffect(Effects.FIRE_RESISTANCE, 20, 1, player);

        return true;
    }

    @Override
    public RitualRecipe getRitualRecipe() {
        return RitualRecipes.WAND_FIRE;
    }
}


