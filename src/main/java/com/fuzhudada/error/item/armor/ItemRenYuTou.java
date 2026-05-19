package com.fuzhudada.error.item.shield;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemRenYuTou extends com.somebody.idlframewok.item.ItemArmorBase {
    public ItemRenYuTou(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, renderIndexIn, equipmentSlotIn);
        com.somebody.idlframewok.util.CommonFunctions.addToEventBus(this);
    }

    @SubscribeEvent
    public void E(LivingHurtEvent event)
    {
        World world = event.getEntityLiving().world;
        if (!world.isRemote)
        {
            EntityLivingBase H = event.getEntityLiving();
            if(H.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == this && H.isBurning())
            {
                event.setAmount(event.getAmount() *2);
            }
        }
    }
}
