package com.moffy5612.iinteg.block.tileentity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.moffy5612.iinteg.capability.item.CapabilityCrystalBall;
import com.moffy5612.iinteg.recipe.ModRecipeHandler;
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
public class TileSpiritualProjector extends ModTileEntity implements ITickable{
    
    public static final int PROGRESS_MAX = 256;
    public static final String NAME = "spiritual_projector";
    
    public TileSpiritualProjector instance;
    public SpiritualProjectorItemStackHandler inventory = new SpiritualProjectorItemStackHandler(3);
    public boolean hasSkyLight;
    public int progress;

    public TileSpiritualProjector(){
        super(NAME);
        hasSkyLight = false;
        progress = 0;
        instance = this;
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
        this.inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        this.progress = compound.getInteger("progress");
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
    public void update() {
        boolean hasRecipe = false;

        this.setSkyLight();
        if(this.hasSkyLight){
            if(!this.inventory.getStackInSlot(0).isEmpty() && !this.inventory.getStackInSlot(1).isEmpty()){
                List<ItemStack> materials = new ArrayList<>();
                materials.add(this.inventory.getStackInSlot(0));
                materials.add(this.inventory.getStackInSlot(1));
                for(ModRecipe recipe : ModRecipeHandler.SPIRITUAL_PROJECTOR_RECIPE.recipes.values()){
                    if(recipe.isMaterialsEquals(materials)){
                        if(this.inventory.getStackInSlot(2).isEmpty() || ItemStack.areItemsEqual(this.inventory.getStackInSlot(2), recipe.result)){
                            hasRecipe = true;
                            progress++;
                            if(progress >= PROGRESS_MAX){
                                progress = 0;
                                ItemStack empty = ItemStack.EMPTY;
                                ItemStack resultStack = recipe.result.copy();
                                if(empty != null && resultStack != null){
                                    if(this.inventory.getStackInSlot(1).getCount() == 1) this.inventory.setStackInSlot(1, empty);
                                    else this.inventory.getStackInSlot(1).setCount(this.inventory.getStackInSlot(1).getCount() - 1);
                                
                                    if(this.inventory.getStackInSlot(2).isEmpty())this.inventory.setStackInSlot(2, resultStack);
                                    else this.inventory.getStackInSlot(2).setCount(this.inventory.getStackInSlot(2).getCount() + 1);
                                }
                            }
                        }
                        
                    }
                }
            }
        }

        if(!hasRecipe){
            progress = 0;
        }
    }

    public void setSkyLight(){
        if(this.getWorld().provider.hasSkyLight()){
            int light = this.world.getLightFor(EnumSkyBlock.SKY, this.pos.up()) - this.world.getSkylightSubtracted();
            this.hasSkyLight = light > 0;
        }
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
            instance.markDirty();
            world.markAndNotifyBlock(pos,null, world.getBlockState(pos),world.getBlockState(pos),2);
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
