package com.captainkray.krayscandles.tileentity.base;

import com.captainkray.krayscandles.block.base.BlockCandleBase;
import com.captainkray.krayscandles.util.EffectHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.registry.Registry;

import java.util.List;
import java.util.Optional;

public abstract class TileEntityCandleBase extends TileEntityBase {

    private static final int EFFECT_RANGE = 5;

    private String entityFilterString;

    public TileEntityCandleBase(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public abstract EffectInstance[] getCandleEffects();

    public EntityType<?> getEntityTypeFilter() {
        Optional<EntityType<?>> entityType = Registry.ENTITY_TYPE.getOptional(new ResourceLocation(entityFilterString));
        return entityType.orElse(null);
    }

    public void setEntityFilterString(String entityFilterString) {
        this.entityFilterString = entityFilterString;
    }

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

                    for (LivingEntity livingEntity : entityArray) {

                        EntityType<?> type = getEntityTypeFilter();

                        if (type == null || livingEntity.getType().equals(type)) {

                            for (EffectInstance effect : getCandleEffects()) {
                                EffectHelper.addPotionEffect(effect.getPotion(), 60, effect.getAmplifier(), livingEntity);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void read (BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);

        entityFilterString = nbt.getString("entity_filter_string");
    }

    @Override
    public CompoundNBT write (CompoundNBT nbt) {
        nbt.putString("entity_filter_string", entityFilterString);
        return super.write(nbt);
    }
}
