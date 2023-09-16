package com.moffy5612.iinteg.integration.jei.category;

import javax.annotation.Nullable;

import com.moffy5612.iinteg.IInteg;
import com.moffy5612.iinteg.Reference;
import com.moffy5612.iinteg.block.AdvancedProjector;
import com.moffy5612.iinteg.handler.ModBlockHandler;
import com.moffy5612.iinteg.integration.jei.ModRecipeWrapper;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;


public class AdvancedProjectorCategory implements IRecipeCategory<ModRecipeWrapper>{

    public static final String NAME = AdvancedProjector.NAME;

    private IDrawable background;
	private IDrawable icon;
    private IDrawableAnimated arrowMain;
    private IDrawableAnimated arrowAbove;
    private IDrawableAnimated arrowBelow;
    private IDrawableAnimated energyBar;

    public AdvancedProjectorCategory(IGuiHelper helper){
        this.background = helper.drawableBuilder(
            new ResourceLocation(Reference.MOD_ID, "textures/jei/jei_"+NAME+".png"), 
            0, 
            0, 
            116, 
            70
        ).setTextureSize(159, 71)
        .build();
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlockHandler.ADVANCED_PROJECTOR));
        this.arrowMain = helper.drawableBuilder(
            new ResourceLocation(Reference.MOD_ID, "textures/jei/jei_"+NAME+".png"), 
            121, 
            0, 
            38, 
            15
        ).setTextureSize(159, 71)
        .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
        this.arrowAbove = helper.drawableBuilder(
            new ResourceLocation(Reference.MOD_ID, "textures/jei/jei_"+NAME+".png"), 
            122, 
            16, 
            16, 
            8
        ).setTextureSize(159, 71)
        .buildAnimated(100, IDrawableAnimated.StartDirection.TOP, false);
        this.arrowBelow = helper.drawableBuilder(
            new ResourceLocation(Reference.MOD_ID, "textures/jei/jei_"+NAME+".png"), 
            139, 
            16, 
            16, 
            8
        ).setTextureSize(159, 71)
        .buildAnimated(100, IDrawableAnimated.StartDirection.BOTTOM, false);
        this.energyBar = helper.drawableBuilder(
            new ResourceLocation(Reference.MOD_ID, "textures/jei/jei_"+NAME+".png"), 
            116, 
            2, 
            4, 
            66
        ).setTextureSize(159, 71)
        .buildAnimated(200, IDrawableAnimated.StartDirection.BOTTOM, false);
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    @Nullable
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public String getModName() {
        return Reference.MOD_NAME;
    }

    @Override
    public String getTitle() {
        return IInteg.proxy.translate("tile." + Reference.MOD_ID + ":" + NAME + ".name");
    }

    @Override
    public String getUid() {
        return NAME;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        arrowMain.draw(minecraft, 44, 29);
        arrowAbove.draw(minecraft, 52, 23);
        arrowBelow.draw(minecraft, 52, 42);
        energyBar.draw(minecraft, 2, 2);
    }

    @Override
    public void setRecipe(IRecipeLayout layout, ModRecipeWrapper wrapper, IIngredients ingredients) {
        IGuiItemStackGroup group = layout.getItemStacks();
        group.init(0, true, 51, 4);
        group.init(1, true, 19, 28);
        group.init(2, true, 51, 51);
        group.init(3, false, 93, 28);
        group.set(0, wrapper.inputList.get(0).get(0));
        group.set(1, wrapper.inputList.get(0).get(1));
        group.set(2, wrapper.inputList.get(0).get(2));
        group.set(3, wrapper.output);
    }
}
