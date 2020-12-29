package com.captainkray.krayscandles.tileentity.base;

import com.captainkray.krayscandles.block.base.BlockCandleBase;
import com.captainkray.krayscandles.util.EffectHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

public abstract class TileEntityCandleBase extends TileEntityBase {

    private static final int EFFECT_RANGE = 5;

    public TileEntityCandleBase(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public abstract EffectInstance[] getCandleEffects();

    @Override
    public void tick() {
        super.tick();

        if (world != null) {

            if (world.getGameTime() % 20 == 0) {

                if (getBlockState().getBlock() instanceof BlockCandleBase && getBlockState().get(BlockCandleBase.LIT)) {

                    List<LivingEntity> entities = world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(getPos().getX() - EFFECT_RANGE, getPos().getY() - EFFECT_RANGE, getPos().getZ() - EFFECT_RANGE, getPos().getX() + EFFECT_RANGE, getPos().getY() + EFFECT_RANGE, getPos().getZ() + EFFECT_RANGE));

                    LivingEntity[] entityArray = new LivingEntity[entities.size()];

                    for (int i = 0; i < entities.size(); i++) {
                        entityArray[i] = entities.get(i);
                    }

                    for (EffectInstance effect : getCandleEffects()) {
                        EffectHelper.addPotionEffect(effect.getPotion(), 60, effect.getAmplifier(), entityArray);
                    }
                }
            }
        }
    }
}
