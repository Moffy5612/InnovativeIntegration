package com.moffy5612.iinteg.block.inventory.slot;

import javax.annotation.Nonnull;

import com.moffy5612.iinteg.block.tileentity.TileAdvancedProjector;
import com.moffy5612.iinteg.capability.item.CapabilityCrystalBall;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class SlotAdvancedProjector extends SlotItemHandler{
    TileAdvancedProjector tap;

    public SlotAdvancedProjector(TileAdvancedProjector tap, int index, int x, int y){
        super(tap.inventory, index, x, y);
        this.tap = tap;
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        if(this.slotNumber == 3)return false;
        else if(this.slotNumber == 0){
            CapabilityCrystalBall capabilityCrystalBall = stack.getCapability(CapabilityCrystalBall.CAPABILITY_CRYSTAL_BALL, null);
            if(capabilityCrystalBall != null)return capabilityCrystalBall.hasOwner;
        }
        return super.isItemValid(stack);
    }
}
