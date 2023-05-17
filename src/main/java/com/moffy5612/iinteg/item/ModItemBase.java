package com.moffy5612.iinteg.item;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nonnull;

import com.moffy5612.iinteg.Reference;
import com.moffy5612.iinteg.handler.ItemHandler;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Loader;

public class ModItemBase extends Item{
    
    public ModItemBase(String name){
        super();
        this.setRegistryName(Reference.MOD_ID, name);
        this.setUnlocalizedName(Reference.MOD_ID+":"+name);
        this.setCreativeTab(Reference.MOD_CREATIVE_TAB);
    }

    public boolean register(){
        boolean foundAllMods = true;
        for(String id : this.getRequiredMods()){
            if(!Loader.isModLoaded(id))foundAllMods = false;
        }
        if(foundAllMods){
            ItemHandler.REG_ITEMS.add(this);
        }
        return foundAllMods;
    }

    @Nonnull
    public Set<String> getRequiredMods() {
        return new HashSet<>();
    }
}
