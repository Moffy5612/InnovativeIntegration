package com.moffy5612.iinteg.block;

import javax.annotation.Nullable;

import com.moffy5612.iinteg.block.tileentity.TileSpiritualGenerator;
import com.moffy5612.iinteg.misc.ModTier;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SpiritualGenerator extends ModMachineBlockBase{
    
    public static final String NAME = "spiritual_generator";
    
    public SpiritualGenerator(){
        super(NAME, new TileSpiritualGenerator());
    }

    @Override
    @Nullable
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileSpiritualGenerator(ModTier.getTierFromIndex(meta));
    }
}
