package com.moffy5612.iinteg.block.inventory;

import com.moffy5612.iinteg.block.inventory.slot.SlotAdvancedProjector;
import com.moffy5612.iinteg.block.tileentity.TileAdvancedProjector;
import com.moffy5612.iinteg.block.tileentity.TileMachineBase;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class ContainerAdvancedProjector extends ModContainerBase{
    
    public TileAdvancedProjector tap;
    public int storedEnergy;

    public ContainerAdvancedProjector(InventoryPlayer inventoryPlayer, TileAdvancedProjector tap){
        this.tap = tap;
		this.storedEnergy = 0;

        this.addSlotToContainer(new SlotAdvancedProjector(tap, 0, 83, 14));
        this.addSlotToContainer(new SlotAdvancedProjector(tap, 1, 51, 38));
        this.addSlotToContainer(new SlotAdvancedProjector(tap, 2, 83, 61));
        this.addSlotToContainer(new SlotAdvancedProjector(tap, 3, 125, 38));
        this.addSlotToContainer(new SlotAdvancedProjector(tap, 4, 177, 6));

        this.addPlayerInventory(inventoryPlayer, 8, 84);
    }

    @Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		IEnergyStorage energyStorage = this.tap.getCapability(CapabilityEnergy.ENERGY, null);

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
			IEnergyStorage energy = this.tap.getCapability(CapabilityEnergy.ENERGY, null);
			if(energy instanceof TileMachineBase.MachineEnergyStorage){
				TileMachineBase.MachineEnergyStorage machineEnergyStorage = (TileMachineBase.MachineEnergyStorage)energy;
				machineEnergyStorage.setEnegyStored(data);
			}
		}
	}
}
