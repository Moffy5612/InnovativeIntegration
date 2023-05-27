package com.moffy5612.iinteg.item;

import com.moffy5612.iinteg.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ArcaneCondenser  extends ModItemBase implements IModMultiTexturedItem{
    public static final String NAME = "arcane_condenser";
    public static final String[] TYPES = {
        "arcane_condenser_etheric"
    };

    public ArcaneCondenser(){
        super(NAME);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);

        this.register();
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
