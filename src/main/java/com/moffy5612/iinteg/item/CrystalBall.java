package com.moffy5612.iinteg.item;

import java.util.List;

import javax.annotation.Nullable;

import com.moffy5612.iinteg.IInteg;
import com.moffy5612.iinteg.Reference;
import com.moffy5612.iinteg.capability.ModCapabilityProvider;
import com.moffy5612.iinteg.capability.item.CapabilityCrystalBall;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class CrystalBall extends ModItemBase{
    public static final String NAME = "crystal_ball";
    public static final String[] TYPES = {
        "crystal_ball",
    };

    public CrystalBall(){
        super(NAME);
        this.setMaxDamage(0);
        this.setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(stack.hasCapability(CapabilityCrystalBall.CAPABILITY_CRYSTAL_BALL, null)){
            CapabilityCrystalBall capabilityCrystalBall = stack.getCapability(CapabilityCrystalBall.CAPABILITY_CRYSTAL_BALL, null);
            if(capabilityCrystalBall != null){
                if(!capabilityCrystalBall.hasOwner){
                    IInteg.proxy.addMultiLineDescription(
                        tooltip, 
                        "item."+Reference.MOD_ID+":"+NAME+".desc",
                        new Style().setColor(TextFormatting.DARK_GRAY)
                    );
                }else{
                    IInteg.proxy.addMultiLineDescription(
                        tooltip, 
                        "item."+Reference.MOD_ID+":"+NAME+"_synchronized.desc",
                        new Style().setColor(TextFormatting.DARK_GRAY)
                    );
                    tooltip.set(tooltip.size() - 1, tooltip.get(tooltip.size() - 1) + capabilityCrystalBall.ownerName);
                }
            }
            
        };
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if(stack.hasCapability(CapabilityCrystalBall.CAPABILITY_CRYSTAL_BALL, null)){
            CapabilityCrystalBall capabilityCrystalBall = stack.getCapability(CapabilityCrystalBall.CAPABILITY_CRYSTAL_BALL, null);
            if(capabilityCrystalBall != null){
                capabilityCrystalBall.hasOwner = true;
                capabilityCrystalBall.ownerName = playerIn.getDisplayNameString();
                ;
            }
        };
        return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.inventory.getCurrentItem());
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        if(stack.hasCapability(CapabilityCrystalBall.CAPABILITY_CRYSTAL_BALL, null)){
            CapabilityCrystalBall capabilityCrystalBall = stack.getCapability(CapabilityCrystalBall.CAPABILITY_CRYSTAL_BALL, null);
            if(capabilityCrystalBall != null){
                return capabilityCrystalBall.hasOwner;
            }
        };
        return false;
    }

    @Override
    @Nullable
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new ModCapabilityProvider(new CapabilityCrystalBall());
    }
}
