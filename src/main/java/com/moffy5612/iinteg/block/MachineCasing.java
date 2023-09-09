package com.moffy5612.iinteg.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;

public class MachineCasing extends ModMachineBlockBase{
    public static final String NAME = "machine_casing";

    public MachineCasing(){
        super(NAME, null);
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
	public boolean isOpaqueCube(IBlockState state){
		return false;
	}
}
