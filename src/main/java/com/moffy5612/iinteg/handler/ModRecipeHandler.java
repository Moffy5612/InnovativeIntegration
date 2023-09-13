package com.moffy5612.iinteg.handler;

import com.moffy5612.iinteg.misc.annotation.IIntegContentInstance;
import com.moffy5612.iinteg.recipe.AdvancedProjectorRecipe;
import com.moffy5612.iinteg.recipe.SpiritualProjectorRecipe;

public class ModRecipeHandler {
    @IIntegContentInstance(contentClass = SpiritualProjectorRecipe.class)public static SpiritualProjectorRecipe SPIRITUAL_PROJECTOR_RECIPE;
    @IIntegContentInstance(contentClass = AdvancedProjectorRecipe.class)public static AdvancedProjectorRecipe ADVANCED_PROJECTOR_RECIPE;
}
