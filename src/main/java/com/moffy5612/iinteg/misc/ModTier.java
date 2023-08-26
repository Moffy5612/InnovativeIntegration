package com.moffy5612.iinteg.misc;

import net.minecraft.util.IStringSerializable;

public enum ModTier implements IStringSerializable{
    ETHERIC(0, "etheric"),
    ASTRAL(1, "astral"),
    CAUSAL(2, "causal");

    private final int index;
    private final String name;

    ModTier(int index, String name){
        this.index = index;
        this.name = name;
    }

    public int getIndex(){
        return this.index;
    }

    public String getName(){
        return this.name;
    }

    public static ModTier getTierFromIndex(int index){
        if(index == CAUSAL.index)return CAUSAL;
        else if(index == ASTRAL.index)return ASTRAL;
        else return ETHERIC;
    }

    public static ModTier getTierFromName(String name){
        if(name.equals(CAUSAL.name))return CAUSAL;
        else if(name.equals(ASTRAL.name))return ASTRAL;
        else return ETHERIC;
    }
}
