package com.moffy5612.iinteg.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;

public class BlockMaterial extends ModBlockBase{
    public static final String[] NAMES = {
        "etheric_machine_casing"
    };

    public BlockMaterial(int nameNum){
        super(Material.IRON, NAMES[nameNum], null);
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
