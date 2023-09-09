package com.moffy5612.iinteg.item;

import java.util.ArrayList;
import java.util.List;

import com.moffy5612.iinteg.Reference;
import com.moffy5612.iinteg.misc.ModTier;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ArcaneCondenser  extends ModItemBase implements IModMultiTexturedItem{
    public static final String NAME = "arcane_condenser";

    public ArcaneCondenser(){
        super(NAME);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);

        this.register();
    }

    @Override
    public String[] getSubItemNames() {
        List<String> names = new ArrayList<>();
        for(ModTier tier:ModTier.values()){
            names.add(NAME+"_"+tier.getName());
        }
        return names.toArray(new String[ModTier.values().length]);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item."+Reference.MOD_ID+":"+NAME+"."+ModTier.getTierFromIndex(stack.getMetadata()).getName();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(tab.equals(Reference.MOD_CREATIVE_TAB)){
            for(int i = 0; i < ModTier.values().length; i++){
                items.add(new ItemStack(this, 1, i));
            }
        }
    }
}
