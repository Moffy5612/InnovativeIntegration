package com.moffy5612.iinteg.block;

import javax.annotation.Nullable;

import com.moffy5612.iinteg.IInteg;
import com.moffy5612.iinteg.block.tileentity.TileAdvancedToolForge;
import com.moffy5612.iinteg.client.gui.IIntegGuiList;
import com.moffy5612.iinteg.misc.ModTier;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AdvancedToolForge extends ModMachineBlockBase{
    public static final String NAME = "advanced_tool_forge";

    public AdvancedToolForge(){
        super(NAME, new TileAdvancedToolForge());
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
            EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(playerIn.isSneaking()){
            
        }else{
            playerIn.openGui(IInteg.INSTANCE, IIntegGuiList.GUI_ADVANCED_TOOL_FORGE, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        
        return true;
    }

    @Override
    @Nullable
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileAdvancedToolForge(ModTier.getTierFromIndex(meta));
    }
}
