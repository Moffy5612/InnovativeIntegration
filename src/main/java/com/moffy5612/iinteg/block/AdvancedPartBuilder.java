package com.moffy5612.iinteg.block;

import javax.annotation.Nullable;

import com.moffy5612.iinteg.IInteg;
import com.moffy5612.iinteg.block.tileentity.TileAdvancedPartBuilder;
import com.moffy5612.iinteg.client.gui.IIntegGuiList;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AdvancedPartBuilder extends ModBlockBase{

    public static final String NAME = "advanced_part_builder";

    public AdvancedPartBuilder(){
        super(Material.WOOD, NAME, new TileAdvancedPartBuilder());
        this.setSoundType(SoundType.WOOD);
        this.setResistance(5f);
        this.setHardness(1f);

        this.register();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
            EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(playerIn.isSneaking()){
            
        }else{
            playerIn.openGui(IInteg.INSTANCE, IIntegGuiList.GUI_ADVANCED_PART_BUILDER, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileAdvancedPartBuilder trpb = (TileAdvancedPartBuilder)worldIn.getTileEntity(pos);
        if(trpb != null){
            for(int i = 0; i < trpb.inventory.getSlots(); i++){
                ItemStack stack = trpb.inventory.getStackInSlot(i);
                EntityItem entityItem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ());
                entityItem.setItem(stack);
                worldIn.spawnEntity(entityItem);
            }
        }
    }

    @Override
    @Nullable
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileAdvancedPartBuilder();
    }
}
