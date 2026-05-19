package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.item.XunXiang.ItemXunXiang;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemXiangLuJian extends com.somebody.idlframewok.item.ItemSwordBase implements skillLevel {
    public ItemXiangLuJian(String name, ToolMaterial material) {
        super(name, material);
    }

    public int getLevel(){
        return 3;
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {

        if(!canUse(playerIn,handIn)){
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0){
            ItemStack XunXiang=playerIn.getHeldItemOffhand();
            if(XunXiang.getItem() instanceof ItemXunXiang && XunXiang.getItemDamage()==0){
                XunXiang.setItemDamage(1);
                return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
