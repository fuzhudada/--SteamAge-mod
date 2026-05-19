package com.fuzhudada.error.item.armor;

import com.fuzhudada.error.enchantments.ModEnchantmentInit;
import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemZhanNuMianLong extends com.somebody.idlframewok.item.ItemArmorBase {
    public ItemZhanNuMianLong(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, renderIndexIn, equipmentSlotIn);
    }

    @Override
    public void onArmorTick(@Nonnull World world , @Nonnull EntityPlayer player , @Nonnull ItemStack itemStack) {
        if(ModEnchantmentInit.PEI.getEnchantmentLevel(itemStack) ==0
                && player.getLookVec().y < -0.866025
                && !player.isPotionActive(MobEffects.POISON)
                && player.getCooldownTracker().getCooldown(itemStack.getItem(),0)<=0){
            player.attackEntityFrom(DamageSource.CACTUS, 20);
            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 200, 2));
            player.getCooldownTracker().setCooldown(itemStack.getItem(), 300);
            world.playSound(null, player.posX, player.posY, player.posZ, ModSoundHandler.ASSASSIN, SoundCategory.PLAYERS, 2F, player.getRNG().nextFloat() * 0.1F + 0.9F);
        }
        super.onArmorTick(world , player , itemStack);
    }
}
