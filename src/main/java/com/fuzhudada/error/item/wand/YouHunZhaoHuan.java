package com.fuzhudada.error.item.wand;

import com.fuzhudada.error.entity.weapon.EntityHuoLong;
import com.fuzhudada.error.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class YouHunZhaoHuan extends com.somebody.idlframewok.item.ItemBase implements IWand{
    public int mana;
    public int coolDown;
    SoundEvent sound;
    public int coolDown1;
    public YouHunZhaoHuan(String name,int mana,int coolDown,SoundEvent sound,int coolDown1) {
        super(name);
        this.setMaxStackSize(1);
        this.mana=mana;
        this.coolDown=coolDown;
        this.sound=sound;
        this.coolDown1=coolDown1;
    }

    public YouHunZhaoHuan(String name) {
        this(name,3300,100,SoundEvents.ENTITY_ENDERMEN_SCREAM,100);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0
          && mana(mana,coolDown1,playerIn)
          && !playerIn.isPotionActive(MobEffects.POISON)) {
            playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), coolDown);
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, sound, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);

            if(playerIn.getHeldItem(handIn).getItem() == ModItems.SPARKLING_WAND){
                EntityHuoLong.spawnParticle(playerIn.posX,playerIn.posY,playerIn.posZ,1,worldIn);
            }
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
