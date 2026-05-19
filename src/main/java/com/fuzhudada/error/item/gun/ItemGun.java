package com.fuzhudada.error.item.gun;

import com.fuzhudada.error.item.bow.ItemShiZiNu;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class ItemGun extends com.somebody.idlframewok.item.ItemBase implements IGun {
    int level;
    int[] type;
    float[] damage;
    int[] cooldown;
    public ItemGun(String name,int level,int[] type,float[] damage,int[] cooldown){
        super(name);
        this.level=level;
        this.type=type;
        this.damage=damage;
        this.cooldown=cooldown;
        this.setMaxStackSize(1);
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0){
            int j=bullet(playerIn,level,type);
            if(j != -1) {
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
            }
        }
        return ActionResult.newResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    @Override
    public float getDamage() {
        return damage[0];
    }
}
