package com.moffy5612.iinteg.block;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.moffy5612.iinteg.Reference;
import com.moffy5612.iinteg.block.tileentity.TileAdvancedCastingTable;
import com.moffy5612.iinteg.handler.BlockHandler;
import com.moffy5612.iinteg.handler.ItemHandler;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import slimeknights.tconstruct.shared.block.BlockTable;
import slimeknights.tconstruct.smeltery.block.BlockCasting;

public class AdvancedCastingTable extends BlockCasting{

    public static final String NAME = "advanced_casting_table";

    public AdvancedCastingTable() {
        super();
        this.setRegistryName(Reference.MOD_ID, NAME);
        this.setUnlocalizedName(Reference.MOD_ID+":"+NAME);
        this.setCreativeTab(Reference.MOD_CREATIVE_TAB);
        this.register();
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this, 1, 0));
    }

    public boolean register() {
        boolean foundAllMods = true;
        for(String id : this.getRequiredMods()){
            if(!Loader.isModLoaded(id))foundAllMods = false;
        }
        if(foundAllMods){
            BlockHandler.REG_BLOCKS.add(this);
            ItemHandler.REG_BLOCK_ITEMS.add(new ItemBlock(this).setRegistryName(Reference.MOD_ID, NAME));
            new TileAdvancedCastingTable().register();
        }
        return foundAllMods;
    }

    @Nonnull
    public Set<String> getRequiredMods() {
        Set<String>requiredMods = new HashSet<String>();
        requiredMods.add("tconstruct");
        return requiredMods;
    }

    @Override
    public RayTraceResult collisionRayTrace(IBlockState blockState, World worldIn, BlockPos pos, Vec3d start, Vec3d end) {
        ImmutableList<AxisAlignedBB> list = ImmutableList.of(
            new AxisAlignedBB(0, 0, 0, 1, 1, 1)
        );
        return BlockTable.raytraceMultiAABB(list, pos, start, end);
    }

    @Override
    @Nullable
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileAdvancedCastingTable();
    }
}
