package com.captainkray.krayscandles.entity;

import com.captainkray.krayscandles.init.InitEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class EntityWraithMagic extends EntityWraith {

    public EntityWraithMagic(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }

    public EntityWraithMagic(World world, String playerName, double x, double y, double z) {
        super(InitEntityTypes.WRAITH_MAGIC.get(), world, playerName, x, y, z);
    }

    @Override
    public WraithType getWraithType() {
        return WraithType.MAGIC;
    }
}
