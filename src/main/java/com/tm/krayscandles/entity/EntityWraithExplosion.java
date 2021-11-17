package com.tm.krayscandles.entity;

import com.tm.krayscandles.init.InitEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class EntityWraithExplosion extends EntityWraith {

    public EntityWraithExplosion(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }

    public EntityWraithExplosion(World world, String playerName, double x, double y, double z) {
        super(InitEntityTypes.WRAITH_EXPLOSION.get(), world, playerName, x, y, z);
    }

    @Override
    public WraithType getWraithType() {
        return WraithType.EXPLOSION;
    }
}
