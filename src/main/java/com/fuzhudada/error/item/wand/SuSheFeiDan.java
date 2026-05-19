package com.fuzhudada.error.item.wand;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.enchantments.ModEnchantmentInit;
import com.fuzhudada.error.entity.projectiles.EntityGeneralProjectiles;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class SuSheFeiDan extends com.somebody.idlframewok.item.ItemBase implements IWand{
    public SuSheFeiDan(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0
                && mana(90,12,playerIn)) {
            EntityGeneralProjectiles.shoot(worldIn,playerIn, ModDamageSource.EnumModDamageSource.ManaDamage,ModEnchantmentInit.XIN_SHOU_JIAO_CHENG.getEnchantmentLevel(playerIn.getHeldItem(handIn))>0?2:18,"cloud");
            playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 10);
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
