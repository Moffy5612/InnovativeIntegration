package com.moffy5612.iinteg.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;

public class EthericMachineCasing extends ModBlockBase{
    public static final String NAME = "etheric_machine_casing";

    public EthericMachineCasing(){
        super(Material.IRON, NAME, null);
        this.register();
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
