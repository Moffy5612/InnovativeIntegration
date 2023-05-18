package com.moffy5612.iinteg.block;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.moffy5612.iinteg.IInteg;
import com.moffy5612.iinteg.block.tileentity.TileAdvancedForge;
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

public class AdvancedForge extends ModBlockBase{
    public static final String NAME = "advanced_forge";

    public AdvancedForge(){
        super(Material.IRON, NAME, new TileAdvancedForge());

        this.setSoundType(SoundType.METAL);
        this.setResistance(10f);
        this.setHardness(2f);

        this.setHarvestLevel("pickaxe", 0);

        this.register();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
            EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(playerIn.isSneaking()){
            
        }else{
            playerIn.openGui(IInteg.INSTANCE, IIntegGuiList.GUI_ADVANCED_FORGE, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileAdvancedForge trf = (TileAdvancedForge)worldIn.getTileEntity(pos);
        if(trf != null){
            for(int i = 0; i < trf.inventory.getSlots(); i++){
                ItemStack stack = trf.inventory.getStackInSlot(i);
                EntityItem entityItem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ());
                entityItem.setItem(stack);
                worldIn.spawnEntity(entityItem);
            }
        }
    }

    @Override
    @Nonnull
    public Set<String> getRequiredMods() {
        Set<String>requiredMods = new HashSet<String>();
        requiredMods.add("tconstruct");
        return requiredMods;
    }

    @Override
    @Nullable
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileAdvancedForge();
    }
}
