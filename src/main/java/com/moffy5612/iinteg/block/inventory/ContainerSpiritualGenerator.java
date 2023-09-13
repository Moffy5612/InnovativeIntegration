package com.moffy5612.iinteg.block.inventory;

import com.moffy5612.iinteg.block.inventory.slot.SlotSpiritualGenerator;
import com.moffy5612.iinteg.block.tileentity.TileSpiritualGenerator;

import net.minecraft.entity.player.InventoryPlayer;

public class ContainerSpiritualGenerator extends ModContainerBase{

    public TileSpiritualGenerator tsg;

    public ContainerSpiritualGenerator(InventoryPlayer inventoryPlayer, TileSpiritualGenerator tsg){
        this.tsg = tsg;

        this.addSlotToContainer(new SlotSpiritualGenerator(tsg, 0, 81, 43));

        this.addPlayerInventory(inventoryPlayer, 8, 84);
    }
}
