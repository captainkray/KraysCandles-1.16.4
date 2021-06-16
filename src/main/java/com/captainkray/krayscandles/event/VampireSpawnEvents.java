package com.captainkray.krayscandles.event;

import com.captainkray.krayscandles.entity.EntityVampire;
import com.captainkray.krayscandles.util.ChatHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.util.Random;

public class VampireSpawnEvents {

    private static final int SPAWN_RANGE = 500;

    private boolean canSpawn = true;

    @SubscribeEvent
    public void onTick(TickEvent.WorldTickEvent event) {

        World world = event.world;
        if (world.getPlayers().size() > 0) {
            PlayerEntity player = world.getPlayers().get(0);
            if (world.getDayTime() == 18000) {
                if (!world.isRemote) {
                    if (canSpawn) {
                        Random random = new Random();
                        if (random.nextInt(5) == 0) {
                            canSpawn = false;
                            int randX = world.getWorldInfo().getSpawnX() + (SPAWN_RANGE - random.nextInt(SPAWN_RANGE * 2));
                            int randZ = world.getWorldInfo().getSpawnZ() + (SPAWN_RANGE - random.nextInt(SPAWN_RANGE * 2));
                            if(world.isNightTime()){
                                EntityVampire entity1 = new EntityVampire(world, (int) player.getPosX(), 251, (int) player.getPosZ());
                                world.addEntity(entity1);
                                entity1.setLocationAndAngles(randX, 251, randZ, 0, 0);
                                ChatHelper.broadcastMessage(world, TextFormatting.DARK_RED + "" + TextFormatting.ITALIC + "A Vampire is hunting at [" + randX + ", " + randZ + "]!");
                                world.playBroadcastSound(1023, entity1.getPosition(), 0);
                              }
                            }
                        }
                    }
                }
            }
            else canSpawn = true;
        }
    }

