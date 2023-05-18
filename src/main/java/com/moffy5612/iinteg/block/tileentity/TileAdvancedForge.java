package com.moffy5612.iinteg.block.tileentity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.moffy5612.iinteg.integration.tconstruct.methods.ConarmMethods;
import com.moffy5612.iinteg.integration.tconstruct.methods.SlashbladeTicMethods;
import com.moffy5612.iinteg.integration.tconstruct.methods.TConstructMethods;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileAdvancedForge extends ModTileEntity implements ITickable{
    public TRFItemStackHandler inventory;
    public boolean canExtractMaterials;

    public static final String NAME = "advanced_Forge";

    public TileAdvancedForge(){
        super(NAME);
        this.inventory = new TRFItemStackHandler(this, 7);
        this.canExtractMaterials = true;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        
        super.readFromNBT(compound);

        NBTTagList items = compound.getTagList("items", NBT.TAG_COMPOUND);
        for(int i = 0; i < items.tagCount(); i++){
            this.inventory.setStackInSlot(i, new ItemStack(items.getCompoundTagAt(i)));
        }

        this.canExtractMaterials = compound.getBoolean("canExtractMaterials");

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        

        NBTTagList items = new NBTTagList();
        for(int i = 0; i < this.inventory.getSlots(); i++){
            NBTTagCompound item = new NBTTagCompound();
            ItemStack stack = this.inventory.getStackInSlot(i);
            stack.writeToNBT(item);
            items.appendTag(item);
        }

        compound.setTag("items", items);
        compound.setBoolean("canExtractMaterials", this.canExtractMaterials);

        return super.writeToNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }

    @Override
    @Nullable
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        
        if(hasCapability(capability, facing))return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this.inventory);
        return super.getCapability(capability, facing);
    }

    @Override
    @Nullable
    public ITextComponent getDisplayName() {
        
        return this.getDisplayName();
    }

    @Override
    public void update() {
        
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
        public TileAdvancedForge trf;

        public TRFItemStackHandler(TileAdvancedForge trf, int size){
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
}
