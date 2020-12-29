package com.captainkray.krayscandles.ritual;

import com.captainkray.krayscandles.init.InitItems;
import com.captainkray.krayscandles.tileentity.TileEntityStoneAlterTile;
import com.captainkray.krayscandles.util.Location;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualAlterIngredient extends RitualIngredient {

    private final ItemStack ritualItem;

    public RitualAlterIngredient(ItemStack ritualItem, int x, int y, int z) {
        super(InitItems.STONE_ALTAR_TILE.get(), x, y, z);
        this.ritualItem = ritualItem;
    }

    public RitualAlterIngredient(ItemStack ritualItem, BlockPos offset) {
        super(InitItems.STONE_ALTAR_TILE.get().getDefaultState(), offset);
        this.ritualItem = ritualItem;
    }

    @Override
    public RitualAlterIngredient rotate(Rotation rotation) {
        return new RitualAlterIngredient(ritualItem, getOffset().rotate(rotation));
    }

    @Override
    public boolean isValid(World world, BlockPos pos) {

        boolean correctIngredient = false;

        Location location = new Location(world, pos.add(getOffset()));

        if (location.getTileEntity() instanceof TileEntityStoneAlterTile) {

            TileEntityStoneAlterTile alterTile = (TileEntityStoneAlterTile) location.getTileEntity();

            if (alterTile.getRitualStack().isItemEqual(ritualItem)) {
                correctIngredient = true;
            }
        }

        return super.isValid(world, pos) && correctIngredient;
    }
}
