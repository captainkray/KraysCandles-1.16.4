package com.captainkray.krayscandles.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class ItemHelper {

    public static CompoundNBT getNBT (ItemStack is) {

        if (is.getTag() == null) {
            is.setTag(new CompoundNBT());
        }

        return is.getTag();
    }

    public static void spawnStackAtLocation(World world, Location location, ItemStack stack) {
        spawnStack(world, (float) location.x + 0.5F, (float) location.y + 0.5F, (float) location.z + 0.5F, stack);
    }

    public static void spawnStackAtEntity(World world, Entity entity, ItemStack stack) {
        spawnStack(world, (float) entity.getPosition().getX() + 0.5F, (float) entity.getPosition().getY() + 0.5F, (float) entity.getPosition().getZ() + 0.5F, stack);
    }

    public static ItemEntity spawnStack(World world, float x, float y, float z, ItemStack stack) {
        ItemEntity item = new ItemEntity(world, x, y, z, stack);
        item.setNoPickupDelay();
        item.setMotion(0, 0, 0);
        world.addEntity(item);
        return item;
    }
}
