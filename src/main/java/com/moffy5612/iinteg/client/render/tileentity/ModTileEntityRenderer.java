package com.moffy5612.iinteg.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class ModTileEntityRenderer<T extends TileEntity> extends TileEntitySpecialRenderer<T>{
    
    public Class<? extends TileEntity> cls;
    
    public ModTileEntityRenderer(Class<? extends TileEntity> cls){
        this.cls = cls;
    }
}
