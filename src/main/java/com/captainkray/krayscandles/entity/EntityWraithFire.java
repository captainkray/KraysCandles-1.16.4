package com.captainkray.krayscandles.entity;

import com.captainkray.krayscandles.init.InitEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class EntityWraithFire extends EntityWraith {

    public EntityWraithFire(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }

    public EntityWraithFire(World world, String playerName, double x, double y, double z) {
        super(InitEntityTypes.WRAITH_FIRE.get(), world, playerName, x, y, z);
    }

    @Override
    public WraithType getWraithType() {
        return WraithType.FIRE;
    }
}
