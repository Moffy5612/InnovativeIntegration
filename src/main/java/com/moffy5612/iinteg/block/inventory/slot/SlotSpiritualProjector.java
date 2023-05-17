package com.moffy5612.iinteg.block.inventory.slot;

import javax.annotation.Nonnull;

import com.moffy5612.iinteg.block.tileentity.TileSpiritualProjector;
import com.moffy5612.iinteg.capability.item.CapabilityCrystalBall;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.oredict.OreDictionary;

public class SlotSpiritualProjector extends SlotItemHandler{
    public TileSpiritualProjector tsp;

    public SlotSpiritualProjector(TileSpiritualProjector tsp, int index, int x, int y){
        super(tsp.inventory, index, x, y);
        this.tsp = tsp;
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        if(this.slotNumber == 0){
            CapabilityCrystalBall capabilityCrystalBall = stack.getCapability(CapabilityCrystalBall.CAPABILITY_CRYSTAL_BALL, null);
            if(capabilityCrystalBall != null)return capabilityCrystalBall.hasOwner ? super.isItemValid(stack) : false;
            return false;
        }else if(this.slotNumber == 1){
            int[] oreDictIds = OreDictionary.getOreIDs(stack);
            for(int oreDictId : oreDictIds){
                if(oreDictId == OreDictionary.getOreID("ingotIron"))return super.isItemValid(stack);
            }
            return false;
        }else if(this.slotNumber == 2)return false;
        return super.isItemValid(stack);
    }
}
