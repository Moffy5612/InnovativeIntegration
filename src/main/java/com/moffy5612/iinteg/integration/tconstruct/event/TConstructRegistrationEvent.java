package com.moffy5612.iinteg.integration.tconstruct.event;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.events.TinkerRegisterEvent;
import slimeknights.tconstruct.library.materials.Material;

public class TConstructRegistrationEvent {
    @SubscribeEvent
    public static void onTraitRegister(TinkerRegisterEvent<Material> event){
        
    }
}
