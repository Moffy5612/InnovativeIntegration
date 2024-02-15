package com.moffy5612.iinteg.integration.tconstruct.handler;

import java.util.HashSet;
import java.util.Set;

import com.moffy5612.iinteg.integration.tconstruct.modifier.ModifierPsionic;
import com.moffy5612.iinteg.misc.annotation.IIntegContentInstance;

import slimeknights.tconstruct.library.traits.AbstractTrait;

public class ModTraitHandler {
    public static final Set<AbstractTrait> REG_TRAIT = new HashSet<AbstractTrait>();
    public static final Set<AbstractTrait> REG_ARMOR_TRAIT = new HashSet<AbstractTrait>();

    @IIntegContentInstance(
        contentClass = ModifierPsionic.class,
        requiredModIds = {"conarm", "psi"}
    )
    public static AbstractTrait PSIONIC;
}
