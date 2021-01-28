package com.captainkray.krayscandles.ritual;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RitualStructure {

    private final String name;

    private final List<RitualBlock> ritualBlocks = new ArrayList<>();

    public RitualStructure(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<RitualBlock> getRitualBlocks() {
        return ritualBlocks;
    }

    public void addSymmetricalRitualBlock(RitualBlock block) {
        getRitualBlocks().add(block);
        getRitualBlocks().add(block.rotate(Rotation.CLOCKWISE_90));
        getRitualBlocks().add(block.rotate(Rotation.COUNTERCLOCKWISE_90));
        getRitualBlocks().add(block.rotate(Rotation.CLOCKWISE_180));
    }

    public void addRitualBlock(RitualBlock block) {
        getRitualBlocks().add(block);
    }

    public RitualStructure getRotatedRitual() {

        RitualStructure structure = new RitualStructure(name);

        for (RitualBlock ritualBlock : getRitualBlocks()) {
            structure.addRitualBlock(ritualBlock.rotate(Rotation.CLOCKWISE_90));
        }

        return structure;
    }

    public boolean isStructureComplete(World world, BlockPos pos, RitualStructure structure, boolean checkSymmetry) {

        for (RitualBlock ritualBlock : structure.getRitualBlocks()) {

            if (!ritualBlock.isValid(world, pos)) {

                if (checkSymmetry) {
                    return structure.isStructureComplete(world, pos, getRotatedRitual(), false);
                }

                return false;
            }
        }

        return true;
    }

    public boolean isStructureComplete(World world, BlockPos pos) {
        return isStructureComplete(world, pos, this, true);
    }
}
