package com.moffy5612.iinteg.recipe;

import com.moffy5612.iinteg.capability.item.CapabilityCrystalBall;
import com.moffy5612.iinteg.handler.ModItemHandler;
import com.moffy5612.iinteg.item.Material;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class SpiritualProjectorRecipe extends ModRecipeListBase{
    public SpiritualProjectorRecipe(){

        ItemStack crystalBall = new ItemStack(ModItemHandler.CRYSTAL_BALL, 1, 0);
        CapabilityCrystalBall capabilityCrystalBall = crystalBall.getCapability(CapabilityCrystalBall.CAPABILITY_CRYSTAL_BALL, null);
        if(capabilityCrystalBall != null){
            capabilityCrystalBall.hasOwner = true;
            capabilityCrystalBall.ownerName = "A Stranger";
        }

        this.addRecipe(
            Material.TYPES[0],
            new ItemStack(ModItemHandler.MATERIAL, 1, 0), 
            crystalBall,
            new ItemStack(Items.IRON_INGOT, 1, 0)
        );

        this.addRecipe(
            Material.TYPES[1],
            new ItemStack(ModItemHandler.MATERIAL, 1, 1), 
            crystalBall,
            new ItemStack(Items.REDSTONE, 1, 0)
        );

        this.addRecipe(
            Material.TYPES[2],
            new ItemStack(ModItemHandler.MATERIAL, 1, 2), 
            crystalBall,
            new ItemStack(Items.GLOWSTONE_DUST, 1, 0)
        );
    }
}
