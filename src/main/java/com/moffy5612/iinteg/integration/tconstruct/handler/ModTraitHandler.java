package com.moffy5612.iinteg.integration.tconstruct.handler;

import java.util.HashSet;
import java.util.Set;

import com.moffy5612.iinteg.integration.tconstruct.modifier.ModifierPsionic;
import com.moffy5612.iinteg.misc.annotation.IIntegContentInstance;

import c4.conarm.lib.modifiers.ArmorModifierTrait;

public class ModTraitHandler {
    public static final Set<ArmorModifierTrait> REG_ARMOR_TRAIT = new HashSet<ArmorModifierTrait>();

    @IIntegContentInstance(
        contentClass = ModifierPsionic.class,
        requiredModIds = {"conarm", "psi"}
    )
    public static ModifierPsionic PSIONIC;
}
