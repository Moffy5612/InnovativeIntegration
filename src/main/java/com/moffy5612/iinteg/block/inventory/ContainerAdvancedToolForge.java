package com.moffy5612.iinteg.block.inventory;

import com.moffy5612.iinteg.block.inventory.slot.SlotAdvancedToolForge;
import com.moffy5612.iinteg.block.tileentity.TileAdvancedToolForge;
import com.moffy5612.iinteg.block.tileentity.TileMachineBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class ContainerAdvancedToolForge extends ModContainerBase{

    public TileAdvancedToolForge taf;
    public int storedEnergy;

    public ContainerAdvancedToolForge(InventoryPlayer playerInventory, TileAdvancedToolForge taf){
        this.taf = taf;
        this.storedEnergy = 0;

        this.addSlotToContainer(new SlotAdvancedToolForge(taf, 0, 44, 42));
        this.addSlotToContainer(new SlotAdvancedToolForge(taf, 1, 26, 63));
        this.addSlotToContainer(new SlotAdvancedToolForge(taf, 2, 22, 38));
        this.addSlotToContainer(new SlotAdvancedToolForge(taf, 3, 44, 20));
        this.addSlotToContainer(new SlotAdvancedToolForge(taf, 4, 66, 38));
        this.addSlotToContainer(new SlotAdvancedToolForge(taf, 5, 62, 63));
		this.addSlotToContainer(new SlotAdvancedToolForge(taf, 6, 124, 38));

        this.addSlotToContainer(new SlotAdvancedToolForge(taf, 7, 177, 6));

        for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(playerInventory, i * 9 + j + 9, 8 + j * 18, 92 + i * 18));
			}
		}

		for (int k = 0; k < 9; k++) {
			this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 150));
		}
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		IEnergyStorage energyStorage = this.taf.getCapability(CapabilityEnergy.ENERGY, null);

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
			IEnergyStorage energy = this.taf.getCapability(CapabilityEnergy.ENERGY, null);
			if(energy instanceof TileMachineBase.MachineEnergyStorage){
				TileMachineBase.MachineEnergyStorage machineEnergyStorage = (TileMachineBase.MachineEnergyStorage)energy;
				machineEnergyStorage.setEnegyStored(data);
			}
		}
	}
}
