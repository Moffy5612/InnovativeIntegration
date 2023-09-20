package com.moffy5612.iinteg.capability.item;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.moffy5612.iinteg.capability.ModCapabilityBase;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityCrystalBall extends ModCapabilityBase{
    
    @CapabilityInject(CapabilityCrystalBall.class)
    public static final Capability<CapabilityCrystalBall> CAPABILITY_CRYSTAL_BALL = null;

    public static final String NAME = "capability.crystal_ball";
    public boolean hasOwner;
    public String ownerName;

    public CapabilityCrystalBall(){
        this.hasOwner = false;
        this.ownerName = "";
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = super.serializeNBT();
        compound.setBoolean("hasOwner", this.hasOwner);
        compound.setString("ownerName", this.ownerName);
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.hasOwner = nbt.getBoolean("hasOwner");
        this.ownerName = nbt.getString("ownerName");
    }

    @Override
    public void register() {
        CapabilityManager.INSTANCE.register(CapabilityCrystalBall.class, this.getStorage(this), CapabilityCrystalBall::new);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CAPABILITY_CRYSTAL_BALL;
    }

    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if(hasCapability(capability, facing))return CAPABILITY_CRYSTAL_BALL.cast(this);
        return null;
    }
    
}
