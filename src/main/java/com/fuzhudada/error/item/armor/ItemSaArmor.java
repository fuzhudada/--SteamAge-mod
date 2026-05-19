package com.fuzhudada.error.item.armor;

import com.fuzhudada.error.item.ModItems;
import com.fuzhudada.error.item.armor.model.ModelSAV1_1;
import com.fuzhudada.error.item.armor.model.ModelSAV1_2;
import com.fuzhudada.error.item.gun.IGun;
import com.fuzhudada.error.item.sword.ISteamWeapon;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ItemSaArmor extends com.somebody.idlframewok.item.ItemArmorBase implements ISteamWeapon, IGun {
    public static ModelSAV1_1 model=null;
    public static ModelSAV1_2 model2=null;

    public ItemSaArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, renderIndexIn, equipmentSlotIn);
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
                        model = new ModelSAV1_1();
                    }

                    model.Head.showModel = armorSlot == EntityEquipmentSlot.HEAD;
                    model.Body.showModel = armorSlot == EntityEquipmentSlot.CHEST;
                    model.LeftArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
                    model.RightArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
                    model.LeftLeg.showModel = armorSlot == EntityEquipmentSlot.FEET;
                    model.RightLeg.showModel = armorSlot == EntityEquipmentSlot.FEET;
                    model.Rpg.showModel = (armorSlot == EntityEquipmentSlot.CHEST && showRPG(entity));

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

    public boolean showRPG(EntityLivingBase entityLivingBase){
        if(entityLivingBase instanceof EntityPlayer){
            EntityPlayer player=(EntityPlayer) entityLivingBase;
            for (int j = 9; j < 40; ++j) {
                ItemStack itemstack = player.inventory.getStackInSlot(j);
                if(itemstack.getItem() == ModItems.ZHONG_PAO_DAN){
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public float maxItemSteam() {
        return 200;
    }

    @Override
    public float getDamage() {
        return 320;
    }
}
