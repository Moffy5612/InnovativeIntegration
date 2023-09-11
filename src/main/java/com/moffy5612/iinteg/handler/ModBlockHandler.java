package com.moffy5612.iinteg.handler;

import java.util.HashSet;
import java.util.Set;

import com.moffy5612.iinteg.block.AdvancedToolForge;
import com.moffy5612.iinteg.block.AdvancedPartBuilder;
import com.moffy5612.iinteg.block.AdvancedProjector;
import com.moffy5612.iinteg.block.EthericCastingTable;
import com.moffy5612.iinteg.block.MachineCasing;
import com.moffy5612.iinteg.block.SpiritualGenerator;
import com.moffy5612.iinteg.block.SpiritualProjector;
import com.moffy5612.iinteg.misc.annotation.IIntegContentInstance;

import net.minecraft.block.BlockContainer;

public class ModBlockHandler {
    public static final Set<BlockContainer> REG_BLOCKS = new HashSet<BlockContainer>();

    @IIntegContentInstance(contentClass = AdvancedProjector.class) public static BlockContainer ADVANCED_PROJECTOR;
    @IIntegContentInstance(contentClass = MachineCasing.class) public static BlockContainer MACHINE_CASING;
    @IIntegContentInstance(contentClass = SpiritualGenerator.class) public static BlockContainer SPIRITUAL_GENERATOR;
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
        contentClass = AdvancedToolForge.class,
        requiredModIds = {"tconstruct"}
    )
    public static BlockContainer ADVANCED_TOOL_FORGE;
}
