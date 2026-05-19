package com.fuzhudada.error.item.shield;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemZuZhouShiBanDun extends ItemShieldBase {
    public ItemZuZhouShiBanDun(String name, int damage) {
        super(name, damage, SoundEvents.BLOCK_STONE_BREAK);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        playerIn.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 20, 0));
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
