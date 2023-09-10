package com.moffy5612.iinteg.client.gui;

import javax.annotation.Nullable;

import com.moffy5612.iinteg.block.inventory.ContainerAdvancedToolForge;
import com.moffy5612.iinteg.block.inventory.ContainerAdvancedPartBuilder;
import com.moffy5612.iinteg.block.inventory.ContainerSpiritualProjector;
import com.moffy5612.iinteg.block.tileentity.TileAdvancedToolForge;
import com.moffy5612.iinteg.block.tileentity.TileAdvancedPartBuilder;
import com.moffy5612.iinteg.block.tileentity.TileSpiritualProjector;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class IIntegGuiHandler implements IGuiHandler{

    @Override
    @Nullable
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == IIntegGuiList.GUI_ADVANCED_PART_BUILDER){
            TileAdvancedPartBuilder trpb = (TileAdvancedPartBuilder)world.getTileEntity(new BlockPos(x, y, z));
            return new ContainerAdvancedPartBuilder(player.inventory, trpb);
        }else if(ID == IIntegGuiList.GUI_ADVANCED_TOOL_FORGE){
            TileAdvancedToolForge trf = (TileAdvancedToolForge)world.getTileEntity(new BlockPos(x, y, z));
            return new ContainerAdvancedToolForge(player.inventory, trf);
        }else if(ID == IIntegGuiList.GUI_SPIRITUAL_PROJECTOR){
            TileSpiritualProjector tsp = (TileSpiritualProjector)world.getTileEntity(new BlockPos(x, y, z));
            return new ContainerSpiritualProjector(player.inventory, tsp);
        }
        return null;
    }

    @Override
    @Nullable
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == IIntegGuiList.GUI_ADVANCED_PART_BUILDER){
            TileAdvancedPartBuilder trpb = (TileAdvancedPartBuilder)world.getTileEntity(new BlockPos(x, y, z));
            return new GuiAdvancedPartBuilder(player.inventory, trpb);
        }else if(ID == IIntegGuiList.GUI_ADVANCED_TOOL_FORGE){
            TileAdvancedToolForge trf = (TileAdvancedToolForge)world.getTileEntity(new BlockPos(x, y, z));
            return new GuiAdvancedToolForge(player.inventory, trf);
        }else if(ID == IIntegGuiList.GUI_SPIRITUAL_PROJECTOR){
            TileSpiritualProjector tsp = (TileSpiritualProjector)world.getTileEntity(new BlockPos(x, y, z));
            return new GuiSpiritualProjector(player.inventory, tsp);
        }
        return null;
    }
    
}
