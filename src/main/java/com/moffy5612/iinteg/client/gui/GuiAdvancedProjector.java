package com.moffy5612.iinteg.client.gui;

import com.moffy5612.iinteg.Reference;
import com.moffy5612.iinteg.block.inventory.ContainerAdvancedProjector;
import com.moffy5612.iinteg.block.tileentity.TileAdvancedProjector;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiAdvancedProjector extends GuiContainer{
    public final int[] ENERGY_BAR = {202, 0, 4, 66};
    public final int[] PROGRESS_BAR_MAIN = {207, 0, 38, 15};
    public final int[] PROGRESS_BAR_ABOVE = {208, 16, 16, 8};
    public final int[] PROGRESS_BAR_BELOW = {225, 16, 16, 8};

    public TileAdvancedProjector tap;

    public GuiAdvancedProjector(InventoryPlayer inventoryPlayer, TileAdvancedProjector tap){
        super(new ContainerAdvancedProjector(inventoryPlayer, tap));
        this.tap = tap;
        this.xSize = 201;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_advanced_projector.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        this.drawTexturedModalRect(
            this.guiLeft+9, 
            this.guiTop+76-((int)(66 * this.tap.energyStorage.getEnergyStored() / this.tap.energyStorage.getMaxEnergyStored())), 
            ENERGY_BAR[0], 
            ENERGY_BAR[1], 
            ENERGY_BAR[2], 
            (int)(ENERGY_BAR[3] * this.tap.energyStorage.getEnergyStored() / this.tap.energyStorage.getMaxEnergyStored())
        );
        this.drawTexturedModalRect(this.guiLeft + 75, this.guiTop + 38, PROGRESS_BAR_MAIN[0], PROGRESS_BAR_MAIN[1], Math.round(this.tap.progress * PROGRESS_BAR_MAIN[2] / TileAdvancedProjector.PROGRESS_MAX), PROGRESS_BAR_MAIN[3]);
        this.drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 32, PROGRESS_BAR_ABOVE[0], PROGRESS_BAR_ABOVE[1], PROGRESS_BAR_ABOVE[2], Math.round(this.tap.progress * PROGRESS_BAR_ABOVE[3] * 2/ TileAdvancedProjector.PROGRESS_MAX));
        this.drawTexturedModalRect(
            this.guiLeft + 83, 
            this.guiTop + 59 - (PROGRESS_BAR_BELOW[3] * this.tap.progress * 2 / TileAdvancedProjector.PROGRESS_MAX), 
            PROGRESS_BAR_BELOW[0], 
            PROGRESS_BAR_BELOW[1] + PROGRESS_BAR_BELOW[3] - (PROGRESS_BAR_BELOW[3] * this.tap.progress * 2 / TileAdvancedProjector.PROGRESS_MAX), 
            PROGRESS_BAR_ABOVE[2], 
            Math.round(this.tap.progress * PROGRESS_BAR_ABOVE[3] * 2/ TileAdvancedProjector.PROGRESS_MAX));
    }
}
