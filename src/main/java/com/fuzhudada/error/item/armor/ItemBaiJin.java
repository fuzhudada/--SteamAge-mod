package com.fuzhudada.error.item.armor;

import com.fuzhudada.error.item.armor.model.*;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ItemBaiJin extends com.somebody.idlframewok.item.ItemArmorBase {
    public static ModelBaiJin_1 model1;
    public static ModelBaiJin_2 model2;
    public static ModelBaiJin_3 model3;

    public ItemBaiJin(String name , ArmorMaterial materialIn , int renderIndexIn , EntityEquipmentSlot equipmentSlotIn) {
        super(name , materialIn , renderIndexIn , equipmentSlotIn);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ModelBiped getArmorModel(@Nonnull EntityLivingBase entity, ItemStack itemStack, @Nonnull EntityEquipmentSlot armorSlot, @Nonnull ModelBiped _default) {
        if(!itemStack.isEmpty())
        {
            if(itemStack.getItem() instanceof ItemArmor)
            {
                if(armorSlot == EntityEquipmentSlot.LEGS) {
                    if (model2 == null) {
                        model2 = new ModelBaiJin_2();
                    }

                    model2.leftArmPose = _default.leftArmPose;
                    model2.rightArmPose = _default.rightArmPose;
                    model2.isChild = _default.isChild;
                    model2.isRiding = _default.isRiding;
                    model2.isSneak = _default.isSneak;

                    return model2;
                }else if(armorSlot == EntityEquipmentSlot.FEET){
                    if (model3 == null) {
                        model3 = new ModelBaiJin_3();
                    }

                    model3.leftArmPose = _default.leftArmPose;
                    model3.rightArmPose = _default.rightArmPose;
                    model3.isChild = _default.isChild;
                    model3.isRiding = _default.isRiding;
                    model3.isSneak = _default.isSneak;

                    return model3;
                }else {
                    if(model1==null){
                        model1 = new ModelBaiJin_1();
                    }

                    model1.Head.showModel = armorSlot == EntityEquipmentSlot.HEAD;
                    model1.Body.showModel = armorSlot == EntityEquipmentSlot.CHEST;
                    model1.LeftArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
                    model1.RightArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;

                    model1.leftArmPose = _default.leftArmPose;
                    model1.rightArmPose = _default.rightArmPose;
                    model1.isChild = _default.isChild;
                    model1.isRiding = _default.isRiding;
                    model1.isSneak = _default.isSneak;

                    return model1;
                }
            }
        }
        return null;
    }
}
