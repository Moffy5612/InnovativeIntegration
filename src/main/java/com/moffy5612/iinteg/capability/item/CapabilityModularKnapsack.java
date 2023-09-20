package com.moffy5612.iinteg.capability.item;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.moffy5612.iinteg.capability.ModCapabilityBase;
import com.moffy5612.iinteg.item.module.IKnapsackModule;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.items.ItemStackHandler;

public class CapabilityModularKnapsack extends ModCapabilityBase{
    @CapabilityInject(CapabilityModularKnapsack.class)
    public static final Capability<CapabilityModularKnapsack> CAPABILITY_MODULAR_KNAPSACK = null;

    public static final String NAME = "capability.modular_knapsack";

    public ModuleInventory inventory;

    public CapabilityModularKnapsack(){
        this.inventory = new ModuleInventory();
    }

    @Override
    public void register() {
        CapabilityManager.INSTANCE.register(CapabilityModularKnapsack.class, getStorage(this), CapabilityModularKnapsack::new);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CAPABILITY_MODULAR_KNAPSACK;
    }

    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == CAPABILITY_MODULAR_KNAPSACK)return CAPABILITY_MODULAR_KNAPSACK.cast(this);
        else return null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = super.serializeNBT();
        compound.setTag("inventory", this.inventory.serializeNBT());
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        if(nbt.hasKey("inventory"))this.inventory.deserializeNBT(nbt.getCompoundTag("inventory"));
        super.deserializeNBT(nbt);
    }

    @Override
    public String getName() {
        return NAME;
    }

    private class ModuleInventory extends ItemStackHandler{
        public ModuleInventory(){
            super(8);
        }

        @Override
        @Nonnull
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            if(!(stack.getItem() instanceof IKnapsackModule))return stack;
            return super.insertItem(slot, stack, simulate);
        }

        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            return super.isItemValid(slot, stack)
                && stack.getItem() instanceof IKnapsackModule;
        }
    }
}