package com.moffy5612.iinteg.integration.jei;

import java.util.ArrayList;
import java.util.List;

import com.moffy5612.iinteg.client.gui.GuiSpiritualProjector;
import com.moffy5612.iinteg.handler.BlockHandler;
import com.moffy5612.iinteg.integration.jei.spiritualprojector.SpiritualProjectorCategory;
import com.moffy5612.iinteg.integration.jei.spiritualprojector.SpiritualProjectorRecipeWrapper;
import com.moffy5612.iinteg.recipe.ModRecipeHandler;
import com.moffy5612.iinteg.recipe.ModRecipeListBase.ModRecipe;

import mezz.jei.Internal;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.gui.GuiHelper;
import mezz.jei.runtime.JeiHelpers;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEIIntegration implements IModPlugin{

    @Override
    public void register(IModRegistry registry) {
        List<SpiritualProjectorRecipeWrapper> SpiritualProjectorWrapperList = new ArrayList<>();
        for(ModRecipe recipe : ModRecipeHandler.SPIRITUAL_PROJECTOR_RECIPE.recipes.values()){
            SpiritualProjectorWrapperList.add(new SpiritualProjectorRecipeWrapper(recipe.material, recipe.result));
        }
        registry.addRecipes(SpiritualProjectorWrapperList, SpiritualProjectorCategory.NAME);
        registry.addRecipeCatalyst(new ItemStack(BlockHandler.SPIRITUAL_PROJECTOR), SpiritualProjectorCategory.NAME);
        registry.addRecipeClickArea(GuiSpiritualProjector.class, 81, 37, 22, 15, SpiritualProjectorCategory.NAME);
        
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        JeiHelpers jeiHelpers = Internal.getHelpers();
		GuiHelper guiHelper = jeiHelpers.getGuiHelper();
        registry.addRecipeCategories(
            new SpiritualProjectorCategory(guiHelper)
        );
    }
}
