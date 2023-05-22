package com.moffy5612.iinteg.block.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ModContainerBase extends Container{

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return !playerIn.isSpectator();
    }
    
    @Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index){
		ItemStack remainder = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack stackSlot = slot.getStack();
			remainder = stackSlot.copy();
			int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();
			if (index < containerSlots) {
				if (!this.mergeItemStack(stackSlot, containerSlots, inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(stackSlot, 0, containerSlots, false)) {
				return ItemStack.EMPTY;
			}

			if(stackSlot.getCount() == 0){
				slot.putStack(ItemStack.EMPTY);
			}else{
				slot.onSlotChanged();
			}

			if(stackSlot.getCount() == remainder.getCount()){
				return ItemStack.EMPTY;
			}

			slot.onTake(player, stackSlot);
		}
		return remainder;
	}
}
