package com.moffy5612.iinteg.handler;

import java.util.HashSet;
import java.util.Set;

import com.moffy5612.iinteg.block.AdvancedCastingTable;
import com.moffy5612.iinteg.block.AdvancedForge;
import com.moffy5612.iinteg.block.AdvancedPartBuilder;
import com.moffy5612.iinteg.block.SpiritualProjector;

import net.minecraft.block.BlockContainer;

public class BlockHandler {
    public static final Set<BlockContainer> REG_BLOCKS = new HashSet<BlockContainer>();

    public static final SpiritualProjector SPIRITUAL_PROJECTOR = new SpiritualProjector();

    public static final AdvancedCastingTable ADVANCED_CASTING_TABLE = new AdvancedCastingTable();
    public static final AdvancedPartBuilder ADVANCED_PART_BUILDER = new AdvancedPartBuilder();
    public static final AdvancedForge ADVANCED_FORGE = new AdvancedForge();
}
