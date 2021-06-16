package com.captainkray.krayscandles.client.render;

import com.captainkray.krayscandles.client.model.ModelVampire;
import com.captainkray.krayscandles.entity.EntityVampire;
import com.captainkray.krayscandles.main.KCReference;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.util.ResourceLocation;

public class RenderVampire extends BipedRenderer<EntityVampire, ModelVampire<EntityVampire>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(KCReference.MOD_ID, "textures/entity/vampire.png");

    public RenderVampire(EntityRendererManager renderManager) {
        super(renderManager, new ModelVampire<>(0.0F, false), 0.5F);
        this.addLayer(new BipedArmorLayer<>(this, new ModelVampire<>(0.5F, true), new ModelVampire<>(1.0F, true)));
    }

    @Override
    public ResourceLocation getEntityTexture(EntityVampire entity) {
        return TEXTURE;
    }
}
