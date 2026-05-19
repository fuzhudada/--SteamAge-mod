package com.fuzhudada.error.item.wand;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.entity.projectiles.EntityGeneralProjectiles;
import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class MengDuFaZhang extends com.somebody.idlframewok.item.ItemBase implements IWand{
    public MengDuFaZhang(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0
                && mana(600,202,playerIn)) {
            EntityGeneralProjectiles.shoot(worldIn,playerIn, ModDamageSource.EnumModDamageSource.ManaDamage,35,600,"happyVillager",true,2,true,true,
                    (x,y) -> {
                        if(y != null && y instanceof EntityLivingBase){
                            ((EntityLivingBase)y).addPotionEffect(new PotionEffect(MobEffects.POISON,60,1));
                        }
                    });
            playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 200);
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundHandler.WAND_POISON , SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
