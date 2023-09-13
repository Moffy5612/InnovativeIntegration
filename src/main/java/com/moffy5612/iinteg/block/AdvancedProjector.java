package com.moffy5612.iinteg.block;

import javax.annotation.Nullable;

import com.moffy5612.iinteg.IInteg;
import com.moffy5612.iinteg.block.tileentity.TileAdvancedProjector;
import com.moffy5612.iinteg.capability.item.CapabilityCrystalBall;
import com.moffy5612.iinteg.client.gui.IIntegGuiList;
import com.moffy5612.iinteg.handler.ModItemHandler;
import com.moffy5612.iinteg.misc.ModTier;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AdvancedProjector extends ModMachineBlockBase{

    public static final String NAME = "advanced_projector";

    public AdvancedProjector(){
        super(NAME, new TileAdvancedProjector());
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
            EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.getHeldItem(hand);
        if(stack.getItem().equals(ModItemHandler.CRYSTAL_BALL)){
            CapabilityCrystalBall capabilityCrystalBall = stack.getCapability(CapabilityCrystalBall.CAPABILITY_CRYSTAL_BALL, null);
            if(capabilityCrystalBall != null){
                if(capabilityCrystalBall.hasOwner){
                    TileAdvancedProjector tap = (TileAdvancedProjector)worldIn.getTileEntity(pos);
                    if(tap != null){
                        tap.inventory.insertItem(0, stack, false);
                        playerIn.setHeldItem(hand, ItemStack.EMPTY);
                        return true;
                    }
                }
            }
        }

        if(!playerIn.isSneaking()){
            playerIn.openGui(IInteg.INSTANCE, IIntegGuiList.GUI_ADVANCED_PROJECTOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }

        return false;
    }

    @Override
    @Nullable
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileAdvancedProjector(ModTier.getTierFromIndex(meta));
    }
}
