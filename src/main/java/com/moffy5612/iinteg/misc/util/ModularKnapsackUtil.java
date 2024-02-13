package com.moffy5612.iinteg.misc.util;

import com.moffy5612.iinteg.handler.ModItemHandler;
import com.moffy5612.iinteg.integration.baubles.ModularKnapsackBauble;
import com.moffy5612.iinteg.item.ModularKnapsack;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

public class ModularKnapsackUtil {
    public static ItemStack getModularKnapsack(EntityLivingBase entity){
        ItemStack chestPlate = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

        ItemStack baublesBody = ItemStack.EMPTY;

        if(Loader.isModLoaded("baubles") && entity instanceof EntityPlayer){
            baublesBody = ModularKnapsackBauble.getItemStackInBaublesSlot((EntityPlayer)entity, ModItemHandler.MODULAR_KNAPSACK);
        }
        
        if(entity.getHeldItemMainhand().getItem() instanceof ModularKnapsack)return entity.getHeldItemMainhand();
        else if(entity.getHeldItemOffhand().getItem() instanceof ModularKnapsack)return entity.getHeldItemOffhand();
        else if(chestPlate.getItem() instanceof ModularKnapsack)return chestPlate;
        else if(!baublesBody.isEmpty())return baublesBody;

        return ItemStack.EMPTY;
    }
}
