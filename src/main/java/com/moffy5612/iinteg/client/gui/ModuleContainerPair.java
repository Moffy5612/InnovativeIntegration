package com.moffy5612.iinteg.client.gui;

import com.moffy5612.iinteg.capability.ModCapabilityBase;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.relauncher.Side;

public class ModuleContainerPair {
    
    public int id;
    public Class<? extends GuiContainer> guiContainerClass;
    public Class<? extends Container> containerClass;

    public ModuleContainerPair(int id, Class<? extends GuiContainer> guiContainerClass, Class<? extends Container> containerClass){
        this.id = id;
        this.guiContainerClass = guiContainerClass;
        this.containerClass = containerClass;
    }

    public Object getContainer(Side side, EntityPlayer player, ModCapabilityBase capability){
        try{
            if(side == Side.SERVER)return containerClass.getDeclaredConstructor().newInstance(player, capability);
            else return guiContainerClass.getDeclaredConstructor().newInstance(player, capability);
        }catch(Exception e){
            return null;
        }
    }
}
