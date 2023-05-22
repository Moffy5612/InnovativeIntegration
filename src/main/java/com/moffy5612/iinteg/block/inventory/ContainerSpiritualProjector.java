package com.moffy5612.iinteg.block.inventory;

import com.moffy5612.iinteg.block.inventory.slot.SlotSpiritualProjector;
import com.moffy5612.iinteg.block.tileentity.TileSpiritualProjector;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class ContainerSpiritualProjector extends ModContainerBase{

    public TileSpiritualProjector tsp;

    public ContainerSpiritualProjector(InventoryPlayer inventoryPlayer, TileSpiritualProjector tsp){
        this.tsp = tsp;

        this.addSlotToContainer(new SlotSpiritualProjector(tsp, 0, 60, 26));
        this.addSlotToContainer(new SlotSpiritualProjector(tsp, 1, 60, 49));
        this.addSlotToContainer(new SlotSpiritualProjector(tsp, 2, 116, 38));

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
