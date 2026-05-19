package com.fuzhudada.error.item.armor;

import com.fuzhudada.error.enchantments.ModEnchantmentInit;
import com.fuzhudada.error.entity.weapon.EntityPoison;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemPoisonBoot extends com.somebody.idlframewok.item.ItemArmorBase {
    public ItemPoisonBoot(String name , ArmorMaterial materialIn , int renderIndexIn , EntityEquipmentSlot equipmentSlotIn) {
        super(name , materialIn , renderIndexIn , equipmentSlotIn);
    }

    @Override
    public void onArmorTick(@Nonnull World world, @Nonnull EntityPlayer player, @Nonnull ItemStack itemStack) {
        if(ModEnchantmentInit.PEI.getEnchantmentLevel(itemStack)==0
                && !player.isPotionActive(MobEffects.POISON)
                && player.getHealth()<0.8*player.getMaxHealth()
                && player.ticksExisted % 40 == 0
                && player.onGround){
            EntityPoison g=new EntityPoison(world,1);
            g.ATTACKER=player;
            g.setPosition(player.posX,player.posY,player.posZ);
            if(!world.isRemote){
                world.spawnEntity(g);
            }
        }
        super.onArmorTick(world, player, itemStack);
    }
}
