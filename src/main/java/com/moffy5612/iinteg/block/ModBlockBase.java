package com.moffy5612.iinteg.block;

import javax.annotation.Nullable;

import com.moffy5612.iinteg.Reference;
import com.moffy5612.iinteg.block.tileentity.ModTileEntityBase;
import com.moffy5612.iinteg.handler.ModBlockHandler;
import com.moffy5612.iinteg.handler.ModItemHandler;
import com.moffy5612.iinteg.item.ModItemBlock;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

public abstract class ModBlockBase extends BlockContainer{

    public String name;

    @Nullable
    public ModTileEntityBase te;

    public ModBlockBase(Material material, String name, ModTileEntityBase te){
        super(material);
        this.setRegistryName(Reference.MOD_ID, name);
        this.setUnlocalizedName(Reference.MOD_ID+":"+name);
        this.setCreativeTab(Reference.MOD_CREATIVE_TAB);
        this.name = name;
        this.te = te;

        this.register();
    }

    public void register(){
        ModBlockHandler.registerBlockContainer(this);
        ModItemBlock itemBlock = new ModItemBlock(this);
        itemBlock.setRegistryName(Reference.MOD_ID, name);
        ModItemHandler.registerItemBlock(itemBlock);
        ModTileEntityBase te = this.te;
        if(te != null)te.register();
    }
    
    @Override
    @Nullable
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }

    public void registerSpecialRenderer(){
        
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    public boolean hasSubBlocks(){
        return false;
    }
}
