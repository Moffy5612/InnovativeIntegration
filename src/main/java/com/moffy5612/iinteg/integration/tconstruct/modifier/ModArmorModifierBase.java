package com.moffy5612.iinteg.integration.tconstruct.modifier;

import com.moffy5612.iinteg.integration.tconstruct.handler.ModTraitHandler;

import c4.conarm.lib.modifiers.ArmorModifierTrait;

public class ModArmorModifierBase extends ArmorModifierTrait{
    public ModArmorModifierBase(String name, int color){
        super(name, color);
    }

    public void register(){
        ModTraitHandler.REG_ARMOR_TRAIT.add(this);
    }
}
