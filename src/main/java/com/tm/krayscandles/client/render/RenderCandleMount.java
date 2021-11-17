package com.tm.krayscandles.client.render;

import com.tm.krayscandles.block.BlockSoyCandleMount;
import com.tm.krayscandles.tileentity.TileEntityCandleMount;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderCandleMount extends TileEntityRenderer<TileEntityCandleMount> {

    public RenderCandleMount(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render (TileEntityCandleMount mount, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {

        if (!mount.getCandleStack().isEmpty()) {

            matrixStack.push();

            matrixStack.translate(0.5D, 0.1D, 0.5D);

            Direction facing = mount.getBlockState().get(BlockSoyCandleMount.FACING);

            final double offset = 0.45D;
            final float rotation = 20;

            if (facing == Direction.NORTH) {
                matrixStack.translate(0.0D, 0.0D, offset);
                matrixStack.rotate(Vector3f.XP.rotationDegrees(-rotation));
            }
            else if (facing == Direction.EAST) {
                matrixStack.translate(-offset, 0.0D, 0.0D);
                matrixStack.rotate(Vector3f.ZP.rotationDegrees(-rotation));
            }
            else if (facing == Direction.SOUTH) {
                matrixStack.translate(0.0D, 0.0D, -offset);
                matrixStack.rotate(Vector3f.XP.rotationDegrees(rotation));
            }
            else if (facing == Direction.WEST) {
                matrixStack.translate(offset, 0.0D, 0.0D);
                matrixStack.rotate(Vector3f.ZP.rotationDegrees(rotation));
            }

            matrixStack.scale(3F, 3F, 3F);

            renderItem(mount.getCandleStack(), partialTicks, matrixStack, buffer, combinedLight);

            matrixStack.pop();
        }
    }

    private void renderItem (ItemStack stack, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight) {
        Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.GROUND, combinedLight, OverlayTexture.NO_OVERLAY, matrixStack, buffer);
    }
}
