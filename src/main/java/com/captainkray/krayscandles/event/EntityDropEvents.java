package com.captainkray.krayscandles.event;

import com.captainkray.krayscandles.init.InitItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

public class EntityDropEvents {

    @SubscribeEvent
    public void onColorBreakEvent(LivingDropsEvent event) {

        LivingEntity entity = event.getEntityLiving();

        if (entity instanceof WitchEntity) {

            Random random = new Random();
            World world = entity.getEntityWorld();

            if (random.nextInt(2) == 0) {
                event.getDrops().add(new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), new ItemStack(InitItems.SOYBEAN_CROP.get().asItem())));
            }
        }
    }
}