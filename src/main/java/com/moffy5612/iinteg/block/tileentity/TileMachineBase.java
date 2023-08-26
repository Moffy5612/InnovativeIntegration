package com.moffy5612.iinteg.block.tileentity;

import javax.annotation.Nullable;

import com.moffy5612.iinteg.misc.ModTier;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.EnergyStorage;

public abstract class TileMachineBase extends ModTileEntityBase{

    public static final int BASE_AMOUNT_ENERGY_STORAGE = 20000;
    public static final int BASE_AMOUNT_ENERGY_TRANSFER = 90;
    public static final int MAX_ENERGY_TRANSFER = 300000;

    public ModTier tier;
    public MachineEnergyStorage energyStorage;
    
    public TileMachineBase(String name, @Nullable ModTier tier, boolean isGenerator){
        super(name);
        this.tier = tier;

        if(tier != null){
            energyStorage = new MachineEnergyStorage(isGenerator);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.tier = ModTier.getTierFromIndex(compound.getInteger("tier"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagCompound nbt = super.writeToNBT(compound);
        nbt.setInteger("tier", this.tier.getIndex());
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        super.deserializeNBT(nbt);
        this.tier = ModTier.getTierFromIndex(nbt.getInteger("tier"));
    }

    public int getTransfer(int itemAmount){
        return BASE_AMOUNT_ENERGY_TRANSFER * (int)Math.pow(10, (double)tier.getIndex()) * (int)Math.pow(1.05, (double)itemAmount);
    };

    public class MachineEnergyStorage extends EnergyStorage{

        public boolean isGenerator;

        public MachineEnergyStorage(boolean isGenerator){
            super(
                BASE_AMOUNT_ENERGY_STORAGE * (int)Math.pow(10, (double)tier.getIndex()), 
                MAX_ENERGY_TRANSFER
            );
            this.isGenerator = isGenerator;
        }

        @Override
        public boolean canExtract() {
            return !this.isGenerator;
        }

        @Override
        public boolean canReceive() {
            return !this.isGenerator;
        }
    }
}
