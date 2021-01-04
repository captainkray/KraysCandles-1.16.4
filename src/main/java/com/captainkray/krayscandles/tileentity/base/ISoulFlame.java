package com.captainkray.krayscandles.tileentity.base;

import net.minecraft.entity.EntityType;

public interface ISoulFlame {

    String getSoulType();
    String getSoulName();

    EntityType<?> getEntityTypeFromSoul();

    void setSoul(String soulType, String soulName);
}
