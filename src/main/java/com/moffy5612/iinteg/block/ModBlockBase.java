package com.moffy5612.iinteg.block;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.moffy5612.iinteg.Reference;
import com.moffy5612.iinteg.block.tileentity.ModTileEntity;
import com.moffy5612.iinteg.handler.ModBlockHandler;
import com.moffy5612.iinteg.handler.ModItemHandler;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;

public class ModBlockBase extends BlockContainer{

    public String name;

    @Nullable
    public ModTileEntity te;

    public ModBlockBase(Material material, String name, ModTileEntity te){
        super(material);
        this.setRegistryName(Reference.MOD_ID, name);
        this.setUnlocalizedName(Reference.MOD_ID+":"+name);
        this.setCreativeTab(Reference.MOD_CREATIVE_TAB);
        this.name = name;
        this.te = te;
    }

    public boolean register(){
        boolean foundAllMods = true;
        for(String id : this.getRequiredMods()){
            if(!Loader.isModLoaded(id))foundAllMods = false;
        }
        if(foundAllMods){
            ModBlockHandler.REG_BLOCKS.add(this);
            ItemBlock itemBlock = new ItemBlock(this);
            itemBlock.setRegistryName(Reference.MOD_ID, name);
            ModItemHandler.REG_BLOCK_ITEMS.add(itemBlock);
            ModTileEntity te = this.te;
            if(te != null)te.register();
        }
        return foundAllMods;
    }

    @Nonnull
    public Set<String> getRequiredMods() {
        return new HashSet<>();
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
}
