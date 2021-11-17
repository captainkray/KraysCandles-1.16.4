package com.tm.api.calemicore.util.helper;

import com.tm.api.calemicore.util.Location;
import com.tm.api.calemicore.util.UnitChatMessage;
import com.tm.api.calemicore.util.VeinScan;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;

public class WorldEditHelper {

    public static void generateCommand (ArrayList<Location> list, BlockState blockToPlace, BlockState mask, PlayerEntity player, UnitChatMessage message) {

        if (VeinScan.MAX_SCAN_SIZE == 0) {
            message.printMessage(TextFormatting.RED, "The Brush is disabled by config!");
            return;
        }

        if (list.size() > VeinScan.MAX_SCAN_SIZE) {
            message.printMessage(TextFormatting.RED, "Too many blocks to fill!");
            message.printMessage(TextFormatting.RED, "You are " + StringHelper.printCommas(list.size() - VeinScan.MAX_SCAN_SIZE) + " blocks over the max amount!");
            return;
        }

        int count = 0;

        for (Location nextLocation : list) {

            if (mask == Blocks.AIR.getDefaultState() && nextLocation.isBlockValidForPlacing()) {

                count++;
                nextLocation.setBlock(blockToPlace);
            }

            else if (nextLocation.getForgeBlockState() == mask) {

                count++;
                nextLocation.setBlock(blockToPlace);
            }
        }

        message.printMessage(TextFormatting.GREEN, "Placed " + ItemHelper.countByStacks(count));

        SoundHelper.playSimpleSound(player, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP);
    }

    public static ArrayList<Location> selectFlatCubeFromFace (Location location, Direction face, int radius) {

        ArrayList<Location> list = new ArrayList<>();

        int xRad = radius;
        int yRad = radius;
        int zRad = radius;

        if (face == Direction.UP || face == Direction.DOWN) {
            yRad = 0;
        }

        else if (face == Direction.NORTH || face == Direction.SOUTH) {
            zRad = 0;
        }

        else if (face == Direction.EAST || face == Direction.WEST) {
            xRad = 0;
        }

        return selectCubeFromRadius(location, xRad, yRad, zRad);
    }

    public static ArrayList<Location> selectCubeFromRadius (Location location, int xRad, int yRad, int zRad) {
        return selectCubeFromRadius(location, xRad, -yRad, yRad, zRad);
    }

    private static ArrayList<Location> selectCubeFromRadius (Location location, int xRad, int yRadMin, int yRadMax, int zRad) {

        ArrayList<Location> list = new ArrayList<>();

        for (int x = -xRad; x <= xRad; x++) {

            for (int y = yRadMin; y <= yRadMax; y++) {

                for (int z = -zRad; z <= zRad; z++) {
                    Location nextLocation = new Location(location.world, location.x + x, location.y + y, location.z + z);
                    list.add(nextLocation);
                }
            }
        }

        return list;
    }

    public static ArrayList<Location> selectCubeFromTwoPoints (Location loc1, Location loc2) {

        ArrayList<Location> list = new ArrayList<>();

        for (int x = Math.min(loc1.x, loc2.x); x <= Math.max(loc1.x, loc2.x); x++) {

            for (int y = Math.min(loc1.y, loc2.y); y <= Math.max(loc1.y, loc2.y); y++) {

                for (int z = Math.min(loc1.z, loc2.z); z <= Math.max(loc1.z, loc2.z); z++) {
                    list.add(new Location(loc1.world, x, y, z));
                }
            }
        }

        return list;
    }

    public static ArrayList<Location> selectHollowCubeFromTwoPoints (Location loc1, Location loc2) {

        ArrayList<Location> list = new ArrayList<>();

        //X Walls
        for (int x = Math.min(loc1.x, loc2.x); x <= Math.max(loc1.x, loc2.x); x++) {

            for (int y = Math.min(loc1.y, loc2.y); y <= Math.max(loc1.y, loc2.y); y++) {
                list.add(new Location(loc1.world, x, y, Math.min(loc1.z, loc2.z)));
                list.add(new Location(loc1.world, x, y, Math.max(loc1.z, loc2.z)));
            }
        }

        //Z Walls
        for (int z = Math.min(loc1.z, loc2.z) + 1; z <= Math.max(loc1.z, loc2.z) - 1; z++) {

            for (int y = Math.min(loc1.y, loc2.y); y <= Math.max(loc1.y, loc2.y); y++) {
                list.add(new Location(loc1.world, Math.min(loc1.x, loc2.x), y, z));
                list.add(new Location(loc1.world, Math.max(loc1.x, loc2.x), y, z));
            }
        }

        //Top and Bottom Walls
        for (int x = Math.min(loc1.x, loc2.x) + 1; x <= Math.max(loc1.x, loc2.x) - 1; x++) {

            for (int z = Math.min(loc1.z, loc2.z) + 1; z <= Math.max(loc1.z, loc2.z) - 1; z++) {
                list.add(new Location(loc1.world, x, Math.min(loc1.y, loc2.y), z));
                list.add(new Location(loc1.world, x, Math.max(loc1.y, loc2.y), z));
            }
        }

        return list;
    }

    public static ArrayList<Location> selectCylinderFromTwoPoints (Location loc1, Location loc2, boolean hollow, int thickness) {

        ArrayList<Location> list = new ArrayList<>();

        int minY = Math.min(loc1.y, loc2.y);
        int maxY = Math.max(loc1.y, loc2.y);

        for (int y = minY; y <= maxY; y++) {
            list.addAll(selectCircleFromTwoPoints(new Location(loc1.world, loc1.x, y, loc1.z), new Location(loc2.world, loc2.x, y, loc2.z), hollow, thickness));
        }

        return list;
    }

    public static ArrayList<Location> selectCircleFromTwoPoints (Location loc1, Location loc2, boolean hollow, int thickness) {

        ArrayList<Location> list = new ArrayList<>();

        int radius = (int) loc1.getDistance(loc2);

        for (int x = -radius; x <= radius; x++) {

            for (int z = -radius; z <= radius; z++) {

                int rx = loc1.x + x;
                int rz = loc1.z + z;

                Location nextLoc = new Location(loc1.world, rx, loc1.y, rz);

                if (loc1.getDistance(nextLoc) - 0.2F <= radius) {

                    if (!hollow || loc1.getDistance(nextLoc) - 0.2F >= radius - thickness) {
                        list.add(nextLoc);
                    }
                }
            }
        }

        return list;
    }

    public static ArrayList<Location> selectSphereFromTwoPoints (Location loc1, Location loc2, boolean hollow, int thickness) {

        ArrayList<Location> list = new ArrayList<>();

        int radius = (int) loc1.getDistance(loc2);

        for (int x = -radius; x <= radius; x++) {

            for (int y = -radius; y <= radius; y++) {

                for (int z = -radius; z <= radius; z++) {

                    int rx = loc1.x + x;
                    int ry = loc1.y + y;
                    int rz = loc1.z + z;

                    Location nextLoc = new Location(loc1.world, rx, ry, rz);

                    if (loc1.getDistance(nextLoc) - 0.2F <= radius) {

                        if (!hollow || loc1.getDistance(nextLoc) - 0.2F >= radius - thickness) {
                            list.add(nextLoc);
                        }
                    }
                }
            }
        }

        return list;
    }

    public static ArrayList<Location> selectPyramidFromRadius (Location loc1, int radius, boolean hollow) {

        ArrayList<Location> list = new ArrayList<>(selectCubeFromRadius(loc1, radius, 0, radius));

        for (int y = 1; y <= radius; y++) {

            if (hollow) list.addAll(selectWallsFromRadius(new Location(loc1.world, loc1.x, loc1.y, loc1.z), radius - y, loc1.y + y, loc1.y + y));
            else list.addAll(selectCubeFromRadius(new Location(loc1.world, loc1.x, loc1.y + y, loc1.z), radius - y, 0, radius - y));
        }

        return list;
    }

    public static ArrayList<Location> selectWallsFromRadius (Location location, int xzRad, int y1, int y2) {

        ArrayList<Location> list = new ArrayList<>();

        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);

        if (xzRad < 0) {
            return list;
        }

        else if (xzRad == 0) {

            for (int y = minY; y <= maxY; y++) {
                list.add(new Location(location.world, location.x, y, location.z));
            }
        }

        else {

            for (int i = -xzRad; i <= xzRad; i++) {

                for (int y = minY; y <= maxY; y++) {
                    list.add(new Location(location.world, location.x + xzRad, y, location.z + i));
                    list.add(new Location(location.world, location.x - xzRad, y, location.z + i));
                }
            }

            for (int i = -xzRad + 1; i <= xzRad - 1; i++) {

                for (int y = minY; y <= maxY; y++) {
                    list.add(new Location(location.world, location.x + i, y, location.z + xzRad));
                    list.add(new Location(location.world, location.x + i, y, location.z - xzRad));
                }
            }
        }

        return list;
    }
}
