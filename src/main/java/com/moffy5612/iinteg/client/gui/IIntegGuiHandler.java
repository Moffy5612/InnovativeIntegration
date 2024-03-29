package com.moffy5612.iinteg.client.gui;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;

import com.moffy5612.iinteg.block.inventory.ContainerAdvancedToolForge;
import com.moffy5612.iinteg.block.inventory.ContainerSpiritualGenerator;
import com.moffy5612.iinteg.block.inventory.ContainerAdvancedPartBuilder;
import com.moffy5612.iinteg.block.inventory.ContainerAdvancedProjector;
import com.moffy5612.iinteg.block.inventory.ContainerSpiritualProjector;
import com.moffy5612.iinteg.block.tileentity.TileAdvancedToolForge;
import com.moffy5612.iinteg.block.tileentity.TileSpiritualGenerator;
import com.moffy5612.iinteg.block.tileentity.TileAdvancedPartBuilder;
import com.moffy5612.iinteg.block.tileentity.TileAdvancedProjector;
import com.moffy5612.iinteg.block.tileentity.TileSpiritualProjector;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class IIntegGuiHandler implements IGuiHandler{

    public final Set<ModuleContainerPair> REG_MODULE_GUI = new HashSet<>();

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
        }else if(ID == IIntegGuiList.GUI_SPIRITUAL_GENERATOR){
            TileSpiritualGenerator tsg = (TileSpiritualGenerator)world.getTileEntity(new BlockPos(x, y, z));
            return new ContainerSpiritualGenerator(player.inventory, tsg);
        }else if(ID == IIntegGuiList.GUI_ADVANCED_PROJECTOR){
            TileAdvancedProjector tap = (TileAdvancedProjector)world.getTileEntity(new BlockPos(x, y, z));
            return new ContainerAdvancedProjector(player.inventory, tap);
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
        }else if(ID == IIntegGuiList.GUI_SPIRITUAL_GENERATOR){
            TileSpiritualGenerator tsg = (TileSpiritualGenerator)world.getTileEntity(new BlockPos(x, y, z));
            return new GuiSpiritualGenerator(player.inventory, tsg);
        }else if(ID == IIntegGuiList.GUI_ADVANCED_PROJECTOR){
            TileAdvancedProjector tap = (TileAdvancedProjector)world.getTileEntity(new BlockPos(x, y, z));
            return new GuiAdvancedProjector(player.inventory, tap);
        }
        return null;
    }
    
    public void addModuleGui(int id, Class<? extends GuiContainer> guiContainerClass, Class<? extends Container> containerClass){
        REG_MODULE_GUI.add(new ModuleContainerPair(id, guiContainerClass, containerClass));
    }
}
