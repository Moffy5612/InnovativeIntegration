package com.moffy5612.iinteg.block.inventory.slot;

import javax.annotation.Nonnull;

import com.moffy5612.iinteg.block.tileentity.TileAdvancedPartBuilder;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import slimeknights.tconstruct.library.smeltery.ICast;
import slimeknights.tconstruct.library.tools.IPattern;

public class SlotAdvancedPartBuilder extends SlotItemHandler{
    public TileAdvancedPartBuilder trpb;

    public SlotAdvancedPartBuilder(TileAdvancedPartBuilder inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn.inventory, index, xPosition, yPosition);
        this.trpb = inventoryIn;
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        if(this.slotNumber == 2)return false;
        else if(this.slotNumber == 0)return (stack.getItem() instanceof IPattern || stack.getItem() instanceof ICast);
        else return super.isItemValid(stack);
    }
}