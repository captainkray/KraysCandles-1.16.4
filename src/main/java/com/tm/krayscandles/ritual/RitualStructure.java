package com.tm.krayscandles.ritual;

import net.minecraft.util.Rotation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class RitualStructure {

    private final String name;
    private SoundEvent sound;
    private Supplier<SoundEvent> soundSupplier;

    private final List<RitualBlock> ritualBlocks = new ArrayList<>();

    public RitualStructure(String name, SoundEvent sound) {
        this.name = name;
        this.sound = sound;
    }

    public RitualStructure(String name, Supplier<SoundEvent> soundSupplier) {
        this.name = name;
        this.soundSupplier = soundSupplier;
    }

    public String getName() {
        return name;
    }

    public SoundEvent getSound() {

        if (soundSupplier != null) {
            return soundSupplier.get();
        }

        return sound;
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

        RitualStructure structure = new RitualStructure(name, sound);

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
