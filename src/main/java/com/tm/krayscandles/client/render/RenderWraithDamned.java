package com.tm.krayscandles.client.render;

import com.tm.krayscandles.client.model.ModelWraithDamned;
import com.tm.krayscandles.entity.EntityWraithDamned;
import com.tm.krayscandles.main.KCReference;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderWraithDamned extends BipedRenderer<EntityWraithDamned, ModelWraithDamned> {

    private static final float SCALE = 2.5F;

    protected static final ResourceLocation TEXTURE = new ResourceLocation(KCReference.MOD_ID, "textures/entity/wraith.png");

    public RenderWraithDamned(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelWraithDamned(), 0.0F);
    }

    @Override
    protected void preRenderCallback(EntityWraithDamned entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(SCALE, SCALE, SCALE);
    }

    @Override
    protected int getBlockLight(EntityWraithDamned entityIn, BlockPos partialTicks) {
        return 15;
    }

    @Override
    public ResourceLocation getEntityTexture(EntityWraithDamned entity) {
        return TEXTURE;
    }
}
