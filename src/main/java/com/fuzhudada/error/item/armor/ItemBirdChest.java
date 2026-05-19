package com.fuzhudada.error.item.armor;

import com.fuzhudada.error.item.armor.model.ModelBirdChest;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ItemBirdChest extends com.somebody.idlframewok.item.ItemArmorBase {
    public static ModelBirdChest model1;

    public ItemBirdChest(String name , ArmorMaterial materialIn , int renderIndexIn , EntityEquipmentSlot equipmentSlotIn) {
        super(name , materialIn , renderIndexIn , equipmentSlotIn);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ModelBiped getArmorModel(@Nonnull EntityLivingBase entity, ItemStack itemStack, @Nonnull EntityEquipmentSlot armorSlot, @Nonnull ModelBiped _default) {
        if(!itemStack.isEmpty() && itemStack.getItem() instanceof ItemArmor)
        {
                if(model1==null){
                    model1 = new ModelBirdChest();
                }

                model1.bone.showModel = armorSlot == EntityEquipmentSlot.CHEST;

                model1.leftArmPose = _default.leftArmPose;
                model1.rightArmPose = _default.rightArmPose;
                model1.isChild = _default.isChild;
                model1.isRiding = _default.isRiding;
                model1.isSneak = _default.isSneak;

                return model1;
        }
        return null;
    }
}
