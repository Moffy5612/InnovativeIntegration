package com.moffy5612.iinteg.integration.tconstruct.event;

import com.moffy5612.iinteg.Reference;
import com.moffy5612.iinteg.integration.tconstruct.capability.ArmorPSICastableCapability;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.tinkering.ITinkerable;

public class TConstructCapabilityEvent {
    private static final ResourceLocation TINKERS_SOCKETABLE_TOOL = new ResourceLocation(Reference.MOD_ID, "iintegtinkerssocketable");

    @SubscribeEvent
    public void attachItemCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        if (event.getObject().getItem() instanceof ITinkerable) {
            if(Loader.isModLoaded("conarm") && Loader.isModLoaded("psi"))
                event.addCapability(TINKERS_SOCKETABLE_TOOL, new ArmorPSICastableCapability(event.getObject()));
        }
    }
}
