package com.moffy5612.iinteg.registry;

import com.moffy5612.iinteg.handler.ModBlockHandler;
import com.moffy5612.iinteg.handler.ModItemHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class Register {
    @SubscribeEvent
    public void onBlockRegister(RegistryEvent.Register<Block> event){
        IForgeRegistry<Block> registry = event.getRegistry();
        for(BlockContainer block : ModBlockHandler.REG_BLOCKS){
            registry.register(block);
        }
    }

    @SubscribeEvent
    public void onItemRegister(RegistryEvent.Register<Item> event){
        IForgeRegistry<Item> registry = event.getRegistry();
        for(Item item : ModItemHandler.REG_ITEMS){
            registry.register(item);
        }
        for(Item item : ModItemHandler.REG_BLOCK_ITEMS){
            registry.register(item);
        }
    }
}
