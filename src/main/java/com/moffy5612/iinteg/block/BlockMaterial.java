package com.moffy5612.iinteg.block;

import net.minecraft.block.material.Material;

public class BlockMaterial extends ModBlockBase{
    public static final String[] NAMES = {
        "etheric_machine_casing"
    };

    public BlockMaterial(int nameNum){
        super(Material.IRON, NAMES[nameNum], null);
        this.register();
    }
}
