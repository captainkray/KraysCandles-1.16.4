package com.captainkray.krayscandles.client.model;

import com.captainkray.krayscandles.entity.EntityLeprechaun;
import net.minecraft.client.renderer.entity.model.BipedModel;


public class ModelLeprechaun<T extends EntityLeprechaun> extends BipedModel<T> {

    public ModelLeprechaun(float modelSize, boolean hasSmallTexture) {
        super(modelSize, 0, 64, hasSmallTexture ? 32 : 64);
    }
}