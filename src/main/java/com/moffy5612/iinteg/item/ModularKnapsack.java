package com.moffy5612.iinteg.item;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.moffy5612.iinteg.capability.item.CapabilityModularKnapsack;
import com.moffy5612.iinteg.integration.baubles.ModularKnapsackBauble;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.Loader;

public class ModularKnapsack extends ModItemBase{
    
    public static final String NAME = "modular_knapsack";

    public ModularKnapsack(){
        super(NAME);
        this.setMaxDamage(0);
        this.setMaxStackSize(1);
    }

    @Override
    @Nullable
    public EntityEquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EntityEquipmentSlot.CHEST;
    }

    @Override
    @Nullable
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        if(!Loader.isModLoaded("baubles"))return null;
        else return new ModularKnapsackCapabilityProvider();
    }

    private static class ModularKnapsackCapabilityProvider implements ICapabilityProvider{
        public CapabilityModularKnapsack capabilityModularKnapsack;

        public ModularKnapsackCapabilityProvider(){
            this.capabilityModularKnapsack = new CapabilityModularKnapsack();
        }

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            boolean isBaublesCapability = false;
            if(Loader.isModLoaded("baubles"))isBaublesCapability = ModularKnapsackBauble.hasCapability(capability, facing);
            return isBaublesCapability
                || capability == CapabilityModularKnapsack.CAPABILITY_MODULAR_KNAPSACK;
        }

        @Override
        @Nullable
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            if(Loader.isModLoaded("baubles") && ModularKnapsackBauble.isBaublesCapability(capability))return ModularKnapsackBauble.getCapability(capability, facing);
            else if(capability == CapabilityModularKnapsack.CAPABILITY_MODULAR_KNAPSACK)return capabilityModularKnapsack.getCapability(capability, facing);
            return null;
        }
    }
}