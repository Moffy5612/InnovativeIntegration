package com.moffy5612.iinteg.capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.util.INBTSerializable;

public abstract class ModCapabilityBase implements INBTSerializable<NBTTagCompound>{

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
    }

    public <T extends ModCapabilityBase> IStorage<T> getStorage(T t){
        IStorage<T> iStorage = new IStorage<T>() {
            @Override
                public void readNBT(Capability<T> capability, T instance,
                        EnumFacing side, NBTBase nbt) {
                }

                @Override
                @Nullable
                public NBTBase writeNBT(Capability<T> capability, T instance,
                        EnumFacing side) {
                    return null;
                }
        };
        return iStorage;
    }

    public abstract void register();

    public abstract boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing);

    public abstract <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing);

    public abstract String getName();
    
}
