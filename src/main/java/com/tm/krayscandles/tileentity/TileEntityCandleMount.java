package com.tm.krayscandles.tileentity;

import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.init.InitTileEntityTypes;
import com.tm.krayscandles.tileentity.base.TileEntityCandleBase;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;

public class TileEntityCandleMount extends TileEntityCandleBase {

    private ItemStack candleStack;

    public TileEntityCandleMount() {
        super(InitTileEntityTypes.CANDLE_SOY_MOUNT.get());
        candleStack = ItemStack.EMPTY;
    }

    @Override
    public EffectInstance[] getCandleEffects() {
        return new EffectInstance[] {};
    }

    public ItemStack getCandleStack() {
        return candleStack;
    }

    public void placeCandle(int colorID) {

        ItemStack newStack = new ItemStack(InitItems.CANDLE_SOY_COLORED_ITEM.get());
        newStack.setDamage(colorID);

        candleStack = newStack;

        if (!world.isRemote) markForUpdate();
    }

    public void takeCandle() {
        candleStack = ItemStack.EMPTY;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void read (BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);

        CompoundNBT stackTag = nbt.getCompound("Stack");
        candleStack = ItemStack.read(stackTag);

        if (!(candleStack.getItem() == InitItems.CANDLE_SOY_COLORED_ITEM.get())) {
            candleStack = ItemStack.EMPTY;
        }
    }

    @Override
    public CompoundNBT write (CompoundNBT nbt) {
        CompoundNBT stackTag = new CompoundNBT();
        candleStack.write(stackTag);
        nbt.put("Stack", stackTag);
        return super.write(nbt);
    }
}
