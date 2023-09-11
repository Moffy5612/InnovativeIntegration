package com.moffy5612.iinteg.block.inventory.slot;

import javax.annotation.Nonnull;

import com.moffy5612.iinteg.block.tileentity.TileSpiritualGenerator;
import com.moffy5612.iinteg.capability.item.CapabilityCrystalBall;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class SlotSpiritualGenerator extends SlotItemHandler{
    public TileSpiritualGenerator tsg;

    public SlotSpiritualGenerator(TileSpiritualGenerator tsg, int index, int x, int y){
        super(tsg.inventory, index, x, y);
        this.tsg = tsg;
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        if(this.slotNumber == 0){
            CapabilityCrystalBall capabilityCrystalBall = stack.getCapability(CapabilityCrystalBall.CAPABILITY_CRYSTAL_BALL, null);
            if(capabilityCrystalBall != null)return capabilityCrystalBall.hasOwner ? super.isItemValid(stack) : false;
            return false;
        }
        return super.isItemValid(stack);
    }
}
