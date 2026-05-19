package com.fuzhudada.error.item.armor;

import com.fuzhudada.error.item.armor.model.ModelBloodyArmor;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ItemBloodyArmor extends com.somebody.idlframewok.item.ItemArmorBase {
    public static ModelBloodyArmor model;

    public ItemBloodyArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, renderIndexIn, equipmentSlotIn);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ModelBiped getArmorModel(@Nonnull EntityLivingBase entity, ItemStack itemStack, @Nonnull EntityEquipmentSlot armorSlot, @Nonnull ModelBiped _default) {
        if(!itemStack.isEmpty())
        {
            if(itemStack.getItem() instanceof ItemArmor)
            {
                if(armorSlot == EntityEquipmentSlot.LEGS || armorSlot == EntityEquipmentSlot.FEET){
                    return null;
                }else{
                    if(model==null){
                        model = new ModelBloodyArmor();
                    }

                    model.Head.showModel = armorSlot == EntityEquipmentSlot.HEAD;
                    model.Body.showModel = armorSlot == EntityEquipmentSlot.CHEST;
                    model.LeftArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;

                    model.leftArmPose = _default.leftArmPose;
                    model.rightArmPose = _default.rightArmPose;
                    model.isChild = _default.isChild;
                    model.isRiding = _default.isRiding;
                    model.isSneak = _default.isSneak;

                    return model;
                }
            }
        }
        return null;
    }
}
