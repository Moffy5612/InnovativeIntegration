package com.moffy5612.iinteg.block.tileentity;

import com.moffy5612.iinteg.misc.ModTier;

public class TileSpiritualGenerator extends TileMachineBase{
    
    public static final String NAME = "spiritual_generator";

    public TileSpiritualGenerator(){
        this(null);
    }

    public TileSpiritualGenerator(ModTier tier){
        super(NAME, tier, true, 1);
    }

    @Override
    public void update() {
        this.extractEnergy();
    }
}
