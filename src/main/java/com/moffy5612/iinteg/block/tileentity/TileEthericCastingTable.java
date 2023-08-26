package com.moffy5612.iinteg.block.tileentity;

import com.moffy5612.iinteg.Reference;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import slimeknights.tconstruct.smeltery.tileentity.TileCastingTable;

public class TileEthericCastingTable extends TileCastingTable{

    public static final String NAME = "ehteric_casting_table";

    public TileEthericCastingTable(){
        super();
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        if(!isStackInSlot(1) && this.tank.getFluidAmount() == 0)return index == 0;
        else return index == 1;
    }

    public void register(){
        GameRegistry.registerTileEntity(this.getClass(), new ResourceLocation(Reference.MOD_ID, NAME));
    }
}
