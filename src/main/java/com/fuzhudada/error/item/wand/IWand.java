package com.fuzhudada.error.item.wand;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IWand{
    static boolean mana1(int mana, int coolDown, EntityPlayer playerIn){
        int j = -1;
        for (int i = 0; i < playerIn.inventory.getSizeInventory(); ++i) {
            ItemStack itemstack = playerIn.inventory.getStackInSlot(i);

            if (itemstack.getItem() instanceof MoLiJuJiQi) {
                j = i;
                break;
            }
        }

        if(j==-1 || playerIn.inventory.getStackInSlot(j).getItemDamage()>playerIn.inventory.getStackInSlot(j).getMaxDamage()-mana){
            return false;
        }

        playerIn.inventory.getStackInSlot(j).setItemDamage(playerIn.inventory.getStackInSlot(j).getItemDamage()+mana);
        playerIn.getCooldownTracker().setCooldown(playerIn.inventory.getStackInSlot(j).getItem(), coolDown);
        return true;
    }

    default boolean mana(int mana, int coolDown, EntityPlayer playerIn){
        int j = -1;
        for (int i = 0; i < playerIn.inventory.getSizeInventory(); ++i) {
            ItemStack itemstack = playerIn.inventory.getStackInSlot(i);

            if (itemstack.getItem() instanceof MoLiJuJiQi) {
                j = i;
                break;
            }
        }

        if(j==-1 || playerIn.inventory.getStackInSlot(j).getItemDamage()>playerIn.inventory.getStackInSlot(j).getMaxDamage()-mana){
            return false;
        }

        playerIn.inventory.getStackInSlot(j).setItemDamage(playerIn.inventory.getStackInSlot(j).getItemDamage()+mana);
        playerIn.getCooldownTracker().setCooldown(playerIn.inventory.getStackInSlot(j).getItem(), coolDown);
        return true;
    }

    static int getMana(EntityPlayer playerIn){
        int j = -1;
        for (int i = 0; i < playerIn.inventory.getSizeInventory(); ++i) {
            ItemStack itemstack = playerIn.inventory.getStackInSlot(i);

            if (itemstack.getItem() instanceof MoLiJuJiQi) {
                j = i;
                break;
            }
        }

        if(j==-1){
            return 0;
        }
        return playerIn.inventory.getStackInSlot(j).getMaxDamage()-playerIn.inventory.getStackInSlot(j).getItemDamage();
    }
}
