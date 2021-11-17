package com.tm.krayscandles.tileentity;

import com.tm.krayscandles.init.InitTileEntityTypes;
import com.tm.krayscandles.tileentity.base.ISoulFlame;
import com.tm.krayscandles.tileentity.base.TileEntityBase;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.util.Optional;

public class TileEntityLanternSoulTrapped extends TileEntityBase implements ISoulFlame {

    private String entitySoulType = "";
    private String entitySoulName = "";

    public TileEntityLanternSoulTrapped() {
        super(InitTileEntityTypes.LANTERN_SOUL_TRAPPED.get());
    }

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
