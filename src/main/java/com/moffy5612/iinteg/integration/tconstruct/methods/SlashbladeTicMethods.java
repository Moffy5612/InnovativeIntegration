package com.moffy5612.iinteg.integration.tconstruct.methods;

import com.moffy5612.iinteg.block.tileentity.TileAdvancedForge;

import cn.mmf.slashblade_tic.blade.TinkerSlashBladeRegistry;
import cn.mmf.slashblade_tic.util.SlashBladeBuilder;
import cn.mmf.slashblade_tic.util.SlashBladeHelper;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import slimeknights.tconstruct.library.events.TinkerCraftingEvent;
import slimeknights.tconstruct.library.modifiers.TinkerGuiException;

public class SlashbladeTicMethods {
    public static boolean tryRepairBlade(TileAdvancedForge trf){
        ItemStack toolStack = trf.inventory.getStackInSlot(0);
        NonNullList<ItemStack>materials = NonNullList.withSize(5, ItemStack.EMPTY);
        for(int i = 1; i < trf.inventory.getSlots() - 1; i++)materials.set(i - 1, trf.inventory.getStackInSlot(i));
        if(!materials.isEmpty()){
            if(trf.inventory.getStackInSlot(6).isEmpty()){
                ItemStack result = SlashBladeBuilder.tryRepairTool(materials, toolStack, false);
                if(result != null && !result.isEmpty()){
                    result = SlashBladeBuilder.tryRepairTool(materials, toolStack, true);
                    if(result != null && !result.isEmpty()){
                        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(result);
                        if(tag != null)ItemSlashBlade.RepairCount.set(tag, ItemSlashBlade.RepairCount.get(tag) + 1);
                        float attack = SlashBladeHelper.getActualAttack(result);
                        ItemSlashBlade.setBaseAttackModifier(tag, attack);
                        trf.inventory.setStackInSlot(6, result);
                        trf.inventory.extractItem(0, 1, false);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean tryModifyBlade(TileAdvancedForge trf){
        ItemStack toolStack = trf.inventory.getStackInSlot(0);
        NonNullList<ItemStack>materials = NonNullList.withSize(5, ItemStack.EMPTY);
        for(int i = 1; i < trf.inventory.getSlots() - 1; i++)materials.set(i - 1, trf.inventory.getStackInSlot(i));
        if(!materials.isEmpty()){
            try{
                if(trf.inventory.getStackInSlot(6).isEmpty()){
                    ItemStack result = SlashBladeBuilder.tryModifyTool(materials, toolStack, false);
                    if(result != null && !result.isEmpty()){
                        result = SlashBladeBuilder.tryModifyTool(materials, toolStack, true);
                        if(result != null && !result.isEmpty()){
                            TinkerCraftingEvent.ToolModifyEvent.fireEvent(result, null, toolStack);
                            NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(result);
                            if(tag != null)ItemSlashBlade.RepairCount.set(tag, ItemSlashBlade.RepairCount.get(tag) + 1);
                            float attack = SlashBladeHelper.getActualAttack(result);
                            ItemSlashBlade.setBaseAttackModifier(tag, attack);
                            trf.inventory.setStackInSlot(6, result);
                            trf.inventory.extractItem(0, 1, false);
                            return true;
                        }
                    }
                }
            }catch(TinkerGuiException exception){}
        }
        return false;
    }

    public static boolean tryBuildBlade(TileAdvancedForge trf){
        NonNullList<ItemStack>materials = NonNullList.withSize(6, ItemStack.EMPTY);
        for(int i = 0; i < trf.inventory.getSlots() - 1; i++)materials.set(i, trf.inventory.getStackInSlot(i));
        if(!materials.isEmpty()){
            try{
                if(trf.inventory.getStackInSlot(6).isEmpty()){
                    ItemStack result = SlashBladeBuilder.tryBuildTool(materials, null, TinkerSlashBladeRegistry.getToolForgeCrafting());
                    if(result != null && !result.isEmpty()){
                        TinkerCraftingEvent.ToolCraftingEvent.fireEvent(result, null, materials);
                        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(result);
                        if(tag != null)ItemSlashBlade.RepairCount.set(tag, ItemSlashBlade.RepairCount.get(tag) + 1);
                        float attack = SlashBladeHelper.getActualAttack(result);
                        ItemSlashBlade.setBaseAttackModifier(tag, attack);
                        trf.inventory.setStackInSlot(6, result);
                        for(int i = 0; i < trf.inventory.getSlots() - 1; i++)trf.inventory.extractItem(i, 1, false);
                        return true;
                    }
                }
            }catch(TinkerGuiException e){}
        }
        return false;
    }
}
