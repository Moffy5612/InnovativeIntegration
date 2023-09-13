package com.moffy5612.iinteg.recipe;

import net.minecraft.item.ItemStack;

public class AdvancedProjectorRecipe extends SpiritualProjectorRecipe{
    public AdvancedProjectorRecipe(){
        super();

        for(ModRecipe recipe : this.recipes.values())recipe.material.add(ItemStack.EMPTY);

        
    }
}
