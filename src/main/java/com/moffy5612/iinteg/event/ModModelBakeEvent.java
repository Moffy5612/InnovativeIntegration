package com.moffy5612.iinteg.event;

import com.moffy5612.iinteg.Reference;
import com.moffy5612.iinteg.handler.ModBlockHandler;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.shared.client.BakedTableModel;

public class ModModelBakeEvent {
    @SubscribeEvent
    public void onModelBake(ModelBakeEvent event){
        if(ModBlockHandler.REG_BLOCKS.contains(ModBlockHandler.ETHERIC_CASTING_TABLE)){
            ResourceLocation ethericCastingTableRL = new ResourceLocation(Reference.MOD_ID, "etheric_casting_table");
            ModelResourceLocation ethericCastingTableMRL = new ModelResourceLocation(ethericCastingTableRL, "type=table");
            IBakedModel model = event.getModelRegistry().getObject(ethericCastingTableMRL);
            if(model != null){
                event.getModelRegistry().putObject(ethericCastingTableMRL, new BakedTableModel(model, null, DefaultVertexFormats.BLOCK));
            }
        }
    }
}
