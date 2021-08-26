package com.captainkray.krayscandles.client.render;

import com.captainkray.krayscandles.client.model.ModelLeprechaun;
import com.captainkray.krayscandles.entity.EntityLeprechaun;
import com.captainkray.krayscandles.main.KCReference;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.util.ResourceLocation;


public class RenderLeprechaun  extends BipedRenderer<EntityLeprechaun, ModelLeprechaun<EntityLeprechaun>> {
    private static final float SCALE = 0.5F;
    protected static final ResourceLocation TEXTURE = new ResourceLocation(KCReference.MOD_ID, "textures/entity/leprechaun.png");

    public RenderLeprechaun(EntityRendererManager renderManager) {
        super(renderManager, new ModelLeprechaun<>(0.0F, false), 0.25F);
        this.addLayer(new BipedArmorLayer<>(this, new ModelLeprechaun<>(0.5F, true), new ModelLeprechaun<>(1.0F, true)));
    }
    @Override
    protected void preRenderCallback(EntityLeprechaun entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(SCALE, SCALE, SCALE);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityLeprechaun entity) {
        return TEXTURE;
    }
}

