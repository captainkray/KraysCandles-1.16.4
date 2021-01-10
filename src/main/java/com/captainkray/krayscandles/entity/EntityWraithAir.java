package com.captainkray.krayscandles.entity;

import com.captainkray.krayscandles.init.InitEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class EntityWraithAir extends EntityWraith {

    public EntityWraithAir(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }

    public EntityWraithAir(World world, String playerName, double x, double y, double z) {
        super(InitEntityTypes.WRAITH_AIR.get(), world, playerName, x, y, z);
    }

    @Override
    public WraithType getWraithType() {
        return WraithType.AIR;
    }
}
