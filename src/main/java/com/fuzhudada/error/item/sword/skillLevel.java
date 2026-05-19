package com.fuzhudada.error.item.sword;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;

import javax.annotation.Nonnull;

public interface skillLevel {
    int getLevel();

    default boolean canUse(EntityPlayer playerIn, @Nonnull EnumHand handIn){
        return handIn.equals(EnumHand.MAIN_HAND);
//        int max=0;
//        ItemStack is=null;
//
//        for (int i = 8; i >=0; i--) {
//            ItemStack itemstack = playerIn.inventory.getStackInSlot(i);
//            if(itemstack.getItem() instanceof skillLevel){
//                skillLevel sl=(skillLevel) itemstack.getItem();
//                if(sl.getLevel()>max) {
//                    max = sl.getLevel();
//                }
//            }
//        }
//
//        for(int i = 8; i >=0; i--){
//            ItemStack itemstack = playerIn.inventory.getStackInSlot(i);
//            if(itemstack.getItem() instanceof skillLevel){
//                skillLevel sl=(skillLevel) itemstack.getItem();
//                if(sl.getLevel()>=max-1) {
//                    is=itemstack;
//                }
//            }
//        }
//
//        ItemStack is1=playerIn.getHeldItem(handIn);
//        if(is1.getItem() instanceof skillLevel){
//            skillLevel is2=(skillLevel) is1.getItem();
//            return is1==is || is2.getLevel()<max-1;
//        }else{
//            return is1==is;
//        }
    }
}
