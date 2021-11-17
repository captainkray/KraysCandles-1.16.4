package com.tm.krayscandles.event;

import com.tm.api.calemicore.util.Location;
import com.tm.api.calemicore.util.helper.ItemHelper;
import com.tm.api.calemicore.util.helper.SoundHelper;
import com.tm.api.calemicore.util.helper.WorldEffectHelper;
import com.tm.krayscandles.entity.EntityWraithDamned;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.ritual.RitualRecipes;
import com.tm.krayscandles.ritual.RitualStructureTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RitualEvent {

    @SubscribeEvent
    public void onRitual(PlayerInteractEvent.RightClickBlock event) {

        PlayerEntity player = event.getPlayer();

        World world = event.getWorld();
        BlockPos pos = event.getPos();
        Location location = new Location(world, pos);

        if (event.getHand() == Hand.MAIN_HAND) {

            for (RitualRecipe recipe : RitualRecipes.allRitualRecipes) {

                boolean handAir = recipe.getHandItem().equals(Items.AIR);

                if (handAir || recipe.getHandItem().equals(player.getHeldItem(Hand.MAIN_HAND).getItem())) {

                    if (recipe.isRitualComplete(world, pos, player)) {

                        if (recipe.getBlockResult() != null) {
                            location.setBlock(recipe.getBlockResult());
                        }

                        if (recipe.getDropResult() != null) {
                            ItemHelper.spawnStackAtEntity(world, player, new ItemStack(recipe.getDropResult()));
                        }

                        if (recipe.getRitualStructure() == RitualStructureTypes.CANDLE) {
                            WorldEffectHelper.spawnLightning(world, location.x + 0.5F, location.y, location.z + 0.5F, true);
                        }

                        else if (recipe == RitualRecipes.WRAITH) {
                            world.addEntity(new EntityWraithDamned(world, pos));
                        }

                        if (!handAir) player.getHeldItem(Hand.MAIN_HAND).shrink(1);
                        player.swingArm(Hand.MAIN_HAND);

                        SoundHelper.playSoundAtLocation(location, recipe.getRitualStructure().getSound(), SoundCategory.BLOCKS, 1, 1);

                        return;
                    }
                }
            }
        }
    }
}















