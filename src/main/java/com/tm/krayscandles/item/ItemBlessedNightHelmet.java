package com.tm.krayscandles.item;

import com.tm.krayscandles.item.base.ItemArmorBase;
import com.tm.krayscandles.item.tier.KCArmorTiers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class ItemBlessedNightHelmet extends ItemArmorBase {
    public ItemBlessedNightHelmet() {
        super(KCArmorTiers.BLESSED_NIGHT, EquipmentSlotType.HEAD);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 600, 0, true, false));
        player.addPotionEffect(new EffectInstance(Effects.GLOWING, 20, 0, true, false ));
    }
}
