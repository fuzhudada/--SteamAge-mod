package com.fuzhudada.error.item.gun;

import com.fuzhudada.error.item.bow.ItemShiZiNu;
import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemNewYearKuiShe extends ItemDanJiaQiang implements IGun {
    public ItemNewYearKuiShe(String name , int ziDanShu , float damage , int coolDown) {
        super(name , ziDanShu , damage , coolDown);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        ItemStack stack=playerIn.getHeldItem(handIn);
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(stack) ==0) {
            if(stack.getItemDamage()<stack.getMaxDamage()) {
                shoot(worldIn,playerIn,handIn,stack.getItemDamage()+1==stack.getMaxDamage()?9:8,damage);
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, stack.getItemDamage()+1==stack.getMaxDamage()? SoundEvents.ENTITY_SLIME_ATTACK : ModSoundHandler.GUN4, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                stack.setItemDamage(stack.getItemDamage() + 1);
                if(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemShiZiNu){
                    playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem(), 20);
                }else if(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemShiZiNu){
                    playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(EnumHand.MAIN_HAND).getItem(), 20);
                }
                return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }else{
                int j=bullet(playerIn,3,0);
                if(j != -1) {
                    shrinkBullet(playerIn,stack, j, 6);
                    stack.setItemDamage(0);
                    playerIn.getCooldownTracker().setCooldown(stack.getItem(), coolDown);
                    worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundHandler.GUN5, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                    return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
                }
            }
        }
        return ActionResult.newResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
