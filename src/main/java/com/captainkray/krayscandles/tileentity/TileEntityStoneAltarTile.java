package com.captainkray.krayscandles.tileentity;

import com.captainkray.krayscandles.init.InitTileEntityTypes;
import com.captainkray.krayscandles.tileentity.base.TileEntityBase;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class TileEntityStoneAltarTile extends TileEntityBase {

    private ItemStack ritualStack;

    public TileEntityStoneAltarTile() {
        super(InitTileEntityTypes.STONE_ALTAR_TILE.get());
        ritualStack = ItemStack.EMPTY;
    }

    public ItemStack getRitualStack() {
        return ritualStack;
    }

    public void placeRitualStack(ItemStack stack) {
        ritualStack = stack;
        if (!world.isRemote) markForUpdate();
    }

    public void takeRitualStack() {
        ritualStack = ItemStack.EMPTY;
        if (!world.isRemote) markForUpdate();
    }

    @Override
    public void read (BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);

        CompoundNBT stackTag = nbt.getCompound("Stack");
        ritualStack = ItemStack.read(stackTag);
    }

    @Override
    public CompoundNBT write (CompoundNBT nbt) {
        CompoundNBT stackTag = new CompoundNBT();
        ritualStack.write(stackTag);
        nbt.put("Stack", stackTag);
        return super.write(nbt);
    }
}
