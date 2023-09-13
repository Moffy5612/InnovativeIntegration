package com.moffy5612.iinteg.block.inventory;

import com.moffy5612.iinteg.block.inventory.slot.SlotAdvancedPartBuilder;
import com.moffy5612.iinteg.block.tileentity.TileAdvancedPartBuilder;
import com.moffy5612.iinteg.block.tileentity.TileMachineBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class ContainerAdvancedPartBuilder extends ModContainerBase{
	public TileAdvancedPartBuilder trpb;
	public int storedEnergy;

    public ContainerAdvancedPartBuilder(InventoryPlayer inventory, TileAdvancedPartBuilder trpb){

		this.trpb = trpb;
		this.storedEnergy = 0;

		this.addSlotToContainer(new SlotAdvancedPartBuilder(trpb, 0, 39, 38));
        this.addSlotToContainer(new SlotAdvancedPartBuilder(trpb, 1, 61, 38));

        this.addSlotToContainer(new SlotAdvancedPartBuilder(trpb, 2, 119, 38));
		
		this.addSlotToContainer(new SlotAdvancedPartBuilder(trpb, 3, 177, 6));

		this.addPlayerInventory(inventory, 8, 84);
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return !playerIn.isSpectator();
    }

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		IEnergyStorage energyStorage = this.trpb.getCapability(CapabilityEnergy.ENERGY, null);

		if(energyStorage!=null){
			int newStoredEnergy = energyStorage.getEnergyStored();
			if(storedEnergy != newStoredEnergy){
				for(IContainerListener listener : listeners){
					listener.sendWindowProperty(this, 0, newStoredEnergy);
				}
				storedEnergy = newStoredEnergy;
			}
		}
	}

	@Override
	@SuppressWarnings("null")
	public void updateProgressBar(int id, int data) {
		super.updateProgressBar(id, data);

		if(id == 0){
			IEnergyStorage energy = this.trpb.getCapability(CapabilityEnergy.ENERGY, null);
			if(energy instanceof TileMachineBase.MachineEnergyStorage){
				TileMachineBase.MachineEnergyStorage machineEnergyStorage = (TileMachineBase.MachineEnergyStorage)energy;
				machineEnergyStorage.setEnegyStored(data);
			}
		}
	}
}
