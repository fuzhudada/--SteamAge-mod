package com.fuzhudada.error.item.shield;

import com.fuzhudada.error.enchantments.ModEnchantmentInit;
import com.fuzhudada.error.item.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemYangQiBeiBao extends com.somebody.idlframewok.item.ItemArmorBase {
    public ItemYangQiBeiBao(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, renderIndexIn, equipmentSlotIn);
        com.somebody.idlframewok.util.CommonFunctions.addToEventBus(this);
    }

    @SubscribeEvent
    public void E(LivingEvent.LivingUpdateEvent event)
    {
        World world = event.getEntity().world;
        if (!world.isRemote && event.getEntity() instanceof EntityLivingBase)
        {
            EntityLivingBase P = (EntityLivingBase) event.getEntity();
            if (P.isInWater() && P.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == ModItems.YANG_QI_BEI_BAO)
            {
                ItemStack Item = P.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                if (getDamage(Item) < 24000)
                {
                    P.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 60, 0));
                    int level = ModEnchantmentInit.RONG_LIANG_SHENG_JI.getEnchantmentLevel(P.getItemStackFromSlot(EntityEquipmentSlot.CHEST));
                    float i = P.getRNG().nextFloat()*(level + 1);
                    if(i < 1) {
                        Item.setItemDamage(getDamage(Item) + 1);
                    }
                }
            }
            if (P.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ModItems.YE_SHI_YAN_JING)
            {
                if(P.isInWater()) {
                    P.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 60, 0));
                }else{
                    P.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 1, 1));
                    P.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 60, 0));
                }
            }
        }
    }
}
