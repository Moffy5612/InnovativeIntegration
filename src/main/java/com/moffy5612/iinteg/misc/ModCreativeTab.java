package com.moffy5612.iinteg.misc;

import com.moffy5612.iinteg.Reference;
import com.moffy5612.iinteg.handler.ItemHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModCreativeTab extends CreativeTabs{

    public ModCreativeTab(){
        super(Reference.MOD_ID);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemHandler.CRYSTAL_BALL);
    }
}
