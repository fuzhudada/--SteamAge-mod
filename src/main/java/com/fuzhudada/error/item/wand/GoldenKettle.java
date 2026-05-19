package com.fuzhudada.error.item.wand;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GoldenKettle extends com.somebody.idlframewok.item.ItemBase {
    public GoldenKettle(String name) {
        super(name);
        this.setMaxStackSize(1);
        com.somebody.idlframewok.util.CommonFunctions.addToEventBus(this);
    }

    @SubscribeEvent
    public void E(LivingEvent.LivingUpdateEvent event) {
        World world = event.getEntity().world;
        if (!world.isRemote && event.getEntity() instanceof EntityLivingBase) {
            EntityLivingBase E = (EntityLivingBase) event.getEntity();
            if (E instanceof EntityPlayer) {
                EntityPlayer P = (EntityPlayer) E;
                int j = -1;
                for (int i = 0; i < P.inventory.getSizeInventory(); ++i) {
                    ItemStack itemstack = P.inventory.getStackInSlot(i);

                    if (itemstack.getItem() instanceof MoLiJuJiQi) {
                        j = i;
                        break;
                    }
                }
                ItemStack I;
                if (P.getHeldItemMainhand().getItem() == this) {
                    I = P.getHeldItemMainhand();
                } else {
                    if (P.getHeldItemOffhand().getItem() == this) {
                        I = P.getHeldItemOffhand();
                    } else {
                        I = null;
                    }
                }

                if (P.fallDistance > 0 && I != null
                        && com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(I) == 0
                        && P.fallDistance<5.0
                        && j>-1 && P.inventory.getStackInSlot(j).getItemDamage() < P.inventory.getStackInSlot(j).getMaxDamage() - 5) {
                    P.setNoGravity(true);
                    P.motionY = -0.5;
                    P.fallDistance = 0;
                    P.inventory.getStackInSlot(j).setItemDamage(P.inventory.getStackInSlot(j).getItemDamage() + 5);
                    P.getCooldownTracker().setCooldown(P.inventory.getStackInSlot(j).getItem(), 20);
                } else {
                    P.setNoGravity(false);
                }
            }
        }
    }
}
