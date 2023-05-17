package com.moffy5612.iinteg.capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ModCapabilityProvider implements ICapabilitySerializable<NBTTagCompound>{
    private ModCapabilityBase capability;

        public ModCapabilityProvider(ModCapabilityBase capability){
            this.capability = capability;
        }

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return this.capability.hasCapability(capability, facing);
        }

        @Override
        @Nullable
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            if(hasCapability(capability, facing))return this.capability.getCapability(capability, facing);
            return null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return this.capability.serializeNBT();
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            this.capability.deserializeNBT(nbt);
        }

        public String getCapabilityName(){
            return this.capability.getName();
        }
}
