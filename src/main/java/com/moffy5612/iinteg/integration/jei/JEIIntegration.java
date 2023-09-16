package com.moffy5612.iinteg.integration.jei;

import java.util.ArrayList;
import java.util.List;

import com.moffy5612.iinteg.client.gui.GuiAdvancedProjector;
import com.moffy5612.iinteg.client.gui.GuiSpiritualProjector;
import com.moffy5612.iinteg.handler.ModBlockHandler;
import com.moffy5612.iinteg.handler.ModRecipeHandler;
import com.moffy5612.iinteg.integration.jei.category.AdvancedProjectorCategory;
import com.moffy5612.iinteg.integration.jei.category.SpiritualProjectorCategory;
import com.moffy5612.iinteg.recipe.ModRecipeListBase;
import com.moffy5612.iinteg.recipe.ModRecipeListBase.ModRecipe;

import mezz.jei.Internal;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.gui.GuiHelper;
import mezz.jei.runtime.JeiHelpers;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEIIntegration implements IModPlugin{

    @Override
    public void register(IModRegistry registry) {
        registerModRecipe(
            registry, 
            ModBlockHandler.SPIRITUAL_PROJECTOR,
            GuiSpiritualProjector.class,
            ModRecipeHandler.SPIRITUAL_PROJECTOR_RECIPE, 
            SpiritualProjectorCategory.NAME, 
            81, 37, 22, 15
        );
        registerModRecipe(
            registry,
            ModBlockHandler.ADVANCED_PROJECTOR,
            GuiAdvancedProjector.class,
            ModRecipeHandler.ADVANCED_PROJECTOR_RECIPE,
            AdvancedProjectorCategory.NAME,
            75, 32, 38, 27
        );
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        JeiHelpers jeiHelpers = Internal.getHelpers();
		GuiHelper guiHelper = jeiHelpers.getGuiHelper();
        registry.addRecipeCategories(
            new SpiritualProjectorCategory(guiHelper)
        );
        registry.addRecipeCategories(
            new AdvancedProjectorCategory(guiHelper)
        );
    }

    public void registerModRecipe(IModRegistry registry, Block block, Class<? extends GuiContainer> guiClass, ModRecipeListBase recipeList, String name, int x, int y, int width, int height){
        List<ModRecipeWrapper> modRecipeWrappers = new ArrayList<>();
        for(ModRecipe recipe : recipeList.recipes.values()){
            modRecipeWrappers.add(new ModRecipeWrapper(recipe.material, recipe.result));
        }

        registry.addRecipes(modRecipeWrappers, name);
        registry.addRecipeCatalyst(new ItemStack(block), name);
        registry.addRecipeClickArea(guiClass, x, y, width, height, name);
    }
}
