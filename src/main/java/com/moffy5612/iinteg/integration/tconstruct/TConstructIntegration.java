package com.moffy5612.iinteg.integration.tconstruct;

import com.moffy5612.iinteg.IInteg;
import com.moffy5612.iinteg.integration.tconstruct.book.conarm.ArmorModifierSectionTransformer;
import com.moffy5612.iinteg.integration.tconstruct.event.CapabilityEvent;
import com.moffy5612.iinteg.integration.tconstruct.handler.TraitHandler;

import c4.conarm.lib.book.ArmoryBook;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TConstructIntegration{
    public static void preInit(){
        if(!Loader.isModLoaded("tconstruct"))return;

        if(Loader.isModLoaded("conarm")){
            if(Loader.isModLoaded("psi")){
                MinecraftForge.EVENT_BUS.register(new CapabilityEvent());

                TraitHandler.REG_ARMOR_TRAIT.add(TraitHandler.PSIONIC);
            }
        }
        if(IInteg.proxy.isClient())preInitClient();
    }

    public static void init(){
        if(!Loader.isModLoaded("tconstruct"))return;
    }

    public static void postInit(){
        if(!Loader.isModLoaded("tconstruct"))return;
        if(Loader.isModLoaded("conarm")){
            ArmoryBook.INSTANCE.addTransformer(new ArmorModifierSectionTransformer());
        }
    }

    @SideOnly(Side.CLIENT)
    private static void preInitClient(){

    }
}

