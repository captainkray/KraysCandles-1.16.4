package com.captainkray.krayscandles.event;

import com.captainkray.krayscandles.init.InitItems;
import com.captainkray.krayscandles.ritual.Ritual;
import com.captainkray.krayscandles.ritual.RitualTypes;
import com.captainkray.krayscandles.util.ItemHelper;
import com.captainkray.krayscandles.util.WorldEffectHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RitualEvents {

    @SubscribeEvent
    public void onRitual(PlayerInteractEvent.RightClickBlock event) {

        if (event.getEntity() instanceof PlayerEntity) {

            //Handles Essence
            if (handleItemRitual(event, RitualTypes.RITUAL_ESSENCE, InitItems.SOUL_ESSENCE_GREATER_DEPLETED.get(), InitItems.SOUL_ESSENCE_GREATER.get()));
            else if (handleItemRitual(event, RitualTypes.RITUAL_ESSENCE, InitItems.SOUL_ESSENCE_BLESSED_DEPLETED.get(), InitItems.SOUL_ESSENCE_BLESSED.get()));

            //Handles Runes
            else if (handleItemRitual(event, RitualTypes.RITUAL_BLESSED, InitItems.RUNE_CATALYST.get(), InitItems.RUNE_BLESSED_FLIGHT.get()));
            else if (handleItemRitual(event, RitualTypes.RITUAL_CURSED, InitItems.RUNE_CATALYST.get(), InitItems.RUNE_CURSED_NIGHT.get()));
            else if (handleItemRitual(event, RitualTypes.RITUAL_PURGED, InitItems.RUNE_CATALYST.get(), InitItems.RUNE_PURGED_LIGHT.get()));
            else if (handleItemRitual(event, RitualTypes.RITUAL_FIRE, InitItems.RUNE_CATALYST.get(), InitItems.RUNE_GREAT_FIRE.get()));
            else if (handleItemRitual(event, RitualTypes.RITUAL_LEVITATE, InitItems.RUNE_CATALYST.get(), InitItems.RUNE_GREAT_LEVITATION.get()));
            else if (handleItemRitual(event, RitualTypes.RITUAL_ZEN, InitItems.RUNE_CATALYST.get(), InitItems.RUNE_ZEN_HEALING.get()));
            else if (handleItemRitual(event, RitualTypes.RITUAL_INVIS, InitItems.RUNE_CATALYST.get(), InitItems.RUNE_GREAT_MAGIC.get()));
            else if (handleItemRitual(event, RitualTypes.RITUAL_LUCK, InitItems.RUNE_CATALYST.get(), InitItems.RUNE_GREAT_LUCK.get()));
            else if (handleItemRitual(event, RitualTypes.RITUAL_ENERGY, InitItems.RUNE_CATALYST.get(), InitItems.RUNE_GREAT_ENERGY.get()));
            else if (handleItemRitual(event, RitualTypes.RITUAL_MINING, InitItems.RUNE_CATALYST.get(), InitItems.RUNE_GREAT_MINING.get()));

            //Handles Candle Rituals
            else if (handleCandleRitual(event, InitItems.RUNE_BLESSED_FLIGHT.get(), InitItems.CANDLE_BLESSED.get()));
            else if (handleCandleRitual(event, InitItems.RUNE_CURSED_NIGHT.get(), InitItems.CANDLE_CURSED.get()));
            else if (handleCandleRitual(event, InitItems.RUNE_PURGED_LIGHT.get(), InitItems.CANDLE_PURGED.get()));
            else if (handleCandleRitual(event, InitItems.RUNE_GREAT_FIRE.get(), InitItems.CANDLE_FIRE.get()));
            else if (handleCandleRitual(event, InitItems.RUNE_GREAT_LEVITATION.get(), InitItems.CANDLE_LEVITATE.get()));
            else if (handleCandleRitual(event, InitItems.RUNE_ZEN_HEALING.get(), InitItems.CANDLE_ZEN.get()));
            else if (handleCandleRitual(event, InitItems.RUNE_GREAT_MAGIC.get(), InitItems.CANDLE_INVIS.get()));
            else if (handleCandleRitual(event, InitItems.RUNE_GREAT_LUCK.get(), InitItems.CANDLE_LUCK.get()));
            else if (handleCandleRitual(event, InitItems.RUNE_GREAT_ENERGY.get(), InitItems.CANDLE_ENERGY.get()));
            else if (handleCandleRitual(event, InitItems.RUNE_GREAT_MINING.get(), InitItems.CANDLE_CAVERN.get()));
        }
    }

    private boolean handleItemRitual(PlayerInteractEvent.RightClickBlock event, Ritual ritual, Item usedItem, Item ritualResult) {

        if (handleAbstractRitual(event, usedItem, ritual)) {
            ItemHelper.spawnStackAtEntity(event.getWorld(), event.getPlayer(), new ItemStack(ritualResult));
            return true;
        }

        return false;
    }

    private boolean handleCandleRitual(PlayerInteractEvent.RightClickBlock event, Item usedItem, Block ritualResult) {

        BlockPos pos = event.getPos();

        if (handleAbstractRitual(event, usedItem, RitualTypes.RITUAL_CANDLE)) {
            event.getWorld().setBlockState(pos, ritualResult.getDefaultState());
            WorldEffectHelper.spawnLightning(event.getWorld(), pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, true);
            return true;
        }

        return false;
    }

    private boolean handleAbstractRitual(PlayerInteractEvent.RightClickBlock event, Item usedItem, Ritual ritual) {

        PlayerEntity player = (PlayerEntity) event.getEntityLiving();
        ItemStack heldItem = player.getHeldItem(event.getHand());

        if (heldItem.getItem() == usedItem) {

            if (ritual.isRitualComplete(event.getWorld(), event.getPos())) {
                heldItem.shrink(1);
                event.getWorld().playSound(player, player.getPosition(), SoundEvents.BLOCK_CONDUIT_ACTIVATE, SoundCategory.NEUTRAL, 1, 8);
                player.swingArm(event.getHand());
                event.setCancellationResult(ActionResultType.SUCCESS);
                return true;
            }
        }

        return false;
    }
}















