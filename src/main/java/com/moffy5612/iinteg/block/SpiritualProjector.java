package com.moffy5612.iinteg.block;

import javax.annotation.Nullable;

import com.moffy5612.iinteg.IInteg;
import com.moffy5612.iinteg.block.tileentity.TileSpiritualProjector;
import com.moffy5612.iinteg.capability.item.CapabilityCrystalBall;
import com.moffy5612.iinteg.client.gui.IIntegGuiList;
import com.moffy5612.iinteg.client.render.tileentity.SpiritualProjectorRenderer;
import com.moffy5612.iinteg.handler.ModItemHandler;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class SpiritualProjector extends ModBlockBase{
    
    public static final String NAME = "spiritual_projector";
    public SpiritualProjectorRenderer renderer;

    public SpiritualProjector(){
        super(Material.WOOD, NAME, new TileSpiritualProjector());
        this.setSoundType(SoundType.WOOD);
        this.setResistance(5f);
        this.setHardness(1f);

        this.renderer =  new SpiritualProjectorRenderer();

        this.register();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
            EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        ItemStack stack = playerIn.getHeldItem(hand);
        if(stack.getItem().equals(ModItemHandler.CRYSTAL_BALL)){
            CapabilityCrystalBall capabilityCrystalBall = stack.getCapability(CapabilityCrystalBall.CAPABILITY_CRYSTAL_BALL, null);
            if(capabilityCrystalBall != null){
                if(capabilityCrystalBall.hasOwner){
                    TileSpiritualProjector tsp = (TileSpiritualProjector)worldIn.getTileEntity(pos);
                    if(tsp != null){
                        tsp.inventory.insertItem(0, stack, false);
                        playerIn.setHeldItem(hand, ItemStack.EMPTY);
                        return true;
                    }
                }
            }
        }

        if(!playerIn.isSneaking()){
            playerIn.openGui(IInteg.INSTANCE, IIntegGuiList.GUI_SPIRITUAL_PROJECTOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }

        return false;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileSpiritualProjector tsp = (TileSpiritualProjector)worldIn.getTileEntity(pos);
        if(tsp != null){
            for(int i = 0; i < tsp.inventory.getSlots(); i++){
                ItemStack stack = tsp.inventory.getStackInSlot(i);
                EntityItem entityItem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ());
                entityItem.setItem(stack);
                worldIn.spawnEntity(entityItem);
            }
        }
    }

    @Override
    @Nullable
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileSpiritualProjector();
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public void registerSpecialRenderer() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileSpiritualProjector.class,renderer);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        AxisAlignedBB alignedBB = new AxisAlignedBB(0, 0, 0, 1, 11D / 16D, 1);
        return alignedBB;
    }
    
    @Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos){
		return false;
	}

    @Override
	public boolean isOpaqueCube(IBlockState state){
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state){
		return false;
	}
}
