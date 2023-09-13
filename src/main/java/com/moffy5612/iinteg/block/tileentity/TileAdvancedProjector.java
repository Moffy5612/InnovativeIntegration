package com.moffy5612.iinteg.block.tileentity;

import javax.annotation.Nullable;

import com.moffy5612.iinteg.capability.item.CapabilityCrystalBall;
import com.moffy5612.iinteg.handler.ModRecipeHandler;
import com.moffy5612.iinteg.misc.ModTier;
import com.moffy5612.iinteg.recipe.ModRecipeListBase.ModRecipe;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;

public class TileAdvancedProjector extends TileMachineBase{

    public static final String NAME = "advanced_projector";
    public static final int PROGRESS_MAX = 350;

    public int progress;
    public ModRecipe recipe;

    public TileAdvancedProjector(){
        this(null);
    }

    public TileAdvancedProjector(ModTier tier){
        super(NAME, tier, false, 5);
        this.progress = 0;
        this.recipe = null;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        if(compound.hasKey("inventory"))this.inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        if(compound.hasKey("progress"))this.progress = compound.getInteger("progress");
        if(compound.hasKey("energy"))this.energyStorage.deserializeNBT(compound.getCompoundTag("energy"));
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", this.inventory.serializeNBT());
        compound.setInteger("progress", this.progress);
        compound.setTag("energy", this.energyStorage.serializeNBT());
        return super.writeToNBT(compound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        NBTTagCompound compound = pkt.getNbtCompound();
        this.inventory.deserializeNBT(compound);
    }

    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(pos, this.getBlockMetadata(), this.inventory.serializeNBT());
    }

    @Override
    public boolean beforeInsertItem(int slot, ItemStack stack, boolean simulate) {
        if(slot == 0){
            CapabilityCrystalBall capabilityCrystalBall = stack.getCapability(CapabilityCrystalBall.CAPABILITY_CRYSTAL_BALL, null);
            if(capabilityCrystalBall != null)return capabilityCrystalBall.hasOwner;
        }
        return true;
    }

    @Override
    public boolean beforeExtractItem(int slot, int amount, boolean simulate) {
        return true;
    }

    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
        this.recipe = ModRecipeHandler.ADVANCED_PROJECTOR_RECIPE.getRecipe(
            this.inventory.getStackInSlot(0),
            this.inventory.getStackInSlot(1),
            this.inventory.getStackInSlot(2)
        );
        if(this.recipe != null)this.progress = 0;
    }

    @Override
    @SuppressWarnings("null")
    public void update() {
        if(!this.inventory.getStackInSlot(1).isEmpty() && this.energyStorage.getEnergyStored() > 0){
            if(this.energyStorage.canExtract() && this.recipe != null){
                int extracted = this.energyStorage.extractEnergy(this.energyStorage.transferRate, false);
                int increasedProgress = extracted / BASE_AMOUNT_ENERGY_TRANSFER;
                if(extracted > 0){
                    progress += increasedProgress;
                    if(progress > PROGRESS_MAX){
                        for(int i = 0; i < (int)(progress / PROGRESS_MAX); i++){
                            ItemStack result = recipe.result.copy();
                            decrStackInSlot(1);
                            decrStackInSlot(2);
                            incrStackInSlot(3, result);
                        }
                        progress = progress % PROGRESS_MAX;
                    }
                }
            }
        }
    }

    @SuppressWarnings("null")
    public void decrStackInSlot(int index){
        if(this.inventory.getStackInSlot(index).getCount() == 1) this.inventory.setStackInSlot(index, ItemStack.EMPTY);
        else this.inventory.getStackInSlot(index).setCount(this.inventory.getStackInSlot(index).getCount() - 1);
    }

    @SuppressWarnings("null")
    public void incrStackInSlot(int index, ItemStack stack){
        if(this.inventory.getStackInSlot(index).isEmpty())this.inventory.setStackInSlot(index, stack);
        else this.inventory.getStackInSlot(index).setCount(this.inventory.getStackInSlot(index).getCount() + 1);
    }
}
