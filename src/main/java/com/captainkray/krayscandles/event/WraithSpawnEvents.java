package com.captainkray.krayscandles.event;

import com.captainkray.krayscandles.entity.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WraithSpawnEvents {

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {

        LivingEntity entity = event.getEntityLiving();

        if (entity instanceof PlayerEntity || entity instanceof VillagerEntity) {

            World world = entity.getEntityWorld();
            DamageSource source = event.getSource();

            String entityName = entity.getDisplayName().getString();
            double x = entity.getPositionVec().x;
            double y = entity.getPositionVec().y;
            double z = entity.getPositionVec().z;

            EntityWraith.WraithType type = null;

            if (source.isFireDamage()) {
                type = EntityWraith.WraithType.FIRE;
                world.addEntity(new EntityWraithFire(world, entityName, x, y, z));
            }

            else if (source == DamageSource.DROWN) {
                type = EntityWraith.WraithType.WATER;
                world.addEntity(new EntityWraithWater(world, entityName, x, y, z));
            }

            else if (source == DamageSource.FALL || source == DamageSource.FALLING_BLOCK || source == DamageSource.ANVIL || source == DamageSource.FLY_INTO_WALL) {
                type = EntityWraith.WraithType.AIR;
                world.addEntity(new EntityWraithAir(world, entityName, x, y, z));
            }

            else if (source.isExplosion()) {
                type = EntityWraith.WraithType.EXPLOSION;
                world.addEntity(new EntityWraithExplosion(world, entityName, x, y, z));
            }

            else if (source.isMagicDamage()) {
                type = EntityWraith.WraithType.MAGIC;
                world.addEntity(new EntityWraithMagic(world, entityName, x, y, z));
            }

            else if (source == DamageSource.DRAGON_BREATH || source == DamageSource.WITHER) {
                type = EntityWraith.WraithType.MOB;
                world.addEntity(new EntityWraithMob(world, entityName, x, y, z));
            }

            else if (event.getSource().getImmediateSource() instanceof MonsterEntity && !(event.getSource().getImmediateSource() instanceof EntityWraith)) {
                type = EntityWraith.WraithType.MOB;
                world.addEntity(new EntityWraithMob(world, entityName, x, y, z));
            }
        }
    }
}
