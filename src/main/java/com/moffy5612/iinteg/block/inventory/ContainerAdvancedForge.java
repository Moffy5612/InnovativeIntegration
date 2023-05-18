package com.moffy5612.iinteg.block.inventory;

import com.moffy5612.iinteg.block.inventory.slot.SlotAdvancedForge;
import com.moffy5612.iinteg.block.tileentity.TileAdvancedForge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class ContainerAdvancedForge extends ModContainerBase{

    public TileAdvancedForge taf;

    public ContainerAdvancedForge(InventoryPlayer playerInventory, TileAdvancedForge taf){
        this.taf = taf;

        this.addSlotToContainer(new SlotAdvancedForge(taf, 0, 33, 42));
        this.addSlotToContainer(new SlotAdvancedForge(taf, 1, 15, 63));
        this.addSlotToContainer(new SlotAdvancedForge(taf, 2, 11, 38));
        this.addSlotToContainer(new SlotAdvancedForge(taf, 3, 33, 20));
        this.addSlotToContainer(new SlotAdvancedForge(taf, 4, 55, 38));
        this.addSlotToContainer(new SlotAdvancedForge(taf, 5, 51, 63));
		this.addSlotToContainer(new SlotAdvancedForge(taf, 6, 124, 38));

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
}
