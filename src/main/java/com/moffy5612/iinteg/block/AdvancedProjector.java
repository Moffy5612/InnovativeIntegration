package com.moffy5612.iinteg.block;

import javax.annotation.Nullable;

import com.moffy5612.iinteg.block.tileentity.TileAdvancedProjector;
import com.moffy5612.iinteg.misc.ModTier;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class AdvancedProjector extends ModMachineBlockBase{

    public static final String NAME = "advanced_projector";

    public AdvancedProjector(){
        super(NAME, new TileAdvancedProjector());
    }

    @Override
    @Nullable
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileAdvancedProjector(ModTier.getTierFromIndex(meta));
    }
}
