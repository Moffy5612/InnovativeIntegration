package com.moffy5612.iinteg.block.inventory;

import com.moffy5612.iinteg.block.inventory.slot.SlotSpiritualGenerator;
import com.moffy5612.iinteg.block.tileentity.TileSpiritualGenerator;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class ContainerSpiritualGenerator extends ModContainerBase{

    public TileSpiritualGenerator tsg;

    public ContainerSpiritualGenerator(InventoryPlayer inventoryPlayer, TileSpiritualGenerator tsg){
        this.tsg = tsg;

        this.addSlotToContainer(new SlotSpiritualGenerator(tsg, 0, 81, 43));

        for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventoryPlayer, i * 9 + j + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; k++) {
			this.addSlotToContainer(new Slot(inventoryPlayer, k, 8 + k * 18, 142));
		}
    }
}
