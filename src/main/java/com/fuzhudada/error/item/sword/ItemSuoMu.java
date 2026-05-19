package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.item.wand.IWand;
import com.somebody.idlframewok.potion.ModPotions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemSuoMu extends com.somebody.idlframewok.item.ItemSwordBase implements skillLevel, IWand {
    public ItemSuoMu(String name, ToolMaterial material) {
        super(name, material);
    }

    public int getLevel(){
        return 3;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {

        if(!canUse(playerIn,handIn)){
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0
                && playerIn.getHeldItemOffhand().getItem() instanceof ItemSongMu
                && !playerIn.isPotionActive(MobEffects.POISON)
                && playerIn.isPotionActive(ModPotions.CEN_DIAN)) {
            playerIn.addPotionEffect(new PotionEffect(ModPotions.JIAN_REN_FENG_BAO,20,0));
            playerIn.addPotionEffect(new PotionEffect(MobEffects.SPEED,20,3));
            playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 210);
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_WITHER_SPAWN, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
