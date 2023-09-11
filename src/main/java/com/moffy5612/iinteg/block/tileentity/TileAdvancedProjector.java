package com.moffy5612.iinteg.block.tileentity;

import com.moffy5612.iinteg.misc.ModTier;

public class TileAdvancedProjector extends TileMachineBase{

    public static final String NAME = "advanced_projector";

    public TileAdvancedProjector(){
        this(null);
    }

    public TileAdvancedProjector(ModTier tier){
        super(NAME, tier, false, 5);
    }

    @Override
    public void update() {
        
    }
}
