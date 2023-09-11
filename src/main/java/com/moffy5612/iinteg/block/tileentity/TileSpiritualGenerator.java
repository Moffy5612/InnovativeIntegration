package com.moffy5612.iinteg.block.tileentity;

import com.moffy5612.iinteg.capability.item.CapabilityCrystalBall;
import com.moffy5612.iinteg.misc.ModTier;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.EnumSkyBlock;

public class TileSpiritualGenerator extends TileMachineBase{
    
    public static final String NAME = "spiritual_generator";
    public boolean hasSkyLight;

    public TileSpiritualGenerator(){
        this(null);
    }

    public TileSpiritualGenerator(ModTier tier){
        super(NAME, tier, true, 1);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        if(compound.hasKey("inventory"))this.inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", this.inventory.serializeNBT());
        return super.writeToNBT(compound);
    }

    @Override
    public void update() {
        this.setSkyLight();
        if(this.hasSkyLight && !this.inventory.getStackInSlot(0).isEmpty()){
            extractEnergy();
        }
    }

    @Override
    public boolean beforeInsertItem(int slot, ItemStack stack, boolean simulate) {
        if(slot == 0){
            CapabilityCrystalBall capabilityCrystalBall = stack.getCapability(CapabilityCrystalBall.CAPABILITY_CRYSTAL_BALL, null);
            if(capabilityCrystalBall != null)return capabilityCrystalBall.hasOwner;
            else return false;
        }
        return true;
    }

    @Override
    public boolean beforeExtractItem(int slot, int amount, boolean simulate) {
        return false;
    }

    public void setSkyLight(){
        if(this.getWorld().provider.hasSkyLight()){
            int light = this.world.getLightFor(EnumSkyBlock.SKY, this.pos.up()) - this.world.getSkylightSubtracted();
            this.hasSkyLight = light > 0;
        }else{
            this.hasSkyLight = false;
        }
    }
}
