package com.fuzhudada.error.item.gun;

import com.fuzhudada.error.item.bow.ItemShiZiNu;
import com.fuzhudada.error.item.sword.ISteamWeapon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemLianFaZhenQiBuQiang extends com.somebody.idlframewok.item.ItemBase implements IGun, ISteamWeapon {
    public ItemLianFaZhenQiBuQiang(String name) {
        super(name);
        this.setMaxDamage(200);
        this.setMaxStackSize(1);
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0){
            int j=bullet(playerIn,2,0,1);

            if(j != -1){
                int type = ItemBullet.GetBulletType(getItemStackFromIndex(playerIn,j).getItem());
                int Cooldown=10;

                if(type == 0) {
                    if(com.fuzhudada.error.enchantments.ModEnchantmentInit.XIN_SHOU_JIAO_CHENG.getEnchantmentLevel(playerIn.getHeldItem(handIn))>0){
                        shoot(worldIn,playerIn,handIn,0,2);
                    }else{
                        shoot(worldIn,playerIn,handIn,0,27);
                    }
                }else {
                    if(com.fuzhudada.error.enchantments.ModEnchantmentInit.XIN_SHOU_JIAO_CHENG.getEnchantmentLevel(playerIn.getHeldItem(handIn))>0){
                        shoot(worldIn,playerIn,handIn,1,3);
                    }else{
                        shoot(worldIn,playerIn,handIn,1,48);
                    }
                    Cooldown = 25;
                }

                shrinkBullet(playerIn,playerIn.getHeldItem(handIn),j,1);
                if(ISteamWeapon.shrinkSteam(playerIn.getHeldItem(handIn),0.25F)) {
                    for(int a=0;a<10;a++) {
                        playerIn.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, playerIn.posX + Math.random() - 0.5, playerIn.posY + 1, playerIn.posZ + Math.random() - 0.5, 0, 0.2, 0);
                    }
                    playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), Cooldown);
                }else{
                    playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 2*Cooldown);
                }
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
    public float maxItemSteam() {
        return 50;
    }

    @Override
    public float getDamage() {
        return 20;
    }
}
