package com.captainkray.krayscandles.tileentity.candle;

import com.captainkray.krayscandles.block.base.BlockCandleBase;
import com.captainkray.krayscandles.block.candle.BlockCandleNull;
import com.captainkray.krayscandles.init.InitTileEntityTypes;
import com.captainkray.krayscandles.tileentity.base.TileEntityCandleBase;
import com.captainkray.krayscandles.util.Location;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;

import java.util.ArrayList;
import java.util.List;

public class TileEntityCandleNull extends TileEntityCandleBase {

    private final List<Location> area = new ArrayList<>();

    public TileEntityCandleNull() {
        super(InitTileEntityTypes.CANDLE_NULL.get());
    }

    @Override
    public EffectInstance[] getCandleEffects() {
        return new EffectInstance[] {};
    }

    @Override
    public void onEntityEffect(LivingEntity entity) {
        entity.clearActivePotions();
    }

    private void setArea() {

        for (int x = -EFFECT_RANGE; x <= EFFECT_RANGE; x++) {
            for (int y = -EFFECT_RANGE; y <= EFFECT_RANGE; y++) {
                for (int z = -EFFECT_RANGE; z <= EFFECT_RANGE; z++) {
                    area.add(new Location(world, getLocation().x + x, getLocation().y + y, getLocation().z + z));
                }
            }
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (area.isEmpty()) {
            setArea();
        }

        if (world != null) {

            if (world.getGameTime() % 20 == 0) {

                if (getBlockState().getBlock() instanceof BlockCandleBase && getBlockState().get(BlockCandleBase.LIT)) {

                    for (Location location : area) {

                        if (location.getBlock() instanceof BlockCandleBase && !(location.getTileEntity() instanceof TileEntityCandleNull)) {
                            BlockCandleBase.extinguishCandle(location);
                        }
                    }
                }
            }
        }
    }
}
