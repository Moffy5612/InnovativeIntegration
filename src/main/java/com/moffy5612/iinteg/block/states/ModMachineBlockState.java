package com.moffy5612.iinteg.block.states;

import com.moffy5612.iinteg.block.ModMachineBlockBase;
import com.moffy5612.iinteg.misc.ModTier;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

public class ModMachineBlockState extends ExtendedBlockState{
    
    public static final PropertyEnum<ModTier> TIER_PROPERTY = PropertyEnum.create("tier", ModTier.class);

    public ModMachineBlockState(ModMachineBlockBase machineBlock){
        super(
            machineBlock,
            new IProperty[]{
                TIER_PROPERTY
            },
            new IUnlistedProperty[]{}
        );
    }
}
