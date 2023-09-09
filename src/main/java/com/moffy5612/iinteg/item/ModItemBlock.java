package com.moffy5612.iinteg.item;

import com.moffy5612.iinteg.block.ModBlockBase;
import com.moffy5612.iinteg.misc.ModTier;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ModItemBlock extends ItemBlock{
    public ModItemBlock(ModBlockBase block){
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(block.hasSubBlocks());
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if(this.getHasSubtypes()) return super.getUnlocalizedName(stack) + "." +ModTier.getTierFromIndex(stack.getMetadata()).getName();
        return super.getUnlocalizedName(stack);
    }
}
