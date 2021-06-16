package com.captainkray.krayscandles.client.model;

import com.captainkray.krayscandles.entity.EntityVampire;
import net.minecraft.client.renderer.entity.model.BipedModel;

public class ModelVampire<T extends EntityVampire> extends BipedModel<T> {

    public ModelVampire(float modelSize, boolean hasSmallTexture) {
        super(modelSize, 0, 64, hasSmallTexture ? 32 : 64);
    }
}