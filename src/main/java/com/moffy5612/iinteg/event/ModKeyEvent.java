package com.moffy5612.iinteg.event;

import com.moffy5612.iinteg.Reference;
import com.moffy5612.iinteg.client.gui.IIntegGuiList;
import com.moffy5612.iinteg.proxy.ClientProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.GuiScreenEvent.KeyboardInputEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModKeyEvent {
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onKeyPress(KeyboardInputEvent event){
        if(ClientProxy.KEY_MODULAR_KNAPSACK_GUI.isPressed()){
            EntityPlayerSP player = Minecraft.getMinecraft().player;
            player.openGui(Reference.MOD_ID, IIntegGuiList.GUI_MODULAR_KNAPSACK, player.world, (int)player.posX, (int)player.posY, (int)player.posZ);
        }
    }
}
