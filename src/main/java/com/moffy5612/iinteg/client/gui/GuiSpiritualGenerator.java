package com.moffy5612.iinteg.client.gui;

import com.moffy5612.iinteg.Reference;
import com.moffy5612.iinteg.block.inventory.ContainerSpiritualGenerator;
import com.moffy5612.iinteg.block.tileentity.TileSpiritualGenerator;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiSpiritualGenerator extends GuiContainer{

    public TileSpiritualGenerator tsg;
    public final int[] MAIN = {0, 0, 176, 166};
    public final int[] CROSS = {176, 0, 16, 16};

    public GuiSpiritualGenerator(InventoryPlayer inventoryPlayer, TileSpiritualGenerator tsg){
        super(new ContainerSpiritualGenerator(inventoryPlayer, tsg));
        this.tsg = tsg;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = I18n.format("tile."+Reference.MOD_ID+":"+TileSpiritualGenerator.NAME+".name");
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/gui_spiritual_generator.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, MAIN[0], MAIN[1], MAIN[2], MAIN[3]);
        if(!this.tsg.hasSkyLight){
            this.drawTexturedModalRect(this.guiLeft + 81, this.guiTop + 25, CROSS[0], CROSS[1], CROSS[2], CROSS[3]);
        }
    }
}
