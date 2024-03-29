package com.tm.krayscandles.tileentity.base;

import com.tm.api.calemicore.util.helper.EffectHelper;
import com.tm.krayscandles.block.base.BlockCandleBase;
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

public abstract class TileEntityCandleBase extends TileEntityBase implements ISoulFlame {

    public static final int EFFECT_RANGE = 8;

    private String entitySoulType = "";
    private String entitySoulName = "";

    public TileEntityCandleBase(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public abstract EffectInstance[] getCandleEffects();

    public void onEntityEffect(LivingEntity entity) {};

    @Override
    public String getSoulType() {
        return entitySoulType;
    }

    @Override
    public String getSoulName() {
        return entitySoulName;
    }

    public EntityType<?> getEntityTypeFromSoul() {
        Optional<EntityType<?>> entityType = Registry.ENTITY_TYPE.getOptional(new ResourceLocation(entitySoulType));
        return entityType.orElse(null);
    }

    @Override
    public void setSoul(String soulType, String soulName) {
        this.entitySoulType = soulType;
        this.entitySoulName = soulName;
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

                        EntityType<?> type = getEntityTypeFromSoul();

                        if (type == null || livingEntity.getType().equals(type)) {

                            for (EffectInstance effect : getCandleEffects()) {
                                EffectHelper.addPotionEffect(effect.getPotion(), 60, effect.getAmplifier(), livingEntity);
                            }

                            onEntityEffect(livingEntity);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void read (BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        entitySoulType = nbt.getString("entity_soul_type");
        entitySoulName = nbt.getString("entity_soul_name");
    }

    @Override
    public CompoundNBT write (CompoundNBT nbt) {
        nbt.putString("entity_soul_type", entitySoulType);
        nbt.putString("entity_soul_name", entitySoulName);
        return super.write(nbt);
    }
}
