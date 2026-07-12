package com.fuzhudada.error.item.gun;

import com.fuzhudada.error.item.bow.ItemShiZiNu;
import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemYellowKuiShe extends ItemDanJiaQiang {
    public ItemYellowKuiShe(String name, int ziDanShu, float damage, int coolDown) {
        super(name, ziDanShu, damage, coolDown);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(stack) == 0 && onGunUse(worldIn, playerIn, handIn)) {
            int j = bullet(playerIn, 3, 0, 3);
            if(j != -1) {
                int type = ItemBullet.GetBulletType(getItemStackFromIndex(playerIn, j).getItem());
                shoot(worldIn, playerIn, handIn, type, damage);
                stack.setItemDamage(stack.getItemDamage() + 1);
                if(stack.getItemDamage() >= stack.getMaxDamage()) {
                    shrinkBullet(playerIn, stack, j, 1);
                    stack.setItemDamage(0);
                    worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, getReloadSound(), SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                    playerIn.getCooldownTracker().setCooldown(stack.getItem(), coolDown);
                } else {
                    worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, getShootSound(), SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                }
                if(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemShiZiNu) {
                    playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem(), 20);
                }
            }
            return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return ActionResult.newResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    @Override
    public net.minecraft.util.SoundEvent getShootSound() {
        return ModSoundHandler.GUN4;
    }

    @Override
    public net.minecraft.util.SoundEvent getReloadSound() {
        return ModSoundHandler.GUN5;
    }
}
