package com.moffy5612.iinteg.block.tileentity;

import com.moffy5612.iinteg.Reference;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntity extends TileEntity{
    public String name;

    public ModTileEntity(String name){
        super();
        this.name = name;
    }

    public void register(){
        GameRegistry.registerTileEntity(this.getClass(), new ResourceLocation(Reference.MOD_ID, this.name));
    }
}
