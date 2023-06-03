package com.moffy5612.iinteg.block;

import com.moffy5612.iinteg.block.tileentity.ModTileEntityBase;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

public abstract class ModTierBlockBase extends ModBlockBase{

    public ModTierBlockBase(Material material, String name, ModTileEntityBase te) {
        super(material, name, te);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockHorizontal.FACING, EnumFacing.NORTH));
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
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
            ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(BlockHorizontal.FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.getFront(meta);
        if(facing.getAxis() == EnumFacing.Axis.Y)facing = EnumFacing.NORTH;
        return this.getDefaultState().withProperty(BlockHorizontal.FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing)state.getValue(BlockHorizontal.FACING)).getIndex();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{BlockHorizontal.FACING});
    }
}