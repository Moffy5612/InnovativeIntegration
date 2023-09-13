package com.moffy5612.iinteg.block.inventory;

import com.moffy5612.iinteg.block.inventory.slot.SlotSpiritualProjector;
import com.moffy5612.iinteg.block.tileentity.TileSpiritualProjector;

import net.minecraft.entity.player.InventoryPlayer;

public class ContainerSpiritualProjector extends ModContainerBase{

    public TileSpiritualProjector tsp;

    public ContainerSpiritualProjector(InventoryPlayer inventoryPlayer, TileSpiritualProjector tsp){
        this.tsp = tsp;

        this.addSlotToContainer(new SlotSpiritualProjector(tsp, 0, 60, 26));
        this.addSlotToContainer(new SlotSpiritualProjector(tsp, 1, 60, 49));
        this.addSlotToContainer(new SlotSpiritualProjector(tsp, 2, 116, 38));

        this.addPlayerInventory(inventoryPlayer, 8, 84);
    }
}
