package com.captainkray.krayscandles.ritual;

import com.captainkray.krayscandles.tileentity.TileEntityStoneAlterTile;
import com.captainkray.krayscandles.tileentity.base.TileEntityCandleBase;
import com.captainkray.krayscandles.util.Location;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RitualRecipe {

    private final boolean baseRecipe;
    private RitualStructure ritualStructure;

    private final List<Item> alterItems = new ArrayList<>();
    private final List<EntityType<?>> soulFlameTypes = new ArrayList<>();

    private Item handItem = Items.AIR;
    private Item dropResult;
    private Block blockResult;

    public RitualRecipe(RitualStructure ritualStructure, boolean baseRecipe) {
        this.baseRecipe = baseRecipe;
        this.ritualStructure = ritualStructure;
        RitualRecipes.allRitualRecipes.add(this);
    }

    public RitualRecipe(RitualStructure ritualStructure) {
        this(ritualStructure, false);
    }

    public RitualRecipe() {
        this(null);
    }

    public void addBaseRecipe(RitualRecipe baseRecipe) {
        ritualStructure = baseRecipe.ritualStructure;
        alterItems.addAll(baseRecipe.alterItems);
        soulFlameTypes.addAll(baseRecipe.soulFlameTypes);
        handItem = baseRecipe.handItem;
    }

    public void addIngredient(Item alterItem) {
        addIngredient(alterItem, 1);
    }

    public void addIngredient(Item alterItem, int count) {

        for (int i = 0; i < count; i++) {
            alterItems.add(alterItem);
        }
    }

    public void addIngredient(EntityType<?> soulFlameType) {
        soulFlameTypes.add(soulFlameType);
    }

    public RitualStructure getRitualStructure() {
        return ritualStructure;
    }

    public Item getHandItem() {
        return handItem;
    }

    public Item getDropResult() {
        return dropResult;
    }

    public Block getBlockResult() {
        return blockResult;
    }

    public List<Item> getAlterItems() {
        return alterItems;
    }

    public void setHandItem(Item handItem) {
        this.handItem = handItem;
    }

    public void setDropResult(Item dropResult) {
        this.dropResult = dropResult;
    }

    public void setBlockResult(Block blockResult) {
        this.blockResult = blockResult;
    }

    public boolean isRitualComplete(World world, BlockPos pos) {

        if (baseRecipe) {
            return false;
        }

        if (getRitualStructure() == null) {
            return false;
        }

        if (!getRitualStructure().isStructureComplete(world, pos)) {
            return false;
        }

        List<Item> alterItemsCopy = new ArrayList<>(this.alterItems);
        List<EntityType<?>> soulFlameTypesCopy = new ArrayList<>(this.soulFlameTypes);

        if (searchRitualStructure(world, pos, alterItemsCopy, soulFlameTypesCopy, getRitualStructure())) {

            for (RitualBlock ritualBlock : getRitualStructure().getRitualBlocks()) {
                ritualBlock.onRitualComplete(world, pos);
            }

            return true;
        }

        if (searchRitualStructure(world, pos, alterItemsCopy, soulFlameTypesCopy, getRitualStructure().getRotatedRitual())) {

            for (RitualBlock ritualBlock : getRitualStructure().getRotatedRitual().getRitualBlocks()) {
                ritualBlock.onRitualComplete(world, pos);
            }

            return true;
        }

        return false;
    }

    private boolean searchRitualStructure(World world, BlockPos pos, List<Item> alterItems, List<EntityType<?>> soulFlameTypes, RitualStructure structure) {

        for (RitualBlock ritualBlock : structure.getRitualBlocks()) {

            Location location = ritualBlock.getLocation(world, pos);

            if (ritualBlock instanceof RitualBlockAlter) {

                if (location.getTileEntity() instanceof TileEntityStoneAlterTile) {

                    for (Item alterItem : alterItems) {

                        if (alterItem.equals(((TileEntityStoneAlterTile) location.getTileEntity()).getRitualStack().getItem())) {
                            alterItems.remove(alterItem);
                            break;
                        }
                    }
                }
            }

            else if (ritualBlock instanceof RitualBlockCandle) {

                for (EntityType<?> soulFlameType : soulFlameTypes) {

                    if (location.getTileEntity() instanceof TileEntityCandleBase) {

                        TileEntityCandleBase candle = (TileEntityCandleBase) location.getTileEntity();

                        if (candle.getEntityTypeFromSoul() != null) {

                            if (soulFlameType == candle.getEntityTypeFromSoul()) {
                                soulFlameTypes.remove(soulFlameType);
                                break;
                            }
                        }
                    }
                }
            }
        }

        return (alterItems.isEmpty() && soulFlameTypes.isEmpty());
    }
}
