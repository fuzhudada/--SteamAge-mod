package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.enchantments.ModEnchantmentInit;
import com.fuzhudada.error.item.Item.ItemZhenQiGuanZi;
import com.fuzhudada.error.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ISteamWeapon {
    float maxItemSteam();

    static float getSteam(ItemStack stack){
        if(stack.getItem() instanceof ISteamWeapon){
            float k1=stack.getItemDamage();
            float k2=stack.getMaxDamage();
            float k3=((ISteamWeapon)stack.getItem()).maxItemSteam();
            return (k2-k1)*k3/k2;
        }else{
            return -1;
        }
    }

    static void setSteam(ItemStack stack,float steam){
        if(stack.getItem() instanceof ISteamWeapon){
            float k1=stack.getMaxDamage();
            float k2=((ISteamWeapon)stack.getItem()).maxItemSteam();
            stack.setItemDamage((int)Math.ceil(k1-steam*k1/k2));
        }
    }

    static boolean shrinkSteam(ItemStack stack,float steam){
        int k= ModEnchantmentInit.CHONG_NENG.getEnchantmentLevel(stack);
        steam*=1-0.1*k;
        if(getSteam(stack)>=steam){
            setSteam(stack,getSteam(stack)-steam);
            return true;
        }
        return false;
    }

    static int checkSteamOnPlayer(EntityPlayer playerIn){
        int s=0;
        for (int i = 0; i < playerIn.inventory.getSizeInventory(); ++i) {
            ItemStack stack1 = playerIn.inventory.getStackInSlot(i);
            if (stack1.getItem() instanceof ItemZhenQiGuanZi) {
                s += ISteamWeapon.getSteam(stack1);
            }
        }
        return s;
    }

    static float consumeSteamOnPlayer(EntityPlayer playerIn,float s){
        float s0=s;
        for (int i = 0; i < playerIn.inventory.getSizeInventory(); ++i) {
            ItemStack stack1 = playerIn.inventory.getStackInSlot(i);
            if (stack1.getItem() instanceof ItemZhenQiGuanZi) {
                float k3 = ISteamWeapon.getSteam(stack1);
                if (s < k3) {
                    ISteamWeapon.setSteam(stack1 , k3 - s);
                    return s;
                } else {
                    stack1.shrink(1);
                    if (!playerIn.inventory.addItemStackToInventory(new ItemStack(ModItems.KONG_GUAN_ZI))) {
                        playerIn.dropItem(new ItemStack(ModItems.KONG_GUAN_ZI) , false);
                    }
                    s -= k3;
                }
            }
        }
        return s0-s;
    }
}
