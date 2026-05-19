package com.fuzhudada.error.item.gun;

import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemGatling extends ItemDanJiaQiang{
    public ItemGatling(String name , int ziDanShu , float damage , int coolDown) {
        super(name , ziDanShu , damage , coolDown);
    }

    public SoundEvent getShootSound(){
        return ModSoundHandler.GUN6;
    }

    @Override
    public void onUpdate(@Nonnull ItemStack stack , @Nonnull World worldIn , @Nonnull Entity entityIn , int itemSlot , boolean isSelected) {
        if(itemSlot==40 && entityIn instanceof EntityLivingBase){
            ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,20,10));
            ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS,20,300));
        }
        super.onUpdate(stack , worldIn , entityIn , itemSlot , isSelected);
    }

    public boolean onGunUse(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn){
        return handIn==EnumHand.MAIN_HAND;
    }

    @Override
    public int getShootType() {
        return 10;
    }
}
