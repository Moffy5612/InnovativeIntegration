package com.moffy5612.iinteg.client.gui;


import com.moffy5612.iinteg.Reference;
import com.moffy5612.iinteg.block.inventory.ContainerAdvancedPartBuilder;
import com.moffy5612.iinteg.block.tileentity.TileAdvancedPartBuilder;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiAdvancedPartBuilder extends GuiContainer{

    public InventoryPlayer playerInventory;
    public com.moffy5612.iinteg.block.tileentity.TileAdvancedPartBuilder trpb;

    public GuiAdvancedPartBuilder(InventoryPlayer player, TileAdvancedPartBuilder trpb) {
        super(new ContainerAdvancedPartBuilder(player, trpb));
        this.trpb = trpb;
        this.playerInventory = player;
        this.xSize = 176;
        this.ySize = 168;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = I18n.format("tile."+Reference.MOD_ID+":advanced_part_builder.name");
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_advanced_part_builder.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
    
}
