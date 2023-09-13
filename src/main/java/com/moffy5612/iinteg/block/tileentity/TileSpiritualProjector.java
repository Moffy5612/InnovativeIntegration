package com.moffy5612.iinteg.block.tileentity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.moffy5612.iinteg.capability.item.CapabilityCrystalBall;
import com.moffy5612.iinteg.handler.ModRecipeHandler;
import com.moffy5612.iinteg.recipe.ModRecipeListBase.ModRecipe;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
public class TileSpiritualProjector extends ModTileEntityBase implements ITickable{
    
    public static final int PROGRESS_MAX = 256;
    public static final String NAME = "spiritual_projector";
    
    public SpiritualProjectorItemStackHandler inventory = new SpiritualProjectorItemStackHandler(3);
    public boolean hasSkyLight;
    @Nullable public ModRecipe recipe;
    public int progress;

    public TileSpiritualProjector(){
        super(NAME);
        hasSkyLight = false;
        progress = 0;
        recipe = null;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }

    @Override
    @Nullable
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(this.hasCapability(capability, facing)){
            inventory.isAutomatic = facing != null;
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        if(compound.hasKey("inventory"))this.inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        if(compound.hasKey("progress"))this.progress = compound.getInteger("progress");
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", this.inventory.serializeNBT());
        compound.setInteger("progress", this.progress);
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
    @SuppressWarnings("null")
    public void update() {
        this.setSkyLight();
        if(this.hasSkyLight){
            if(!this.inventory.getStackInSlot(0).isEmpty() && !this.inventory.getStackInSlot(1).isEmpty()){
                if(this.recipe != null){
                    if(this.inventory.getStackInSlot(2).isEmpty() || ItemStack.areItemsEqual(this.inventory.getStackInSlot(2), recipe.result)){
                        progress++;
                        if(progress >= PROGRESS_MAX){
                            progress = 0;
                            ItemStack resultStack = recipe.result.copy();
                            if(resultStack != null){
                                decrStackInSlot(1);
                                incrStackInSlot(2, resultStack);
                            }
                        }
                    }
                }
            }
        }
    }

    public void setSkyLight(){
        if(this.getWorld().provider.hasSkyLight()){
            int light = this.world.getLightFor(EnumSkyBlock.SKY, this.pos.up()) - this.world.getSkylightSubtracted();
            this.hasSkyLight = light > 0;
        }else{
            this.hasSkyLight = false;
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

    public class SpiritualProjectorItemStackHandler extends ItemStackHandler{
        public boolean isAutomatic;

        public SpiritualProjectorItemStackHandler(int size){
            super(size);
            this.isAutomatic = false;
        }

        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            markDirty();
            world.markAndNotifyBlock(pos,null, world.getBlockState(pos),world.getBlockState(pos),2);

            recipe = ModRecipeHandler.SPIRITUAL_PROJECTOR_RECIPE.getRecipe(
                inventory.getStackInSlot(0),
                inventory.getStackInSlot(1)
            );
            if(recipe == null)progress = 0;
        }

        @Override
        @Nonnull
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            ItemStack empty = ItemStack.EMPTY;
            if(empty != null && this.isAutomatic && slot != 2)return empty;
            return super.extractItem(slot, amount, simulate);
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
            }else if(slot == 1){
                return super.insertItem(slot, stack, simulate);
            }else if(slot == 2){
                if(empty != null)return empty;
            }
            return super.insertItem(slot, stack, simulate);
        }
    }
    
}
