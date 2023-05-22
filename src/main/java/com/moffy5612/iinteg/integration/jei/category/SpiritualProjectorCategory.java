package com.moffy5612.iinteg.integration.jei.category;

import javax.annotation.Nullable;

import com.moffy5612.iinteg.IInteg;
import com.moffy5612.iinteg.Reference;
import com.moffy5612.iinteg.block.SpiritualProjector;
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

public class SpiritualProjectorCategory implements IRecipeCategory<ModRecipeWrapper>{
    public static final String NAME = SpiritualProjector.NAME;

    private IDrawable background;
	private IDrawable icon;
    private IDrawableAnimated arrow;

    public SpiritualProjectorCategory(IGuiHelper helper){
        this.background = helper.drawableBuilder(
                new ResourceLocation(Reference.MOD_ID, "textures/jei/jei_"+NAME+".png"),
                0,
                0,
                100,
                41
            ).setTextureSize(123, 41)
            .build();
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlockHandler.SPIRITUAL_PROJECTOR));
        this.arrow = helper.drawableBuilder(
            new ResourceLocation(Reference.MOD_ID, "textures/jei/jei_"+NAME+".png"),
            101,
            0,
            21,
            15
        ).setTextureSize(123, 41)
        .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
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
        arrow.draw(minecraft, 45, 13);
    }

    @Override
    public void setRecipe(IRecipeLayout layout, ModRecipeWrapper wrapper, IIngredients ingredients) {
        IGuiItemStackGroup group = layout.getItemStacks();
        group.init(0, true, 22, 0);
        group.init(1, true, 22, 23);
        group.init(2, false, 78, 12);
        group.set(0, wrapper.inputList.get(0).get(0));
        group.set(1, wrapper.inputList.get(0).get(1));
        group.set(2, wrapper.output);
    }
}
