package com.fuzhudada.error.item.wand;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ZhiLiaoWand extends com.somebody.idlframewok.item.ItemBase implements IWand{
    public ZhiLiaoWand(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn))==0
                && mana(3300,200,playerIn) && !playerIn.isPotionActive(MobEffects.POISON)) {
            ItemStack stack=playerIn.getHeldItem(handIn);
            int amount=30;
            if (stack.getTagCompound() != null && stack.getTagCompound().hasKey("amount")) {
                amount = stack.getTagCompound().getInteger("amount");
            }
            if(playerIn.getHealth()<playerIn.getMaxHealth()) playerIn.heal(amount);
            playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 200);
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
