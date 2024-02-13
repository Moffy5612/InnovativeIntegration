package com.moffy5612.iinteg.integration.tconstruct.modifier;

import com.moffy5612.iinteg.Reference;

import c4.conarm.lib.utils.RecipeMatchHolder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.ISocketableCapability;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.common.core.handler.PlayerDataHandler;
import vazkii.psi.common.item.ItemCAD;

public class ModifierPsionic extends ModArmorModifierBase{
    public static final String NAME = Reference.MOD_ID+".psionic";

    public ModifierPsionic(){
        super(NAME, 0x5752CC);
        RecipeMatchHolder.addItem(this, "gemPsi", 1, 1);

        this.register();
    }
    
    @SuppressWarnings("null")
    @Override
    public boolean canApplyCustom(ItemStack stack) {
        return super.canApplyCustom(stack) && (EntityLiving.getSlotForItemStack(stack) == EntityEquipmentSlot.CHEST
            || EntityLiving.getSlotForItemStack(stack) == EntityEquipmentSlot.LEGS
            || EntityLiving.getSlotForItemStack(stack) == EntityEquipmentSlot.FEET);
    }

    @SuppressWarnings("null")
    @Override
    public void onArmorTick(ItemStack tool, World world, EntityPlayer player) {
        if(EntityLiving.getSlotForItemStack(tool) == EntityEquipmentSlot.LEGS)cast(player, tool);
    }

    @SuppressWarnings("null")
    @Override
    public void onJumping(ItemStack armor, EntityPlayer player, LivingJumpEvent evt) {
        if(EntityLiving.getSlotForItemStack(armor) == EntityEquipmentSlot.FEET)cast(player, armor);
    }

    @SuppressWarnings("null")
    @Override
    public float onDamaged(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage,
            LivingDamageEvent evt) {
        if(EntityLiving.getSlotForItemStack(armor) == EntityEquipmentSlot.CHEST)cast(player, armor, source.getTrueSource(), damage);
        return super.onDamaged(armor, player, source, damage, newDamage, evt);
    }

    private void cast(EntityPlayer player, ItemStack tool){
        EntityPlayer pl = (EntityPlayer) player;
        PlayerDataHandler.PlayerData data = PlayerDataHandler.get(pl);
        ItemStack playerCad = PsiAPI.getPlayerCAD(pl);

        if (!playerCad.isEmpty()) {
            ItemStack bullet = ISocketableCapability.socketable(tool).getBulletInSocket(ISocketableCapability.socketable(tool).getSelectedSlot());
            ItemCAD.cast(player.getEntityWorld(), pl, data, bullet, playerCad, 5, 10, 0.05F, (SpellContext context) -> {
                context.tool = tool;
            });
        }
    }

    private void cast(EntityPlayer player, ItemStack tool, Entity attackingEntity, float damage){
        EntityPlayer pl = (EntityPlayer) player;
        PlayerDataHandler.PlayerData data = PlayerDataHandler.get(pl);
        ItemStack playerCad = PsiAPI.getPlayerCAD(pl);

        if (!playerCad.isEmpty()) {
            ItemStack bullet = ISocketableCapability.socketable(tool).getBulletInSocket(ISocketableCapability.socketable(tool).getSelectedSlot());
            ItemCAD.cast(player.getEntityWorld(), pl, data, bullet, playerCad, 5, 10, 0.05F, (SpellContext context) -> {
                context.tool = tool;
                if(attackingEntity instanceof EntityLiving)context.attackingEntity = (EntityLiving)attackingEntity;
                context.damageTaken = damage;
            });
        }
    }
}
