package com.fuzhudada.error.item.armor;

import com.fuzhudada.error.item.sword.ISteamWeapon;
import net.minecraft.inventory.EntityEquipmentSlot;

public class ItemPenQi extends com.somebody.idlframewok.item.ItemArmorBase implements ISteamWeapon {
    public ItemPenQi(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, renderIndexIn, equipmentSlotIn);
    }

    @Override
    public float maxItemSteam() {
        return 100;
    }
}
