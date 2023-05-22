package com.moffy5612.iinteg.integration.jei;

import java.util.Collections;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class ModRecipeWrapper implements IRecipeWrapper{
    
    public List<List<ItemStack>> inputList;
    public ItemStack output;

    public ModRecipeWrapper(List<ItemStack> inputList, ItemStack output){
        this.inputList = Collections.singletonList(inputList);
        this.output = output;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputLists(VanillaTypes.ITEM, this.inputList);
        ingredients.setOutput(VanillaTypes.ITEM, this.output);
    }
    
}
