package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.item.ModItems;
import com.somebody.idlframewok.potion.ModPotions;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BlockMrSmGreen extends BlockKeKeJu{
    public BlockMrSmGreen(String name, Material material) {
        super(name, material);
    }

    public void onEntityCollidedWithBlock(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Entity entityIn)
    {
        if(entityIn instanceof EntityLivingBase
                && !((EntityLivingBase)entityIn).isPotionActive(ModPotions.QU_SAN_XIANG)
                && !((EntityLivingBase)entityIn).isPotionActive(ModPotions.JI_XING_ZHONG_DU)
                && !((EntityLivingBase)entityIn).isPotionActive(ModPotions.CEN_DIAN)
                && ((EntityLivingBase)entityIn).getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() != ModItems.POSION_BOOT){
            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(ModPotions.JI_XING_ZHONG_DU,100,1));
        }
    }
}
