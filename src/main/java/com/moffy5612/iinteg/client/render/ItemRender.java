package com.moffy5612.iinteg.client.render;

import com.moffy5612.iinteg.Reference;
import com.moffy5612.iinteg.handler.ItemHandler;
import com.moffy5612.iinteg.item.IModMultiTexturedItem;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRender {
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event) {
        for(Item item : ItemHandler.REG_ITEMS){
            if(item instanceof IModMultiTexturedItem){
                String[]names = ((IModMultiTexturedItem)item).getSubItemNames();
                for(int i = 0; i < names.length; i++){
                    register(item, names[i], i);
                }
            }else{
                register(item, 0);
            }
        }

        for (Item item : ItemHandler.REG_BLOCK_ITEMS){
            if(item instanceof IModMultiTexturedItem){
                String[]names = ((IModMultiTexturedItem)item).getSubItemNames();
                for(int i = 0; i < names.length; i++){
                    register(item, names[i], i);
                }
            }else{
                register(item, 0);
            }
        }
    }

    private static void register(Item item, int meta){
        ResourceLocation rl = item.getRegistryName();
        ModelResourceLocation mrl = new ModelResourceLocation(rl, "inventory");
        ModelLoader.setCustomModelResourceLocation(item, meta, mrl);
    }

    private static void register(Item item, String name, int meta){
        ResourceLocation rl = new ResourceLocation(Reference.MOD_ID, name);
        ModelResourceLocation mrl = new ModelResourceLocation(rl, "inventory");
        ModelLoader.setCustomModelResourceLocation(item, meta, mrl);
    }
}
