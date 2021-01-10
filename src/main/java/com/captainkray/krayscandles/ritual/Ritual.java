package com.captainkray.krayscandles.ritual;

import com.captainkray.krayscandles.block.base.BlockCandleBase;
import com.captainkray.krayscandles.tileentity.TileEntityStoneAlterTile;
import com.captainkray.krayscandles.tileentity.base.TileEntityCandleBase;
import com.captainkray.krayscandles.util.Location;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ritual {

    private List<RitualIngredient> ingredients = new ArrayList<>();

    public List<RitualIngredient> getIngredients() {
        return ingredients;
    }

    public void addSymmetricalIngredient(RitualIngredient ingredient) {
        ingredients.add(ingredient);
        ingredients.add(ingredient.rotate(Rotation.CLOCKWISE_90));
        ingredients.add(ingredient.rotate(Rotation.COUNTERCLOCKWISE_90));
        ingredients.add(ingredient.rotate(Rotation.CLOCKWISE_180));
    }

    public void addIngredient(RitualIngredient ingredient) {
        ingredients.add(ingredient);
    }

    public void addRitual(Ritual ritual) {
        ingredients.addAll(ritual.getIngredients());
    }

    public void rotateRitual() {

        List<RitualIngredient> rotatedIngredients = new ArrayList<>();

        for (RitualIngredient ingredient : ingredients) {
            rotatedIngredients.add(ingredient.rotate(Rotation.CLOCKWISE_90));
        }

        ingredients = rotatedIngredients;
    }

    public boolean isRitualComplete(World world, BlockPos pos) {

        List<ItemStack> ritualItems = new ArrayList<>();
        List<EntityType<?>> soulTypes = new ArrayList<>();

        for (RitualIngredient ingredient : ingredients) {

            if (ingredient instanceof RitualAlterIngredient) {
                RitualAlterIngredient alterIngredient = (RitualAlterIngredient) ingredient;
                ritualItems.add(alterIngredient.getRitualItem());
            }

            else if (ingredient instanceof RitualSoulCandleIngredient) {
                RitualSoulCandleIngredient candleIngredient = (RitualSoulCandleIngredient) ingredient;
                soulTypes.add(candleIngredient.getSoulType());
            }

            if (!ingredient.isValid(world, pos)) {
                return false;
            }
        }

        for (RitualIngredient ingredient : ingredients) {

            if (ingredient instanceof RitualAlterIngredient) {

                Location location = new Location(world, pos.add(ingredient.getOffset()));

                for (ItemStack ritualItem : ritualItems) {

                    if (ritualItem.isItemEqual(((TileEntityStoneAlterTile) location.getTileEntity()).getRitualStack())) {
                        ritualItems.remove(ritualItem);
                        break;
                    }
                }
            }

            if (ingredient instanceof RitualSoulCandleIngredient) {

                Location location = new Location(world, pos.add(ingredient.getOffset()));

                for (EntityType<?> soulType : soulTypes) {

                    TileEntityCandleBase candle = (TileEntityCandleBase) location.getTileEntity();

                    if (candle.getEntityTypeFromSoul() != null) {

                        if (soulType == candle.getEntityTypeFromSoul()) {
                            soulTypes.remove(soulType);
                            break;
                        }
                    }
                }
            }
        }

        if (ritualItems.isEmpty() && soulTypes.isEmpty()) {

            for (RitualIngredient ingredient : ingredients) {

                if (ingredient instanceof RitualAlterIngredient) {
                    Location location = new Location(world, pos.add(ingredient.getOffset()));
                    ((TileEntityStoneAlterTile) location.getTileEntity()).takeRitualStack();
                }

                else if (ingredient instanceof RitualSoulCandleIngredient) {
                    Location location = new Location(world, pos.add(ingredient.getOffset()));
                    BlockCandleBase.setLit(location, false);
                }
            }

            return true;
        }

        return false;
    }
}
