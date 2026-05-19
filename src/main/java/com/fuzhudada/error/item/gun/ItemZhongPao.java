package com.fuzhudada.error.item.gun;

import com.fuzhudada.error.item.sword.ISteamWeapon;
import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemZhongPao extends com.somebody.idlframewok.item.ItemBase implements ISteamWeapon,IGun {
    public static final float damage=300;

    public ItemZhongPao(String name) {
        super(name);
        this.setMaxStackSize(1);
        this.setMaxDamage(200);
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0
        && playerIn.getHeldItem(handIn).getItemDamage()<playerIn.getHeldItem(handIn).getMaxDamage()){
            int j=bullet(playerIn,0,6);

            if(j != -1 && ISteamWeapon.shrinkSteam(playerIn.getHeldItem(handIn),5)) {
                shoot(worldIn,playerIn,handIn,6,damage);

                Vec3d D=playerIn.getLookVec().normalize();
                if (D.x * D.x + D.z * D.z != 0) {
                    playerIn.knockBack(playerIn, 0.5f, D.x, D.z);
                }
                shrinkBullet(playerIn,playerIn.getHeldItem(handIn), j, 1);
                playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 100);
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundHandler.GUN3, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
        }
        return ActionResult.newResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    @Override
    public float maxItemSteam() {
        return 200;
    }

    @Override
    public float getDamage() {
        return damage;
    }
}
