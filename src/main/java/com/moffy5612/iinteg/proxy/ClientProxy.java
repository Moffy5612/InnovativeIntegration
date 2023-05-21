package com.moffy5612.iinteg.proxy;

import java.util.List;

import com.moffy5612.iinteg.block.ModBlockBase;
import com.moffy5612.iinteg.client.render.ItemRender;
import com.moffy5612.iinteg.event.ModModelBakeEvent;
import com.moffy5612.iinteg.handler.ModBlockHandler;

import net.minecraft.block.BlockContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.Style;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{

    private static final int TOOLTIP_WRAP_WIDTH = 140;

    @Override
    public void preInit(FMLPreInitializationEvent event){
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(new ItemRender());
        MinecraftForge.EVENT_BUS.register(new ModModelBakeEvent());
        
        for(BlockContainer block : ModBlockHandler.REG_BLOCKS){
            if(block instanceof ModBlockBase){
                ((ModBlockBase)block).registerSpecialRenderer();
            }
        }
    }

    @Override
    public void init(FMLInitializationEvent event){
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event){
        super.postInit(event);
    }

    @Override
    public boolean isClient() {
        return true;
    }

    @Override
	public String translate(String key, Style style, Object... args){
		return style.getFormattingCode() + I18n.format(key, args);
	}

    @Override
	public void addMultiLineDescription(List<String> tooltip, String key, Style style, Object... args){
		tooltip.addAll(Minecraft.getMinecraft().fontRenderer.listFormattedStringToWidth(translate(key, style, args), TOOLTIP_WRAP_WIDTH));
	}
}
