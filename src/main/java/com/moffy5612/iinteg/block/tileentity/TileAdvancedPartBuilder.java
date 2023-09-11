package com.moffy5612.iinteg.block.tileentity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.moffy5612.iinteg.misc.ModTier;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.NonNullList;
import slimeknights.tconstruct.library.modifiers.TinkerGuiException;
import slimeknights.tconstruct.library.smeltery.ICast;
import slimeknights.tconstruct.library.tools.IPattern;
import slimeknights.tconstruct.library.utils.ToolBuilder;

public class TileAdvancedPartBuilder extends TileMachineBase{
    public boolean canExtractMaterials;
    public int progress;
    public boolean isRecipeValid;

    public static final int PROGRESS_MAX = 350;

    public static final String NAME = "advanced_part_builder";

    public TileAdvancedPartBuilder(){
        this(null);
    }


    public TileAdvancedPartBuilder(ModTier tier){
        super(NAME, tier, false, 4);
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
        compound.setTag("energy", energyStorage.serializeNBT());
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
        return !(slot == 0 && !((stack.getItem() instanceof IPattern) || (stack.getItem() instanceof ICast)));
    }

    @Override
    public boolean beforeExtractItem(int slot, int amount, boolean simulate) {
        return !(slot < 2 && !this.canExtractMaterials);
    }

    @Override
    public void afterExtractItem(int slot, int amount, boolean simulate) {
        if(this.inventory.getStackInSlot(1).getCount() < 1)this.progress = 0;
    }

    @Override
    public boolean isSlotValid(int slot, @Nonnull ItemStack stack) {
        if(slot == 0)return super.isSlotValid(slot, stack) && ((stack.getItem() instanceof IPattern) || (stack.getItem() instanceof ICast));
        return super.isSlotValid(slot, stack);
    }

    @Override
    public void update() {
        if(!this.inventory.getStackInSlot(0).isEmpty() && !this.inventory.getStackInSlot(1).isEmpty() && this.energyStorage.getEnergyStored() > 0){
            if(this.energyStorage.canExtract() && this.isRecipeValid){
                int extracted = this.energyStorage.extractEnergy(this.energyStorage.transferRate, false);
                int increasedProgress = extracted / BASE_AMOUNT_ENERGY_TRANSFER;
                if(extracted > 0){
                    progress += increasedProgress;
                    if(progress > PROGRESS_MAX){
                        for(int i = 0; i < (int)(progress / PROGRESS_MAX); i++)makePart();
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

    public void makePart(){
        ItemStack patternStack = this.inventory.getStackInSlot(0);
        ItemStack materialStack = this.inventory.getStackInSlot(1);
        ItemStack resultStack = null;
        if(!patternStack.isEmpty() && !materialStack.isEmpty()){
            try{
                NonNullList<ItemStack>materials = NonNullList.withSize(1, ItemStack.EMPTY);
                materials.set(0, materialStack);
                NonNullList<ItemStack> result = ToolBuilder.tryBuildToolPart(patternStack, materials, false);
                if(result != null){
                    resultStack = result.get(0);
                }
            }catch(TinkerGuiException e){

            }
            if(resultStack != null){
                if(this.inventory.getStackInSlot(2).isEmpty()){
                    try{
                        NonNullList<ItemStack>materials = NonNullList.withSize(1, ItemStack.EMPTY);
                        materials.set(0, materialStack);
                        NonNullList<ItemStack> result = ToolBuilder.tryBuildToolPart(patternStack, materials, true);
                        if(result != null){
                            resultStack = result.get(0);
                            this.inventory.setStackInSlot(2, resultStack);
                        }
                    }catch(TinkerGuiException e){
        
                    }
                }else if(ItemStack.areItemsEqual(this.inventory.getStackInSlot(2), resultStack)){
                    try{
                        NonNullList<ItemStack>materials = NonNullList.withSize(1, ItemStack.EMPTY);
                        materials.set(0, materialStack);
                        NonNullList<ItemStack> result = ToolBuilder.tryBuildToolPart(patternStack, materials, true);
                        if(result != null){
                            this.inventory.getStackInSlot(2).grow(1);
                        }
                    }catch(TinkerGuiException e){
        
                    }
                }
            }
        }
    }

    public boolean searchRecipe(){
        ItemStack patternStack = this.inventory.getStackInSlot(0);
        ItemStack materialStack = this.inventory.getStackInSlot(1);
        ItemStack resultStack = null;
        if(!patternStack.isEmpty() && !materialStack.isEmpty()){
            try{
                NonNullList<ItemStack>materials = NonNullList.withSize(1, ItemStack.EMPTY);
                materials.set(0, materialStack);
                NonNullList<ItemStack> result = ToolBuilder.tryBuildToolPart(patternStack, materials, false);
                if(result != null){
                    resultStack = result.get(0);
                    if(!resultStack.isEmpty())return true;
                }
            }catch(TinkerGuiException e){
                return false;
            }
        }
        return false;
    }
}
