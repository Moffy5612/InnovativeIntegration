package com.moffy5612.iinteg.handler;

import java.util.HashSet;
import java.util.Set;

import com.moffy5612.iinteg.item.CrystalBall;
import com.moffy5612.iinteg.item.Material;
import com.moffy5612.iinteg.item.MysticalCircuit;
import com.moffy5612.iinteg.misc.annotation.IIntegContentInstance;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class ModItemHandler {
    public static final Set<Item> REG_ITEMS = new HashSet<Item>();
    public static final Set<ItemBlock> REG_BLOCK_ITEMS = new HashSet<ItemBlock>();
    
    @IIntegContentInstance(contentClass = Material.class) public static Item MATERIAL;
    @IIntegContentInstance(contentClass = MysticalCircuit.class) public static Item MYSTICAL_CIRCUIT;
    @IIntegContentInstance(contentClass = CrystalBall.class) public static Item CRYSTAL_BALL;
    
}
