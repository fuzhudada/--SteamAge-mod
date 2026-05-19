package com.fuzhudada.error.item.armor;

import com.fuzhudada.error.item.wand.MoLiJuJiQi;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemHuDun extends com.somebody.idlframewok.item.ItemArmorBase {
    public ItemHuDun(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, renderIndexIn, equipmentSlotIn);
        com.somebody.idlframewok.util.CommonFunctions.addToEventBus(this);
    }

    @SubscribeEvent
    public void E(LivingHurtEvent event)
    {
        World world = event.getEntityLiving().world;
        EntityLivingBase H = event.getEntityLiving();
        if (!world.isRemote && H instanceof EntityPlayer)
        {
            EntityPlayer playerIn = (EntityPlayer)H;
            int j = -1;
            for (int i = 0; i < playerIn.inventory.getSizeInventory(); ++i) {
                ItemStack itemstack = playerIn.inventory.getStackInSlot(i);

                if (itemstack.getItem() instanceof MoLiJuJiQi) {
                    j = i;
                    break;
                }
            }
            if(H.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == this && j>-1 &&
                    playerIn.inventory.getStackInSlot(j).getItemDamage()<=playerIn.inventory.getStackInSlot(j).getMaxDamage()-180)
            {
                event.setAmount(event.getAmount() - 6);
                playerIn.inventory.getStackInSlot(j).setItemDamage(playerIn.inventory.getStackInSlot(j).getItemDamage()+180);
                playerIn.getCooldownTracker().setCooldown(playerIn.inventory.getStackInSlot(j).getItem(), 22);
                world.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
            }
        }
    }
}
