package com.captainkray.krayscandles.ritual;

import com.captainkray.krayscandles.tileentity.TileEntityStoneAlterTile;
import com.captainkray.krayscandles.util.Location;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class Ritual {

    private final List<RitualIngredient> ingredients = new ArrayList<>();

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

    public boolean isRitualComplete(World world, BlockPos pos) {

        List<ItemStack> ritualItems = new ArrayList<>();

        for (RitualIngredient ingredient : ingredients) {

            if (ingredient instanceof RitualAlterIngredient) {
                RitualAlterIngredient alterIngredient = (RitualAlterIngredient) ingredient;
                ritualItems.add(alterIngredient.getRitualItem());
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
        }

        if (ritualItems.isEmpty()) {

            for (RitualIngredient ingredient : ingredients) {

                if (ingredient instanceof RitualAlterIngredient) {
                    Location location = new Location(world, pos.add(ingredient.getOffset()));
                    ((TileEntityStoneAlterTile) location.getTileEntity()).takeRitualStack();
                }
            }

            return true;
        }

        return false;
    }
}
