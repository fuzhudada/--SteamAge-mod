package com.fuzhudada.error.item.gun;

import com.fuzhudada.error.item.bow.ItemShiZiNu;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemSanFaBuQiang extends com.somebody.idlframewok.item.ItemBase implements IGun {
    public ItemSanFaBuQiang(String name) {
        super(name);
        this.setMaxDamage(2);
        this.setMaxStackSize(1);
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0){
            int j=bullet(playerIn,2,0,1,2);

            if(j != -1){
              int type = ItemBullet.GetBulletType(getItemStackFromIndex(playerIn,j).getItem());
              int Cooldown = 20;

              if(type == 0){
                  shoot(worldIn,playerIn,handIn,0,15);
              }
              if(type == 1){
                  shoot(worldIn,playerIn,handIn,1,26);
              }
              if(type == 2){
                  shoot(worldIn,playerIn,handIn,2,10);
              }
                if(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemShiZiNu){
                    playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem(), 20);
                }else if(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemShiZiNu){
                    playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(EnumHand.MAIN_HAND).getItem(), 20);
                }

                switch(playerIn.getHeldItem(handIn).getItemDamage()) {
                    case 1:
                        playerIn.getHeldItem(handIn).setItemDamage(2);
                        playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), Cooldown);
                        break;
                    case 2:
                        shrinkBullet(playerIn,playerIn.getHeldItem(handIn),j,1);
                        playerIn.getHeldItem(handIn).setItemDamage(0);
                        break;
                    case 0:
                        playerIn.getHeldItem(handIn).setItemDamage(1);
                        break;
                }
                return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
        }
        return ActionResult.newResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    @Override
    public float getDamage() {
        return 12;
    }
}
