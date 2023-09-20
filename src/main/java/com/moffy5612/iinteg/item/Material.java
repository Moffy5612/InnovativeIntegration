package com.moffy5612.iinteg.item;

import com.moffy5612.iinteg.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Material extends ModItemBase implements IModMultiTexturedItem{
    
    public static final String NAME = "material";
    public static final String[] TYPES = {
        "etheric_ingot",
        "reim_converter_anode",
        "reim_converter_cathode"
    };

    public Material(){
        super(NAME);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public String[] getSubItemNames() {
        return TYPES;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item."+Reference.MOD_ID+":"+TYPES[stack.getMetadata()];
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(tab.equals(Reference.MOD_CREATIVE_TAB)){
            for(int i = 0; i < TYPES.length; i++){
                items.add(new ItemStack(this, 1, i));
            }
        }
    }
}
