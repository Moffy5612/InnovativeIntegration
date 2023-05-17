package com.moffy5612.iinteg.recipe;

import com.moffy5612.iinteg.handler.ItemHandler;
import com.moffy5612.iinteg.item.Material;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class SpiritualProjectorRecipe extends ModRecipeListBase{
    public SpiritualProjectorRecipe(){

        this.addRecipe(
            Material.TYPES[0],
            new ItemStack(ItemHandler.MATERIAL, 1, 0), 
            new ItemStack(ItemHandler.CRYSTAL_BALL, 1, 0),
            new ItemStack(Items.IRON_INGOT, 1, 0)
        );

    }
}
