package com.moffy5612.iinteg.integration.tconstruct.methods;

import com.moffy5612.iinteg.block.tileentity.TileAdvancedToolForge;

import c4.conarm.lib.ArmoryRegistry;
import c4.conarm.lib.tinkering.ArmorBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import slimeknights.tconstruct.library.events.TinkerCraftingEvent;
import slimeknights.tconstruct.library.modifiers.TinkerGuiException;
import slimeknights.tconstruct.library.utils.ToolBuilder;

@SuppressWarnings("null")
public class ConarmMethods {
    public static boolean tryRepairArmor(TileAdvancedToolForge trf){
        ItemStack toolStack = trf.inventory.getStackInSlot(0);
        NonNullList<ItemStack>materials = NonNullList.withSize(5, ItemStack.EMPTY);
        for(int i = 1; i <= 5; i++)materials.set(i - 1, trf.inventory.getStackInSlot(i));
        if(!materials.isEmpty()){
            if(trf.inventory.getStackInSlot(6).isEmpty()){
                ItemStack result = ToolBuilder.tryRepairTool(materials, toolStack, false);
                if(result != null && !result.isEmpty()){
                    result = ToolBuilder.tryRepairTool(materials, toolStack, true);
                    
                    if(result != null && !result.isEmpty())trf.inventory.setStackInSlot(6, result);
                    trf.inventory.extractItem(0, 1, false);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean tryModifyArmor(TileAdvancedToolForge trf){
        ItemStack toolStack = trf.inventory.getStackInSlot(0);
        NonNullList<ItemStack>materials = NonNullList.withSize(5, ItemStack.EMPTY);
        for(int i = 1; i <= 5; i++)materials.set(i - 1, trf.inventory.getStackInSlot(i));
        if(!materials.isEmpty()){
            try{
                if(trf.inventory.getStackInSlot(6).isEmpty()){
                    ItemStack result = ArmorBuilder.tryModifyArmor(materials, toolStack, false);
                    if(result != null && !result.isEmpty()){
                        result = ArmorBuilder.tryModifyArmor(materials, toolStack, true);
                        if(result != null && !result.isEmpty())trf.inventory.setStackInSlot(6, result);
                        TinkerCraftingEvent.ToolModifyEvent.fireEvent(result, null, toolStack);
                        trf.inventory.extractItem(0, 1, false);
                        return true;
                    }
                }
            }catch(TinkerGuiException exception){}
        }
        return false;
    }

    public static boolean tryBuildArmor(TileAdvancedToolForge trf){
        NonNullList<ItemStack>materials = NonNullList.withSize(6, ItemStack.EMPTY);
        for(int i = 0; i <= 5; i++)materials.set(i, trf.inventory.getStackInSlot(i));
        if(!materials.isEmpty()){
            try{
                if(trf.inventory.getStackInSlot(6).isEmpty()){
                    ItemStack result = ArmorBuilder.tryBuildArmor(materials, null, ArmoryRegistry.getArmorCrafting());
                    if(result != null && !result.isEmpty()){
                        TinkerCraftingEvent.ToolCraftingEvent.fireEvent(result, null, materials);
                        trf.inventory.setStackInSlot(6, result);
                        for(int i = 0; i <= 5; i++)trf.inventory.extractItem(i, 1, false);
                        return true;
                    }
                }
            }catch(TinkerGuiException e){}
        }
        return false;
    }

    public static boolean hasRecipe(TileAdvancedToolForge trf){
        try{
            ItemStack core = trf.inventory.getStackInSlot(0);
            NonNullList<ItemStack>materials = NonNullList.withSize(5, ItemStack.EMPTY);
            NonNullList<ItemStack>all = NonNullList.withSize(6, ItemStack.EMPTY);
            
            all.set(0, core);
            for(int i = 1; i <= 5; i++){
                all.set(i, trf.inventory.getStackInSlot(i));
                materials.set(i - 1, trf.inventory.getStackInSlot(i));
            }

            ItemStack repair = ToolBuilder.tryRepairTool(materials, core, false);
            ItemStack modify = ArmorBuilder.tryModifyArmor(materials, core, false);
            ItemStack build = ArmorBuilder.tryBuildArmor(all, null, ArmoryRegistry.getArmorCrafting());

            return(!repair.isEmpty() || !modify.isEmpty() || !build.isEmpty());
        }catch(TinkerGuiException e){
            return false;
        }
    }
}
