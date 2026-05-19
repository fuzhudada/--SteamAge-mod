package com.fuzhudada.error.blocks.fluid;

import com.somebody.idlframewok.potion.ModPotions;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

import javax.annotation.Nonnull;

public class FluidManaBlock extends FluidBlock{
    public FluidManaBlock(String name, Fluid fluid, Material material) {
        super(name, fluid, material);
    }

    @Override
    public void onEntityCollidedWithBlock(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Entity entityIn) {
        if(entityIn instanceof EntityLivingBase){
            if(!((EntityLivingBase) entityIn).isPotionActive(ModPotions.MO_LI_QIN_HE)) {
                entityIn.attackEntityFrom(DamageSource.MAGIC, ((EntityLivingBase) entityIn).getMaxHealth() / 20f);
            }
            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(ModPotions.MIN_XIANG_XIANG,10,5));
        }
        super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
    }
}
