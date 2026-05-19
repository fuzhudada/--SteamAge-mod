package com.fuzhudada.error.item.armor;

import com.fuzhudada.error.item.armor.model.ModelDongLiJia_1;
import com.fuzhudada.error.item.armor.model.ModelSAV1_2;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ItemDongLiJia extends com.somebody.idlframewok.item.ItemArmorBase{
    public static ModelDongLiJia_1 model=null;
    public static ModelSAV1_2 model2=null;

    public ItemDongLiJia(String name , ArmorMaterial materialIn , int renderIndexIn , EntityEquipmentSlot equipmentSlotIn) {
        super(name , materialIn , renderIndexIn , equipmentSlotIn);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ModelBiped getArmorModel(@Nonnull EntityLivingBase entity, ItemStack itemStack, @Nonnull EntityEquipmentSlot armorSlot, @Nonnull ModelBiped _default) {
        if(!itemStack.isEmpty())
        {
            if(itemStack.getItem() instanceof ItemArmor)
            {
                if(armorSlot == EntityEquipmentSlot.LEGS){
                    if(model2==null){
                        model2 = new ModelSAV1_2();
                    }

                    model2.leftArmPose = _default.leftArmPose;
                    model2.rightArmPose = _default.rightArmPose;
                    model2.isChild = _default.isChild;
                    model2.isRiding = _default.isRiding;
                    model2.isSneak = _default.isSneak;

                    return model2;
                }else {
                    if(model==null){
                        model = new ModelDongLiJia_1();
                    }

                    model.Head.showModel = armorSlot == EntityEquipmentSlot.HEAD;
                    model.Body.showModel = armorSlot == EntityEquipmentSlot.CHEST;
                    model.LeftArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
                    model.RightArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
                    model.LeftLeg.showModel = armorSlot == EntityEquipmentSlot.FEET;
                    model.RightLeg.showModel = armorSlot == EntityEquipmentSlot.FEET;

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
