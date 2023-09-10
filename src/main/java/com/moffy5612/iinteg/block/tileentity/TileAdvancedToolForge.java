package com.moffy5612.iinteg.block.tileentity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.moffy5612.iinteg.integration.tconstruct.methods.ConarmMethods;
import com.moffy5612.iinteg.integration.tconstruct.methods.SlashbladeTicMethods;
import com.moffy5612.iinteg.integration.tconstruct.methods.TConstructMethods;
import com.moffy5612.iinteg.misc.ModTier;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.items.ItemStackHandler;

public class TileAdvancedToolForge extends TileMachineBase{
    public boolean canExtractMaterials;
    public int progress;
    public boolean isRecipeValid;

    public static final int PROGRESS_MAX = 200;

    public static final String NAME = "advanced_Forge";

    public TileAdvancedToolForge(){
        this(null);
    }

    public TileAdvancedToolForge(ModTier tier){
        super(NAME, tier, false, 8);
        this.canExtractMaterials = true;
        this.progress = 0;
        this.isRecipeValid = false;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        if(compound.hasKey("inventory"))this.inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        if(compound.hasKey("progress"))this.progress = compound.getInteger("progress");
        if(compound.hasKey("canExtractMaterials"))this.canExtractMaterials = compound.getBoolean("canExtractMaterials");
        if(compound.hasKey("energy"))this.energyStorage.deserializeNBT(compound.getCompoundTag("energy"));
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory",this.inventory.serializeNBT());
        compound.setInteger("progress", this.progress);
        compound.setBoolean("canExtractMaterials", this.canExtractMaterials);
        compound.setTag("energy", this.energyStorage.serializeNBT());
        NBTTagCompound nbt = super.writeToNBT(compound);
        return nbt;
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
    public void update() {
        if(this.inventory.getStackInSlot(6).isEmpty() && this.energyStorage.getEnergyStored() > 0){
            if(this.energyStorage.canExtract() && this.isRecipeValid){
                int extracted = this.energyStorage.extractEnergy(transferredEnergy, false);
                int increasedProgress = extracted / BASE_AMOUNT_ENERGY_TRANSFER;
                if(extracted > 0){
                    progress += increasedProgress;
                    if(progress > PROGRESS_MAX){
                        for(int i = 0; i < (int)(progress / PROGRESS_MAX); i++)updateSlot();
                        progress = progress % PROGRESS_MAX;
                    }
                }
            }
        }
    }

    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
        this.isRecipeValid = searchRecipe();
        if(!this.isRecipeValid)this.progress=0;
    }

    public void updateSlot(){
        boolean flag = false;
        if(!flag)flag = TConstructMethods.tryRepairTool(this);
        if(!flag)flag = TConstructMethods.tryModifyTool(this);
        if(!flag)flag = TConstructMethods.tryBuildTool(this);
        if(Loader.isModLoaded("conarm")){
            if(!flag)flag = ConarmMethods.tryRepairArmor(this);
            if(!flag)flag = ConarmMethods.tryModifyArmor(this);
            if(!flag)flag = ConarmMethods.tryBuildArmor(this);
        }
        if(Loader.isModLoaded("slashbladetic")){
            if(!flag)flag = SlashbladeTicMethods.tryRepairBlade(this);
            if(!flag)flag = SlashbladeTicMethods.tryModifyBlade(this);
            if(!flag)flag = SlashbladeTicMethods.tryBuildBlade(this);
        }
    }

    public class TRFItemStackHandler extends ItemStackHandler{
        public TileAdvancedToolForge trf;

        public TRFItemStackHandler(TileAdvancedToolForge trf, int size){
            super(size);
            this.trf = trf;
        }

        @Override
        @Nonnull
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            
            ItemStack empty = ItemStack.EMPTY;
            if(empty != null){
                if(slot < 6 && !trf.canExtractMaterials)return empty;
            }
            return super.extractItem(slot, amount, simulate);
        }
    }

    public boolean searchRecipe(){
        boolean b = TConstructMethods.hasRecipe(this);
        if(Loader.isModLoaded("conarm")) b = b || ConarmMethods.hasRecipe(this);
        if(Loader.isModLoaded("slashbladetic")) b = b || SlashbladeTicMethods.hasRecipe(this);
        return b;
    }
}
