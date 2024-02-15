package com.moffy5612.iinteg.integration.tconstruct.event;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class TConstrustEvent {
    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent event){
        EntityPlayer player = event.getEntityPlayer();
        if (player.world.isRemote)return;
        
        if(TinkerUtil.hasTrait(player.getHeldItemMainhand().getTagCompound(), "tconevo.omnipotence")){
            if(event.getTarget() instanceof MultiPartEntityPart){
                IEntityMultiPart parent = ((MultiPartEntityPart)event.getTarget()).parent;
                if(parent instanceof EntityLivingBase){
                    EntityLivingBase target = (EntityLivingBase)parent;
                    target.setHealth(0);
                    target.onDeath(new EntityDamageSource("infinity", player));
                }
            }
        }
    }
}
