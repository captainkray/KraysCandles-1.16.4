package com.captainkray.krayscandles.client.render;

import com.captainkray.krayscandles.tileentity.TileEntityStoneAlterTile;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderStoneAlterTile extends TileEntityRenderer<TileEntityStoneAlterTile> {

    private long lastTime;
    private float rot;
    private float hover;

    public RenderStoneAlterTile(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render (TileEntityStoneAlterTile mount, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {

        if (!mount.getRitualStack().isEmpty()) {

            long targetTime = 10;
            if (System.currentTimeMillis() - lastTime >= targetTime) {
                lastTime = System.currentTimeMillis();
                rot += 1F;
                rot %= 360;
                hover += 0.025F;
                hover %= 2 * Math.PI;
            }

            matrixStack.push();

            float offset = 0;
            float scale = 1;

            if (mount.getRitualStack().getItem() instanceof BlockItem) {
                offset = -0.125F;
                scale = 1.5F;
            }

            matrixStack.translate(0.5D, 0.5D + offset + (MathHelper.sin(hover) / 5), 0.5D);
            matrixStack.rotate(Vector3f.YP.rotationDegrees(rot));
            matrixStack.scale(scale, scale, scale);

            renderItem(mount.getRitualStack(), partialTicks, matrixStack, buffer, combinedLight);

            matrixStack.pop();
        }
    }

    private void renderItem (ItemStack stack, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight) {
        Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.GROUND, combinedLight, OverlayTexture.NO_OVERLAY, matrixStack, buffer);
    }
}
