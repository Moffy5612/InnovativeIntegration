package com.moffy5612.iinteg.handler;

import java.util.HashSet;
import java.util.Set;

import com.moffy5612.iinteg.item.CrystalBall;
import com.moffy5612.iinteg.item.Material;

import net.minecraft.item.Item;

public class ItemHandler {
    public static final Set<Item> REG_ITEMS = new HashSet<Item>();
    public static final Set<Item> REG_BLOCK_ITEMS = new HashSet<Item>();
    
    public static final Material MATERIAL = new Material();
    public static final CrystalBall CRYSTAL_BALL = new CrystalBall();
    
}
