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

    public static final BlockMaterial ETHERIC_MACHINE_CASING = new BlockMaterial(0);
    public static final SpiritualProjector SPIRITUAL_PROJECTOR = new SpiritualProjector();

    public static final EthericCastingTable ETHERIC_CASTING_TABLE = new EthericCastingTable();
    public static final AdvancedPartBuilder ADVANCED_PART_BUILDER = new AdvancedPartBuilder();
    public static final AdvancedForge ADVANCED_FORGE = new AdvancedForge();
}
