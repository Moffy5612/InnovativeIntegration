package com.moffy5612.iinteg.integration.tconstruct;

import com.moffy5612.iinteg.integration.tconstruct.book.conarm.ArmorModifierSectionTransformer;
import com.moffy5612.iinteg.integration.tconstruct.event.TConstructCapabilityEvent;
import com.moffy5612.iinteg.integration.tconstruct.event.TConstrustEvent;
import com.moffy5612.iinteg.integration.tconstruct.handler.ModTraitHandler;
import com.moffy5612.iinteg.misc.util.AnnotationUtil;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;

public class TConstructIntegration{
    public static void preInit(){
        if(!Loader.isModLoaded("tconstruct"))return;

        AnnotationUtil.initHandler(ModTraitHandler.class);

        MinecraftForge.EVENT_BUS.register(new TConstrustEvent());
        MinecraftForge.EVENT_BUS.register(new TConstructCapabilityEvent());
    }

    public static void init(){
        if(!Loader.isModLoaded("tconstruct"))return;
    }

    public static void postInit(){
        if(!Loader.isModLoaded("tconstruct"))return;
        if(Loader.isModLoaded("conarm")){
            ArmorModifierSectionTransformer.add();
        }
    }
}

