package com.moffy5612.iinteg.block;

import java.util.ArrayList;
import java.util.List;

import com.moffy5612.iinteg.Reference;
import com.moffy5612.iinteg.block.states.ModMachineBlockState;
import com.moffy5612.iinteg.block.tileentity.ModTileEntityBase;
import com.moffy5612.iinteg.misc.ModTier;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

public abstract class ModMachineBlockBase extends ModBlockBase implements IModMultiTexturedBlock{

    public ModMachineBlockBase(String name, ModTileEntityBase te) {
        super(Material.IRON, name, te);

        this.setSoundType(SoundType.METAL);
        this.setResistance(10f);
        this.setHardness(2f);

        this.setHarvestLevel("pickaxe", 0);
    }

    @Override
    public void register() {
        ModelLoader.setCustomMeshDefinition(
            Item.getItemFromBlock(this), 
            stack -> new ModelResourceLocation(this.getRegistryName(), "type=")
            );
        super.register();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new ModMachineBlockState(this);
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        if(itemIn.equals(Reference.MOD_CREATIVE_TAB)){
            items.add(new ItemStack(this, 1, 0));
            items.add(new ItemStack(this, 1, 1));
            items.add(new ItemStack(this, 1, 2));
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
            ItemStack stack) {
        worldIn.setBlockState(
            pos, 
            state.withProperty(ModMachineBlockState.TIER_PROPERTY, ModTier.getTierFromIndex(stack.getMetadata())),
            2
        );
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(ModMachineBlockState.TIER_PROPERTY, ModTier.getTierFromIndex(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(ModMachineBlockState.TIER_PROPERTY).getIndex();
    }

    @Override
    public boolean hasSubBlocks() {
        return true;
    }

    @Override
    public String[] getSubBlockNames() {
        List<String> names = new ArrayList<String>();
        for(ModTier tier : ModTier.values()){
            names.add(name + "_" + tier.getName());
        }
        return names.toArray(new String[names.size()]);
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
	public boolean isOpaqueCube(IBlockState state){
		return false;
	}
}