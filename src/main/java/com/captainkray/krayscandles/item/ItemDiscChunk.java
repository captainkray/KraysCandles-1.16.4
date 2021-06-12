package com.captainkray.krayscandles.item;

import com.captainkray.krayscandles.main.KraysCandles;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class ItemDiscChunk extends MusicDiscItem {

    public ItemDiscChunk(Supplier<SoundEvent> soundSupplier) {
        super(15, soundSupplier, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1).rarity(Rarity.RARE));
    }

    public ItemDiscChunk(Supplier<SoundEvent> soundSupplier, ItemGroup tab) {
        super(15, soundSupplier, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1).rarity(Rarity.RARE));
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(TextFormatting.GRAY + "" +  "Kray - chunk"));
    }
}