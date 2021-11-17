package com.tm.krayscandles.event;

import com.tm.api.calemicore.util.helper.MathHelper;
import com.tm.krayscandles.entity.EntityWraith;
import com.tm.krayscandles.entity.EntityWraithDamned;
import com.tm.krayscandles.init.InitItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntityDropEvents {

    @SubscribeEvent
    public void onEntityDrops(LivingDropsEvent event) {

        World world = event.getEntity().getEntityWorld();
        LivingEntity entity = event.getEntityLiving();

        double lootingMultiplier = event.getLootingLevel() * 10;

        if (entity instanceof WitchEntity) {

            if (MathHelper.roll(50 + lootingMultiplier)) {
                event.getDrops().add(new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), new ItemStack(InitItems.SOYBEAN_CROP.get().asItem())));
            }

            if (MathHelper.roll(20 + lootingMultiplier)) {
                event.getDrops().add(new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), new ItemStack(InitItems.FLYING_BAT_EYEBALL.get().asItem())));
            }

            if (MathHelper.roll(10 + lootingMultiplier)) {
                event.getDrops().add(new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), new ItemStack(InitItems.ZOMBIE_EAR.get().asItem())));
            }

            if (MathHelper.roll(10 + lootingMultiplier)) {
                event.getDrops().add(new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), new ItemStack(InitItems.GARLIC.get().asItem())));
            }

        }

        if (entity instanceof EntityWraith) {


            if (MathHelper.roll(20 + lootingMultiplier)) {
                event.getDrops().add(new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), new ItemStack(InitItems.CLOTH_CURSED.get().asItem())));
            }
        }

        if (entity instanceof EntityWraithDamned) {
            event.getDrops().add(new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), new ItemStack(InitItems.WINGS_WRAITH.get().asItem())));
        }
    }
}
