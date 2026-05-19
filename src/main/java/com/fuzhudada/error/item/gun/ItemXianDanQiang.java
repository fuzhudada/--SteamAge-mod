package com.fuzhudada.error.item.gun;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemXianDanQiang extends com.somebody.idlframewok.item.ItemBase implements IGun {
    float damage;
    int level;

    public ItemXianDanQiang(String name,float damage,int level) {
        super(name);
        this.damage=damage;
        this.level=level;
        this.setMaxStackSize(1);
        this.setMaxDamage(1);
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0){
            if(playerIn.getHeldItem(handIn).getItemDamage()==0) {
                Vec3d F = playerIn.getLookVec();
                if (F.x * F.x + F.z * F.z != 0) {
                    playerIn.knockBack(playerIn, 0.5f, F.x, F.z);
                }
                shoot(worldIn,playerIn,handIn,4,damage);
                playerIn.getHeldItem(handIn).setItemDamage(1);
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 0.5F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }else{
                int j=bullet(playerIn,3,4);
                if(j != -1) {
                    shrinkBullet(playerIn,playerIn.getHeldItem(handIn),j,1);
                    playerIn.getHeldItem(handIn).setItemDamage(0);
                    playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 60);
                    worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.UI_BUTTON_CLICK, SoundCategory.PLAYERS, 2.0F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                    return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
                }
            }
        }
        return ActionResult.newResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    @Override
    public float getDamage() {
        return damage;
    }
}
