package com.tm.krayscandles.entity;

import com.tm.krayscandles.init.InitEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class EntityWraithWater extends EntityWraith {

    public EntityWraithWater(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }

    public EntityWraithWater(World world, String playerName, double x, double y, double z) {
        super(InitEntityTypes.WRAITH_WATER.get(), world, playerName, x, y, z);
    }

    @Override
    public WraithType getWraithType() {
        return WraithType.WATER;
    }
}
