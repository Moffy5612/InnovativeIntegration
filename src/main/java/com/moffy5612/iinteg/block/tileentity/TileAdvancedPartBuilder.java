package com.moffy5612.iinteg.block.tileentity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.moffy5612.iinteg.misc.ModTier;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import slimeknights.tconstruct.library.modifiers.TinkerGuiException;
import slimeknights.tconstruct.library.smeltery.ICast;
import slimeknights.tconstruct.library.tools.IPattern;
import slimeknights.tconstruct.library.utils.ToolBuilder;

public class TileAdvancedPartBuilder extends TileMachineBase implements ITickable{
    public TRPBItemStackHandler inventory;
    public boolean canExtractMaterials;
    public int progress;
    public int transferredEnergy;

    public static final int PROGRESS_MAX = 200;

    public static final String NAME = "advanced_part_builder";

    public TileAdvancedPartBuilder(){
        super(NAME, null, false);
    }


    public TileAdvancedPartBuilder(ModTier tier){
        super(NAME, tier, false);
        this.inventory = new TRPBItemStackHandler(this, 4);
        this.canExtractMaterials = true;
        this.progress = 0;
        this.transferredEnergy = getEnergyTransfer();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        this.inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        this.progress = compound.getInteger("progress");
        this.canExtractMaterials = compound.getBoolean("canExtractMaterials");

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory",this.inventory.serializeNBT());
        compound.setInteger("progress", this.progress);
        compound.setBoolean("canExtractMaterials", this.canExtractMaterials);

        return super.writeToNBT(compound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        NBTTagCompound compound = pkt.getNbtCompound();
        this.inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        this.progress = compound.getInteger("progress");
    }

    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(pos, this.getBlockMetadata(), this.serializeNBT());
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

    public int getEnergyTransfer() {
        return super.getTransfer(this.inventory.getStackInSlot(3).getCount());
    }

    @Override
    public void update() {
        if(!this.inventory.getStackInSlot(0).isEmpty() && !this.inventory.getStackInSlot(1).isEmpty()){
            if(this.energyStorage.canExtract()){
                int extracted = this.energyStorage.extractEnergy(transferredEnergy, false);
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

    public class TRPBItemStackHandler extends ItemStackHandler{
        public TileAdvancedPartBuilder trpb;

        public TRPBItemStackHandler(TileAdvancedPartBuilder trpb, int size){
            super(size);
            this.trpb = trpb;
        }

        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            trpb.markDirty();
            world.markAndNotifyBlock(pos,null, world.getBlockState(pos),world.getBlockState(pos),2);
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
            ItemStack result = super.extractItem(slot, amount, simulate);
            if(this.trpb.inventory.getStackInSlot(1).getCount() < 1)this.trpb.progress = 0;
            return result;
        }
    }
}
