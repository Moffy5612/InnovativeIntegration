package com.moffy5612.iinteg.block.inventory.slot;

import javax.annotation.Nonnull;

import com.moffy5612.iinteg.block.tileentity.TileAdvancedToolForge;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class SlotAdvancedToolForge extends SlotItemHandler{
    public SlotAdvancedToolForge(TileAdvancedToolForge trf, int index, int xPosition, int yPosition) {
        super(trf.inventory, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        if(this.slotNumber == 6)return false;
        return super.isItemValid(stack);
    }
}
