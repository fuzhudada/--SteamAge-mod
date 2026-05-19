package com.fuzhudada.error.item.food;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemDrink extends ItemBlock {
    PotionEffect potionEffect;
    public int coolDown;

    public ItemDrink(Block block, PotionEffect potionEffect, int coolDown) {
        super(block);
        this.potionEffect=potionEffect;
        this.coolDown=coolDown;
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0 && !worldIn.isRemote){
            playerIn.addPotionEffect(new PotionEffect(this.potionEffect));
            playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(),coolDown);
            playerIn.getHeldItem(handIn).shrink(1);
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
        }
        return super.onItemRightClick(worldIn,playerIn,handIn);
    }
}
