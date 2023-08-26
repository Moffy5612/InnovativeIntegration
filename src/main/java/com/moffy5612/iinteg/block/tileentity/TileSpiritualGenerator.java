package com.moffy5612.iinteg.block.tileentity;

import java.util.EnumSet;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.moffy5612.iinteg.capability.item.CapabilityCrystalBall;
import com.moffy5612.iinteg.misc.ModTier;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileSpiritualGenerator extends TileMachineBase implements ITickable{
    
    public static final String NAME = "spiritual_generator";
    
    public int transferredEnergy;

    public SpiritualGeneratorItemStackHandler inventory;

    public TileSpiritualGenerator(){
        super(NAME, null, true);
    }

    public TileSpiritualGenerator(ModTier tier){
        super(NAME, tier, true);

        this.inventory = new SpiritualGeneratorItemStackHandler();
        this.transferredEnergy = getEnergyTransfer();
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
                || capability == CapabilityEnergy.ENERGY;
    }

    public int getEnergyTransfer(){
        return super.getTransfer(0);
    }

    @Override
    public void update() {
        giveEnergy(this.getPos().up());
        giveEnergy(this.getPos().down());
        giveEnergy(this.getPos().north());
        giveEnergy(this.getPos().south());
        giveEnergy(this.getPos().east());
        giveEnergy(this.getPos().west());
    }

    public void giveEnergy(BlockPos pos){
        TileEntity te = this.getWorld().getTileEntity(pos);
        float vector[] = {
            pos.getX() - this.getPos().getX(),
            pos.getY() - this.getPos().getY(),
            pos.getZ() - this.getPos().getZ()
        };
        if(te != null){
            if(te.hasCapability(CapabilityEnergy.ENERGY, EnumFacing.getFacingFromVector(vector[0], vector[1], vector[2]))){
                IEnergyStorage capEnergyStorage = te.getCapability(CapabilityEnergy.ENERGY, EnumFacing.getFacingFromVector(vector[0], vector[1], vector[2]));
                if(capEnergyStorage != null && capEnergyStorage.canReceive())capEnergyStorage.receiveEnergy(this.transferredEnergy, false);
            }
        }
    }

    public class SpiritualGeneratorItemStackHandler extends ItemStackHandler{
        public SpiritualGeneratorItemStackHandler(){
            super(1);
        }

        @Override
        @Nonnull
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            ItemStack empty = ItemStack.EMPTY;

            if(slot == 0){
                CapabilityCrystalBall capabilityCrystalBall = stack.getCapability(CapabilityCrystalBall.CAPABILITY_CRYSTAL_BALL, null);
                if(capabilityCrystalBall != null){
                    if(capabilityCrystalBall.hasOwner){
                        return super.insertItem(slot, stack, simulate);
                    }
                }
                if(empty != null)return empty;
            }
            return super.insertItem(slot, stack, simulate);
        }
    }
}
