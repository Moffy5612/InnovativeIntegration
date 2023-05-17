package com.moffy5612.iinteg.client.render.tileentity;

import com.moffy5612.iinteg.block.tileentity.TileSpiritualProjector;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.item.ItemStack;

public class SpiritualProjectorRenderer extends ModTileEntityRenderer<TileSpiritualProjector>{
    private int rotate;
    
    public SpiritualProjectorRenderer() {
        super(TileSpiritualProjector.class);
        this.rotate = 0;
    }

    @Override
    public void render(TileSpiritualProjector te, double x, double y, double z, float partialTicks, int destroyStage,
            float alpha) {
        ItemStack crystalBall = te.inventory.getStackInSlot(0);
        ItemStack ironIngot = te.inventory.getStackInSlot(1);
        ItemStack resultStack = te.inventory.getStackInSlot(2);

        GlStateManager.pushMatrix();
        
        GlStateManager.translate(x + 0.5, y + 0.2, z + 0.5);
        GlStateManager.scale(0.5, 0.5, 0.5);
        GlStateManager.rotate(-90, 1F, 0, 0);
        Minecraft.getMinecraft().getRenderItem().renderItem(resultStack.isEmpty() ? ironIngot : resultStack, TransformType.FIXED);
        GlStateManager.rotate(90, 1, 0, 0);
        GlStateManager.translate(0, 1.4 + (0.1 * Math.sin(this.rotate * 2 * Math.PI / 180)), 0);
        GlStateManager.rotate(this.rotate, 0F, 1F, 0F);
        Minecraft.getMinecraft().getRenderItem().renderItem(crystalBall, TransformType.FIXED);
        GlStateManager.popMatrix();

        this.rotate = (this.rotate + 1) % 360;
    }
}
