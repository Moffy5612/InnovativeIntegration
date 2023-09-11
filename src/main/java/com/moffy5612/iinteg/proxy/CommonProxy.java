package com.moffy5612.iinteg.proxy;

import java.util.List;

import com.moffy5612.iinteg.IInteg;
import com.moffy5612.iinteg.capability.ModCapabilityBase;
import com.moffy5612.iinteg.client.gui.IIntegGuiHandler;
import com.moffy5612.iinteg.handler.ModBlockHandler;
import com.moffy5612.iinteg.handler.ModCapabilityHandler;
import com.moffy5612.iinteg.handler.ModItemHandler;
import com.moffy5612.iinteg.handler.ModRecipeHandler;
import com.moffy5612.iinteg.integration.tconstruct.TConstructIntegration;
import com.moffy5612.iinteg.misc.util.AnnotationUtil;
import com.moffy5612.iinteg.registry.Register;

import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(new Register());
        
        AnnotationUtil.initHandler(ModCapabilityHandler.class);
        AnnotationUtil.initHandler(ModItemHandler.class);
        AnnotationUtil.initHandler(ModBlockHandler.class);

        for(ModCapabilityBase capability : ModCapabilityHandler.REG_CAPABILITIES){
            capability.register();
        }
        
        TConstructIntegration.preInit();
    }

    public void init(FMLInitializationEvent event){
        AnnotationUtil.initHandler(ModRecipeHandler.class);
        NetworkRegistry.INSTANCE.registerGuiHandler(IInteg.INSTANCE, new IIntegGuiHandler());

        TConstructIntegration.init();
    }

    public void postInit(FMLPostInitializationEvent event){
        

        TConstructIntegration.postInit();
    }

    public boolean isClient(){
        return false;
    }

    public String translate(String key, Object... args){
		return translate(key, new Style(), args);
	}

    public String translate(String key, Style style, Object... args){
		return key;
	}

    public void addMultiLineDescription(List<String> tooltip, String key, Style style, Object... args){}

    public void addMultiLineDescription(List<String> tooltip, String key, Object... args){
      this.addMultiLineDescription(tooltip, key, new Style().setColor(TextFormatting.GRAY), args);
    }
}
