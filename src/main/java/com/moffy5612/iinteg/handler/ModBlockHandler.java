package com.moffy5612.iinteg.handler;

import java.util.HashSet;
import java.util.Set;

import com.moffy5612.iinteg.block.EthericCastingTable;
import com.moffy5612.iinteg.block.AdvancedForge;
import com.moffy5612.iinteg.block.AdvancedPartBuilder;
import com.moffy5612.iinteg.block.BlockMaterial;
import com.moffy5612.iinteg.block.SpiritualProjector;

import net.minecraft.block.BlockContainer;

public class ModBlockHandler {
    public static final Set<BlockContainer> REG_BLOCKS = new HashSet<BlockContainer>();

    public static BlockContainer ETHERIC_MACHINE_CASING = new BlockMaterial(0);
    public static BlockContainer SPIRITUAL_PROJECTOR = new SpiritualProjector();

    public static BlockContainer ETHERIC_CASTING_TABLE = new EthericCastingTable();
    public static BlockContainer ADVANCED_PART_BUILDER = new AdvancedPartBuilder();
    public static BlockContainer ADVANCED_FORGE = new AdvancedForge();
}
