package com.moffy5612.iinteg.block;

import com.moffy5612.iinteg.Reference;
import com.moffy5612.iinteg.block.tileentity.ModTileEntity;
import com.moffy5612.iinteg.handler.ModItemHandler;
import com.moffy5612.iinteg.handler.ModBlockHandler;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.Loader;

public class BlockMaterial extends ModBlockBase implements IModMultiTexturedBlock{

    public static final String NAME = "block_material";
    public static final String[] TYPES = {
        "etheric_machine_casing"
    };

    public BlockMaterial() {
        super(Material.IRON, NAME, null);
        
        this.setSoundType(SoundType.METAL);
        this.setResistance(10f);
        this.setHardness(2f);
        
        this.setHarvestLevel("pickaxe", 0);

        this.register();
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        if(itemIn.equals(Reference.MOD_CREATIVE_TAB)){
            for(int i = 0; i < TYPES.length; i++){
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public boolean register() {
        boolean foundAllMods = true;
        for(String id : this.getRequiredMods()){
            if(!Loader.isModLoaded(id))foundAllMods = false;
        }
        if(foundAllMods){
            ModBlockHandler.REG_BLOCKS.add(this);
            ItemBlock itemBlock = new ItemMaterialBlock(this);
            itemBlock.setRegistryName(Reference.MOD_ID, name);
            ModItemHandler.REG_BLOCK_ITEMS.add(itemBlock);
            ModTileEntity te = this.te;
            if(te != null)te.register();
        }
        return foundAllMods;
    }

    @Override
    public String[] getSubBlockNames() {
        return TYPES;
    }
    
    private class ItemMaterialBlock extends ItemBlock{

        public ItemMaterialBlock(Block block) {
            super(block);
            this.setHasSubtypes(true);
        }
        
        @Override
        public String getUnlocalizedName(ItemStack stack) {
            return "tile."+Reference.MOD_ID+":"+TYPES[stack.getMetadata()];
        }
    }
}
