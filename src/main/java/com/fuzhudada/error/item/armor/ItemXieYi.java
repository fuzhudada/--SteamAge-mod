package com.fuzhudada.error.item.shield;

import com.fuzhudada.error.item.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ItemXieYi extends com.somebody.idlframewok.item.ItemArmorBase {
    public ItemXieYi(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, renderIndexIn, equipmentSlotIn);
        com.somebody.idlframewok.util.CommonFunctions.addToEventBus(this);
    }

    @SubscribeEvent
    public void E(TickEvent.PlayerTickEvent event)
    {
        World world = event.player.getEntityWorld();
        if (!world.isRemote)
        {
            EntityLivingBase P = event.player;
            float H=P.getMaxHealth();
            if (P.getHealth()<0.1*H)
            {
                int i = 0;
                    if (P.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ModItems.XIE_YI_TOU) {
                        i = i + 1;
                    }
                    if (P.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == ModItems.XIE_YI_JIA) {
                        i = i + 1;
                    }
                    if (P.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == ModItems.XIE_YI_TUI) {
                        i = i + 1;
                    }
                    if (P.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == ModItems.XIE_YI_XIE) {
                        i = i + 1;
                    }
                    if(i>1){
                        P.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,40,i-2));
                    }
            }
        }
    }
}
