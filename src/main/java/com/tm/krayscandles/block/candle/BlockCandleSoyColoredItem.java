package com.tm.krayscandles.block.candle;

import com.tm.api.calemicore.util.helper.ItemHelper;
import com.tm.krayscandles.main.KraysCandles;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class BlockCandleSoyColoredItem extends BlockItem {

    public BlockCandleSoyColoredItem(Block block) {
        super(block, new Item.Properties());
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        CompoundNBT nbt = ItemHelper.getNBT(stack);
        return new TranslationTextComponent(this.getTranslationKey(stack) + "_" + DyeColor.byId(stack.getDamage()).getTranslationKey());
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {

        if (group == KraysCandles.TAB_CANDLE) {

            for (byte i = 0; i < DyeColor.values().length; i++) {
                ItemStack stack = new ItemStack(this);
                stack.setDamage(i);
                items.add(stack);
            }
        }
    }
}
