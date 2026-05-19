package com.fuzhudada.error.item.gun;

import com.fuzhudada.error.item.Item.ItemBomb;
import com.fuzhudada.error.item.sword.ISteamWeapon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemLiuDanPao extends com.somebody.idlframewok.item.ItemBase implements ISteamWeapon,IGun {
    public static final float damage=185;

    public ItemLiuDanPao(String name) {
        super(name);
        this.setMaxStackSize(1);
        this.setMaxDamage(200);
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0){
            int j = bulletBomb(playerIn,0,1,2);

            if(j != -1 && ISteamWeapon.shrinkSteam(playerIn.getHeldItem(handIn),2.5F)) {
                int type =((ItemBomb)getItemStackFromIndex(playerIn,j).getItem()).type;
                shootBomb(worldIn,playerIn,handIn,type,damage);
                shrinkBullet(playerIn,playerIn.getHeldItem(handIn), j, 1);
                playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 60);
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
        }
        return ActionResult.newResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    @Override
    public float maxItemSteam() {
        return 100;
    }

    @Override
    public float getDamage() {
        return damage;
    }
}
