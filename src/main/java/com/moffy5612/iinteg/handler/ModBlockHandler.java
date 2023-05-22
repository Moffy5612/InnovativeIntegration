package com.moffy5612.iinteg.handler;

import java.util.HashSet;
import java.util.Set;

import com.moffy5612.iinteg.block.AdvancedForge;
import com.moffy5612.iinteg.block.AdvancedPartBuilder;
import com.moffy5612.iinteg.block.EthericCastingTable;
import com.moffy5612.iinteg.block.EthericMachineCasing;
import com.moffy5612.iinteg.block.SpiritualProjector;
import com.moffy5612.iinteg.misc.annotation.IIntegContentInstance;

import net.minecraft.block.BlockContainer;

public class ModBlockHandler {
    public static final Set<BlockContainer> REG_BLOCKS = new HashSet<BlockContainer>();

    @IIntegContentInstance(contentClass = EthericMachineCasing.class) public static BlockContainer ETHERIC_MACHINE_CASING;
    @IIntegContentInstance(contentClass = SpiritualProjector.class) public static BlockContainer SPIRITUAL_PROJECTOR;

    @IIntegContentInstance(
        contentClass = EthericCastingTable.class,
        requiredModIds = {"tconstruct"}
    ) 
    public static BlockContainer ETHERIC_CASTING_TABLE;

    @IIntegContentInstance(
        contentClass = AdvancedPartBuilder.class,
        requiredModIds = {"tconstruct"}
    ) 
    public static BlockContainer ADVANCED_PART_BUILDER;

    @IIntegContentInstance(
        contentClass = AdvancedForge.class,
        requiredModIds = {"tconstruct"}
    ) 
    public static BlockContainer ADVANCED_FORGE;
}
