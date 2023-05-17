package com.moffy5612.iinteg.client.gui;

import com.moffy5612.iinteg.Reference;
import com.moffy5612.iinteg.block.inventory.ContainerSpiritualProjector;
import com.moffy5612.iinteg.block.tileentity.TileSpiritualProjector;

import mods.flammpfeil.slashblade.client.renderer.GlStateManager;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiSpiritualProjector extends GuiContainer{

    public InventoryPlayer playerInventory;
    public TileSpiritualProjector tsp;
    public final int[] MAIN = {0, 0, 176, 166};
    public final int[] PROGRESS_BAR = {176, 0, 22, 15};
    public final int[] CROSS = {176, 15, 16, 16};

    public GuiSpiritualProjector(InventoryPlayer playerInventory, TileSpiritualProjector tsp){
        super(new ContainerSpiritualProjector(playerInventory, tsp));
        this.playerInventory = playerInventory;
        this.tsp = tsp;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = I18n.format("tile."+Reference.MOD_ID+":"+TileSpiritualProjector.NAME+".name");
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_spiritual_projector.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, MAIN[0], MAIN[1], MAIN[2], MAIN[3]);
        this.drawTexturedModalRect(this.guiLeft + 82, this.guiTop + 38, PROGRESS_BAR[0], PROGRESS_BAR[1], Math.round(this.tsp.progress * PROGRESS_BAR[2] / TileSpiritualProjector.PROGRESS_MAX), PROGRESS_BAR[3]);
        if(!this.tsp.hasSkyLight){
            this.drawTexturedModalRect(this.guiLeft + 18, this.guiTop + 36, CROSS[0], CROSS[1], CROSS[2], CROSS[3]);
        }
    }
}
