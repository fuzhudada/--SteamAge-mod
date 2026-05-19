package com.fuzhudada.error.item.armor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemHuoLong extends com.somebody.idlframewok.item.ItemArmorBase {
    public ItemHuoLong(String name, ItemArmor.ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, renderIndexIn, equipmentSlotIn);
    }

    @Override
    public void onArmorTick(@Nonnull World world, @Nonnull EntityPlayer player, @Nonnull ItemStack itemStack) {
        if(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemHuoLong
                && player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemHuoLong
                && player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof ItemHuoLong
                && player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemHuoLong){
            player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE,20,0));
        }
        super.onArmorTick(world, player, itemStack);
    }
}
