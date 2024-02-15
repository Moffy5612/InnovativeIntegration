package com.moffy5612.iinteg.integration.tconstruct.modifier;

import com.moffy5612.iinteg.integration.tconstruct.handler.ModTraitHandler;

import slimeknights.tconstruct.library.traits.AbstractTrait;

public class ModTraitBase extends AbstractTrait{
    public ModTraitBase(String name, int color){
        super(name, color);
    }

    public void register(){
        ModTraitHandler.REG_TRAIT.add(this);
    }
}
