package com.moffy5612.iinteg.block.tileentity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.moffy5612.iinteg.misc.ModTier;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class TileMachineBase extends ModTileEntityBase implements ITickable{

    public static final int BASE_AMOUNT_ENERGY_STORAGE = 20000;
    public static final int BASE_AMOUNT_ENERGY_TRANSFER = 10;
    public static final int MAX_ENERGY_TRANSFER = Integer.MAX_VALUE;

    public String name;
    public ModTier tier;
    public MachineEnergyStorage energyStorage;
    public boolean isGenerator;
    public MachineInventory inventory;
    
    public TileMachineBase(String name, @Nullable ModTier tier, boolean isGenerator, int inventorySize){
        super(name);
        this.name = name;
        this.tier = tier;
        this.isGenerator = isGenerator;
        this.inventory = new MachineInventory(inventorySize);
        this.energyStorage = new MachineEnergyStorage(isGenerator);

        this.setEnergyTransfer(0);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        if(compound.hasKey("tier"))this.tier = ModTier.getTierFromIndex(compound.getInteger("tier"));
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        if(this.tier != null)compound.setInteger("tier", this.tier.getIndex());
        return super.writeToNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
            || capability == CapabilityEnergy.ENERGY;
    }

    @Override
    @Nullable
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this.inventory);
        else if(capability == CapabilityEnergy.ENERGY)return CapabilityEnergy.ENERGY.cast(this.energyStorage);
        return super.getCapability(capability, facing);
    }

    @Override
    abstract public void update();

    public void extractEnergy(){
        extractEnergy(this.getPos().up());
        extractEnergy(this.getPos().down());
        extractEnergy(this.getPos().north());
        extractEnergy(this.getPos().south());
        extractEnergy(this.getPos().east());
        extractEnergy(this.getPos().west());
    }

    public void extractEnergy(BlockPos pos){
        TileEntity te = this.getWorld().getTileEntity(pos);
        float vector[] = {
            pos.getX() - this.getPos().getX(),
            pos.getY() - this.getPos().getY(),
            pos.getZ() - this.getPos().getZ()
        };
        if(te != null){
            if(te.hasCapability(CapabilityEnergy.ENERGY, EnumFacing.getFacingFromVector(vector[0], vector[1], vector[2]))){
                IEnergyStorage capEnergyStorage = te.getCapability(CapabilityEnergy.ENERGY, EnumFacing.getFacingFromVector(vector[0], vector[1], vector[2]));
                if(capEnergyStorage != null && capEnergyStorage.canReceive())capEnergyStorage.receiveEnergy(this.energyStorage.transferRate, false);
            }
        }
    }

    public void receiveEnergy(){
        receiveEnergy(this.getPos().up());
        receiveEnergy(this.getPos().down());
        receiveEnergy(this.getPos().north());
        receiveEnergy(this.getPos().south());
        receiveEnergy(this.getPos().east());
        receiveEnergy(this.getPos().west());
    }

    public void receiveEnergy(BlockPos pos){
        TileEntity te = this.getWorld().getTileEntity(pos);
        float vector[] = {
            pos.getX() - this.getPos().getX(),
            pos.getY() - this.getPos().getY(),
            pos.getZ() - this.getPos().getZ()
        };
        if(te != null){
            if(te.hasCapability(CapabilityEnergy.ENERGY, EnumFacing.getFacingFromVector(vector[0], vector[1], vector[2]))){
                IEnergyStorage capEnergyStorage = te.getCapability(CapabilityEnergy.ENERGY, EnumFacing.getFacingFromVector(vector[0], vector[1], vector[2]));
                if(capEnergyStorage != null && capEnergyStorage.canExtract()){
                    int extracted = capEnergyStorage.extractEnergy(this.energyStorage.transferRate, false);
                    this.energyStorage.receiveEnergy(extracted, false);
                }
            }
        }
    }

    public void setEnergyTransfer(int itemAmount){
        if(tier == null) this.energyStorage.setEnergyTransfer(BASE_AMOUNT_ENERGY_TRANSFER * (int)Math.pow(1.05, (double)itemAmount));
        else this.energyStorage.setEnergyTransfer(BASE_AMOUNT_ENERGY_TRANSFER * (int)Math.pow(5, (double)tier.getIndex()) * (int)Math.pow(1.05, (double)itemAmount));
    };

    public void onSlotChanged(){

    }

    public boolean beforeInsertItem(int slot, ItemStack stack, boolean simulate){
        return true;
    }

    public void afterInsertItem(int slot, ItemStack stack, boolean simulate){

    }

    public boolean beforeExtractItem(int slot, int amount, boolean simulate){
        return true;
    }

    public void afterExtractItem(int slot, int amount, boolean simulate){

    }

    public boolean isSlotValid(int slot, @Nonnull ItemStack stack){
        return true;
    };

    public class MachineEnergyStorage implements IEnergyStorage{

        public int capacity;
        public int energy;
        public boolean isGenerator;
        public int transferRate;

        public MachineEnergyStorage(boolean isGenerator){
            this.isGenerator = isGenerator;
            this.capacity = BASE_AMOUNT_ENERGY_STORAGE;
            this.energy = 0;
            this.transferRate = BASE_AMOUNT_ENERGY_TRANSFER;
        }

        @Override
        public boolean canExtract() {
            return !this.isGenerator;
        }

        @Override
        public boolean canReceive() {
            return !this.isGenerator;
        }

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            if (!canReceive())
            return 0;

            int energyReceived = Math.min(capacity - energy, Math.min(this.transferRate, maxReceive));
            if (!simulate)
                energy += energyReceived;
            world.markAndNotifyBlock(pos,null, world.getBlockState(pos),world.getBlockState(pos),2);
            return energyReceived;
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            if (!canExtract())
            return 0;

        int energyExtracted = Math.min(energy, Math.min(transferRate, maxExtract));
        if (!simulate)
            energy -= energyExtracted;
        world.markAndNotifyBlock(pos,null, world.getBlockState(pos),world.getBlockState(pos),2);
        return energyExtracted;
        }

        @Override
        public int getEnergyStored() {
            return energy;
        }

        @Override
        public int getMaxEnergyStored() {
            return capacity;
        }

        public void setEnegyStored(int newEnergy){
            this.energy = newEnergy;
        }

        public int getEnergyTransfer(){
            return transferRate;
        }

        public void setEnergyTransfer(int newTransfer){
            this.transferRate = newTransfer;
        }

        public NBTTagCompound serializeNBT(){
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger("capacity", this.capacity);
            nbt.setInteger("storedEnergy", this.energy);
            nbt.setInteger("transfer", this.transferRate);
            nbt.setBoolean("isGenerator", this.isGenerator);
            return nbt;
        }

        public void deserializeNBT(NBTTagCompound nbt){
            this.capacity = nbt.getInteger("capacity");
            this.energy = nbt.getInteger("storedEnergy");
            this.transferRate = nbt.getInteger("transfer");
            this.isGenerator = nbt.getBoolean("isGenerator");
        }
    }

    public class MachineInventory extends ItemStackHandler{
        public MachineInventory(int size){
            super(Math.max(1, size));
        }

        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            markDirty();
            world.markAndNotifyBlock(pos,null, world.getBlockState(pos),world.getBlockState(pos),2);
            onSlotChanged();
        }

        @Override
        @Nonnull
        @SuppressWarnings("null")
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            boolean cancelNeeded = !beforeInsertItem(slot, stack, simulate);
            if(cancelNeeded)return stack;
            ItemStack result = super.insertItem(slot, stack, simulate);
            if(ItemStack.areItemStacksEqual(stack, result))return stack;
            afterInsertItem(slot, stack, simulate);
            return result;
        }

        @Override
        @Nonnull
        @SuppressWarnings("null")
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            boolean cancelNeeded = !beforeExtractItem(slot, amount, simulate);
            if(cancelNeeded)return ItemStack.EMPTY;
            ItemStack result = super.extractItem(slot, amount, simulate);
            if(result.isEmpty())return ItemStack.EMPTY;
            afterExtractItem(slot, amount, simulate);
            return result;
        }
    }
}
