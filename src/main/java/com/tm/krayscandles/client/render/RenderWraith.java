package com.tm.krayscandles.client.render;

import com.tm.krayscandles.entity.EntityWraith;
import com.tm.krayscandles.main.KCReference;
import com.tm.krayscandles.client.model.ModelWraith;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderWraith extends BipedRenderer<EntityWraith, ModelWraith> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(KCReference.MOD_ID, "textures/entity/wraith.png");

    public RenderWraith(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelWraith(), 0.0F);
    }

    @Override
    protected int getBlockLight(EntityWraith entityIn, BlockPos partialTicks) {
        return 15;
    }

    @Override
    public ResourceLocation getEntityTexture(EntityWraith entity) {
        return TEXTURE;
    }
}
