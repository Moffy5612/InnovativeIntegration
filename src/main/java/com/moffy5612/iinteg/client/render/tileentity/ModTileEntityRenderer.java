package com.moffy5612.iinteg.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public abstract class ModTileEntityRenderer<T extends TileEntity> extends TileEntitySpecialRenderer<T>{
    
    public Class<? extends TileEntity> cls;
    
    public ModTileEntityRenderer(Class<? extends TileEntity> cls){
        this.cls = cls;
    }

    @Override
    public abstract void render(T te, double x, double y, double z, float partialTicks, int destroyStage, float alpha);
}
