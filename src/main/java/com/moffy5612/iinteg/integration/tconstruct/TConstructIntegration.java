package com.moffy5612.iinteg.integration.tconstruct;

import com.moffy5612.iinteg.integration.tconstruct.book.conarm.ArmorModifierSectionTransformer;
import com.moffy5612.iinteg.integration.tconstruct.handler.ModTraitHandler;
import com.moffy5612.iinteg.misc.util.AnnotationUtil;

import net.minecraftforge.fml.common.Loader;

public class TConstructIntegration{
    public static void preInit(){
        if(!Loader.isModLoaded("tconstruct"))return;
        
        AnnotationUtil.initHandler(ModTraitHandler.class);
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

