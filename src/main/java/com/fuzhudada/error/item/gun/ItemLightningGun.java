package com.fuzhudada.error.item.gun;

import com.fuzhudada.error.item.Item.ItemBomb;
import com.fuzhudada.error.item.bow.ItemShiZiNu;
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
import java.util.Arrays;

public class ItemLightningGun extends com.somebody.idlframewok.item.ItemBase implements ISteamWeapon,IGun {
    public static final float damage1=580;
    int level;
    int[] type;
    float[] damage;
    int[] cooldown;
    public ItemLightningGun(String name,int level,int[] type,float[] damage,int[] cooldown){
        super(name);
        this.level=level;
        this.type=type;
        this.damage=damage;
        this.cooldown=cooldown;
        this.setMaxStackSize(1);
        this.setMaxDamage(200);
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0){
            int j=bullet(playerIn,level,type);
            int j1 = bulletBomb(playerIn,3);
            if(j != -1 && (j1 == -1 || j<j1)) {
                int type = ItemBullet.GetBulletType(getItemStackFromIndex(playerIn,j).getItem());
                int index= Arrays.binarySearch(this.type,type);
                if(index != -1) {
                    shoot(worldIn, playerIn,handIn, type, damage[index]);
                    shrinkBullet(playerIn,playerIn.getHeldItem(handIn), j, 1);
                    playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), cooldown[index]);
                    if(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemShiZiNu){
                        playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem(), 20);
                    }else if(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemShiZiNu){
                        playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(EnumHand.MAIN_HAND).getItem(), 20);
                    }
                    return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
                }
            }else if(j1 != -1 && (j==-1 || j1<j)){
                if(ISteamWeapon.shrinkSteam(playerIn.getHeldItem(handIn),2.5F)) {
                    int type =((ItemBomb)getItemStackFromIndex(playerIn,j1).getItem()).type;
                    shootBomb(worldIn,playerIn,handIn,type,damage1);
                    shrinkBullet(playerIn,playerIn.getHeldItem(handIn), j1, 1);
                    playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 60);
                    worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                    return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
                }
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
        return damage[0];
    }
}
