package com.moffy5612.iinteg.block.inventory;

import com.moffy5612.iinteg.block.inventory.slot.SlotAdvancedPartBuilder;
import com.moffy5612.iinteg.block.tileentity.TileAdvancedPartBuilder;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class ContainerAdvancedPartBuilder extends ModContainerBase{
	public TileAdvancedPartBuilder trpb;
    public ContainerAdvancedPartBuilder(InventoryPlayer inventory, TileAdvancedPartBuilder trpb){

		this.trpb = trpb;

		this.addSlotToContainer(new SlotAdvancedPartBuilder(trpb, 0, 39, 38));
        this.addSlotToContainer(new SlotAdvancedPartBuilder(trpb, 1, 61, 38));

        this.addSlotToContainer(new SlotAdvancedPartBuilder(trpb, 2, 119, 38));
		
        for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventory, i * 9 + j + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; k++) {
			this.addSlotToContainer(new Slot(inventory, k, 8 + k * 18, 142));
		}
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return !playerIn.isSpectator();
    }

    
}
