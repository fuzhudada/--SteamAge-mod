package com.fuzhudada.error.item.armor;

import com.fuzhudada.error.item.armor.model.ModelAAS;
import com.fuzhudada.error.item.armor.model.ModelSAV1_1;
import com.fuzhudada.error.item.armor.model.ModelSAV1_2;
import com.fuzhudada.error.item.sword.ISteamWeapon;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ItemAAS extends ItemSaArmor implements ISteamWeapon{
    public static ModelAAS model=null;

    public ItemAAS(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, renderIndexIn, equipmentSlotIn);
    }

    @Override
    public float maxItemSteam() {
        return 250;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ModelBiped getArmorModel(@Nonnull EntityLivingBase entity, ItemStack itemStack, @Nonnull EntityEquipmentSlot armorSlot, @Nonnull ModelBiped _default) {
        if(!itemStack.isEmpty())
        {
            if(itemStack.getItem() instanceof ItemArmor)
            {
                if(model==null){
                    model = new ModelAAS();
                }

                model.bone2.showModel = armorSlot == EntityEquipmentSlot.CHEST;
                model.bone3.showModel = armorSlot == EntityEquipmentSlot.CHEST;
                model.bone5.showModel = armorSlot == EntityEquipmentSlot.CHEST;

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
}
