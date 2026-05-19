package com.fuzhudada.error.item.gun;

import com.fuzhudada.error.item.bow.ItemShiZiNu;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemPoJiaQiang extends com.somebody.idlframewok.item.ItemBase implements IGun {
    public ItemPoJiaQiang(String name) {
        super(name);
        this.setMaxDamage(500);
        this.setMaxStackSize(1);
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0){
            int j=bullet(playerIn,2,0,2);
            if(j != -1) {
                int type = ItemBullet.GetBulletType(getItemStackFromIndex(playerIn,j).getItem());
                if (type == 0) {
                    if (com.fuzhudada.error.enchantments.ModEnchantmentInit.XIN_SHOU_JIAO_CHENG.getEnchantmentLevel(playerIn.getHeldItem(handIn)) > 0) {
                        shoot(worldIn,playerIn,handIn,0,3);
                    } else {
                        shoot(worldIn,playerIn,handIn,0,30);
                    }
                }
                if (type == 2) {
                    if (com.fuzhudada.error.enchantments.ModEnchantmentInit.XIN_SHOU_JIAO_CHENG.getEnchantmentLevel(playerIn.getHeldItem(handIn)) > 0) {
                        shoot(worldIn,playerIn,handIn,2,2);
                    } else {
                        shoot(worldIn,playerIn,handIn,2,20);
                    }
                }
                shrinkBullet(playerIn,playerIn.getHeldItem(handIn),j,1);
                playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 20);
                if(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemShiZiNu){
                    playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem(), 20);
                }else if(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemShiZiNu){
                    playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(EnumHand.MAIN_HAND).getItem(), 20);
                }
                return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
        }
        return ActionResult.newResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    @Override
    public float getDamage() {
        return 30;
    }
}
