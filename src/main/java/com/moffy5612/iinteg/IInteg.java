package com.moffy5612.iinteg;

import java.util.logging.Logger;

import com.moffy5612.iinteg.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
    modid = Reference.MOD_ID,
    name = Reference.MOD_NAME,
    version = Reference.MOD_VERSION
)
public class IInteg {

    @Instance
    public static IInteg INSTANCE;

    @SidedProxy(
        modId = Reference.MOD_ID,
        serverSide = Reference.COMMON_PROXY,
        clientSide = Reference.CLIENT_PROXY
    )
    public static CommonProxy proxy;

    public static final Logger LOGGER = Logger.getLogger(Reference.MOD_ID);
    

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event){
        proxy.preInit(event);
    }

    @EventHandler
    public static void init(FMLInitializationEvent event){
        proxy.init(event);
    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);
    }
}
