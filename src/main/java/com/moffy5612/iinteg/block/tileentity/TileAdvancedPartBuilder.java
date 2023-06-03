package com.moffy5612.iinteg.block.tileentity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import slimeknights.tconstruct.library.modifiers.TinkerGuiException;
import slimeknights.tconstruct.library.smeltery.ICast;
import slimeknights.tconstruct.library.tools.IPattern;
import slimeknights.tconstruct.library.utils.ToolBuilder;

public class TileAdvancedPartBuilder extends ModTileEntityBase implements ITickable{
    public TRPBItemStackHandler inventory;
    public boolean canExtractMaterials;

    public static final String NAME = "advanced_part_builder";

    public TileAdvancedPartBuilder(){
        super(NAME);
        this.inventory = new TRPBItemStackHandler(this, 3);
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
    public void update() {
        
    }

    public void updateSlot(){
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

    public class TRPBItemStackHandler extends ItemStackHandler{
        public TileAdvancedPartBuilder trpb;

        public TRPBItemStackHandler(TileAdvancedPartBuilder trpb, int size){
            super(size);
            this.trpb = trpb;
        }

        @Override
        @Nonnull
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            ItemStack empty = ItemStack.EMPTY;
            if(empty != null){
                if(slot == 0 && !(stack.getItem() instanceof IPattern))return empty;
                else if(slot == 0 && !(stack.getItem() instanceof ICast))return empty;
            }
            return super.insertItem(slot, stack, simulate);
        }

        @Override
        @Nonnull
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            ItemStack empty = ItemStack.EMPTY;
            if(empty != null){
                if(slot < 2 && !trpb.canExtractMaterials)return empty;
            }
            return super.extractItem(slot, amount, simulate);
        }
    }
}
