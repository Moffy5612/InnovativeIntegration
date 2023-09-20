package com.moffy5612.iinteg.integration.baubles;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.api.cap.BaublesCapabilities;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

@SuppressWarnings("unchecked")
public class ModularKnapsackBauble{

    public static boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == BaublesCapabilities.CAPABILITY_ITEM_BAUBLE;
    }

    public static <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return (T)(IBauble)itemstack -> BaubleType.BODY;
    }
    
    public static <T> boolean isBaublesCapability(Capability<T> capability){
        return capability == BaublesCapabilities.CAPABILITY_ITEM_BAUBLE;
    }
}
