package com.moffy5612.iinteg.integration.tconstruct.handler;

import java.util.HashSet;
import java.util.Set;

import com.moffy5612.iinteg.integration.tconstruct.modifier.ModifierPsionic;

import c4.conarm.lib.modifiers.ArmorModifierTrait;

public class TraitHandler {
    public static final ModifierPsionic PSIONIC = new ModifierPsionic();

    public static final Set<ArmorModifierTrait> REG_ARMOR_TRAIT = new HashSet<ArmorModifierTrait>();
}
