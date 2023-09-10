package com.moffy5612.iinteg.client.gui;


import com.moffy5612.iinteg.Reference;
import com.moffy5612.iinteg.block.inventory.ContainerAdvancedForge;
import com.moffy5612.iinteg.block.tileentity.TileAdvancedForge;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiAdvancedForge extends GuiContainer{
    
    public InventoryPlayer playerInventory;
    public TileAdvancedForge trf;

    public final int[] ENERGY_BAR = {202, 0, 4, 66};
    public final int[] PROGRESS_BAR = {208, 0, 22, 15};

    public GuiAdvancedForge(InventoryPlayer playerInventory, TileAdvancedForge trf){
        super(new ContainerAdvancedForge(playerInventory, trf));
        this.playerInventory = playerInventory;
        this.trf = trf;
        this.xSize = 201;
        this.ySize = 174;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = I18n.format("tile."+Reference.MOD_ID+":advanced_forge.name");
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2 - 5, 6, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_advanced_forge.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        this.drawTexturedModalRect(
            this.guiLeft+9, 
            this.guiTop+76-((int)(66 * this.trf.energyStorage.getEnergyStored() / this.trf.energyStorage.getMaxEnergyStored())), 
            ENERGY_BAR[0], 
            ENERGY_BAR[1], 
            ENERGY_BAR[2], 
            (int)(ENERGY_BAR[3] * this.trf.energyStorage.getEnergyStored() / this.trf.energyStorage.getMaxEnergyStored())
        );
        this.drawTexturedModalRect(this.guiLeft + 90, this.guiTop + 38, PROGRESS_BAR[0], PROGRESS_BAR[1], Math.round(this.trf.progress * PROGRESS_BAR[2] / TileAdvancedForge.PROGRESS_MAX), PROGRESS_BAR[3]);
    }
}
