package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.blocks.BlockBase;
import com.fuzhudada.error.item.ModItems;
import com.somebody.idlframewok.potion.ModPotions;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BlockMrGreen extends BlockBase {
    public BlockMrGreen(String name, Material material, SoundType type) {
        super(name, material, type);
    }

    public void onEntityWalk(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull Entity entityIn){
        if(entityIn instanceof EntityLivingBase
                && !((EntityLivingBase)entityIn).isPotionActive(ModPotions.QU_SAN_XIANG)
                && !((EntityLivingBase)entityIn).isPotionActive(ModPotions.JI_XING_ZHONG_DU)
                && !((EntityLivingBase)entityIn).isPotionActive(ModPotions.CEN_DIAN)
                && ((EntityLivingBase)entityIn).getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() != ModItems.POSION_BOOT){
            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(ModPotions.JI_XING_ZHONG_DU,100,1));
        }
    }
}
