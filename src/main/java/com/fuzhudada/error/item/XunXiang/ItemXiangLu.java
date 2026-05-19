package com.fuzhudada.error.item.XunXiang;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class ItemXiangLu extends com.somebody.idlframewok.item.ItemBase {
    public int[] canUse;

    public ItemXiangLu(String name,int[] canUse ) {
        super(name);
        this.setMaxStackSize(1);
        this.canUse=canUse;
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0){
            ItemStack XunXiang=playerIn.getHeldItemOffhand();
            if(XunXiang.getItem() instanceof ItemXunXiang && XunXiang.getItemDamage()==0){
                int id = ((ItemXunXiang)XunXiang.getItem()).getId();
                if(Arrays.binarySearch(canUse,id)>-1) {
                    XunXiang.setItemDamage(1);
                    return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
                }
            }
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
