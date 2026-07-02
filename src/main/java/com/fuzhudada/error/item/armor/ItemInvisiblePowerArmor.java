package com.fuzhudada.error.item.armor;

import com.fuzhudada.error.item.armor.model.ModelInvisiblePowerArmor;
import com.fuzhudada.error.item.sword.ISteamWeapon;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ItemInvisiblePowerArmor extends com.somebody.idlframewok.item.ItemArmorBase implements ISteamWeapon {
    public static ModelInvisiblePowerArmor model;

    public ItemInvisiblePowerArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, renderIndexIn, equipmentSlotIn);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ModelBiped getArmorModel(@Nonnull EntityLivingBase entity, ItemStack itemStack, @Nonnull EntityEquipmentSlot armorSlot, @Nonnull ModelBiped _default) {
        if(!itemStack.isEmpty())
        {
            if(armorSlot != EntityEquipmentSlot.FEET){
                if(model==null){
                    model = new ModelInvisiblePowerArmor();
                }

                model.Head.showModel = armorSlot == EntityEquipmentSlot.HEAD;
                model.Body.showModel = armorSlot == EntityEquipmentSlot.CHEST;
                model.LeftLeg.showModel = armorSlot == EntityEquipmentSlot.LEGS;
                model.RightLeg.showModel = armorSlot == EntityEquipmentSlot.LEGS;

                model.leftArmPose = _default.leftArmPose;
                model.rightArmPose = _default.rightArmPose;
                model.isChild = _default.isChild;
                model.isRiding = _default.isRiding;
                model.isSneak = _default.isSneak;

                return model;
            }
        }
        return null;
    }

    @Override
    public float maxItemSteam() {
        return 100;
    }
}
