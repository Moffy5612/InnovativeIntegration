package com.moffy5612.iinteg.handler;

import java.util.HashSet;
import java.util.Set;

import com.moffy5612.iinteg.item.CrystalBall;
import com.moffy5612.iinteg.item.Material;
import com.moffy5612.iinteg.item.MysticalCircuit;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class ModItemHandler {
    public static final Set<Item> REG_ITEMS = new HashSet<Item>();
    public static final Set<ItemBlock> REG_BLOCK_ITEMS = new HashSet<ItemBlock>();
    
    public static final Material MATERIAL = new Material();
    public static final MysticalCircuit MYSTICAL_CIRCUIT = new MysticalCircuit();
    public static final CrystalBall CRYSTAL_BALL = new CrystalBall();
    
}
