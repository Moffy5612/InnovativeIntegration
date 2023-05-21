package com.moffy5612.iinteg.item;

import com.moffy5612.iinteg.Reference;
import com.moffy5612.iinteg.handler.ModItemHandler;

import net.minecraft.item.Item;

public abstract class ModItemBase extends Item{
    
    public ModItemBase(String name){
        super();
        this.setRegistryName(Reference.MOD_ID, name);
        this.setUnlocalizedName(Reference.MOD_ID+":"+name);
        this.setCreativeTab(Reference.MOD_CREATIVE_TAB);
    }

    public void register(){
        ModItemHandler.REG_ITEMS.add(this);
    }
}
