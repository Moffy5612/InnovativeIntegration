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

    public GuiAdvancedForge(InventoryPlayer playerInventory, TileAdvancedForge trf){
        super(new ContainerAdvancedForge(playerInventory, trf));
        this.playerInventory = playerInventory;
        this.trf = trf;
        this.xSize = 176;
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
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_advanced_forge.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
}
