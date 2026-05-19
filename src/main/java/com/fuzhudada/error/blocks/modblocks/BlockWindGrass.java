package com.fuzhudada.error.blocks.modblocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BlockWindGrass extends BlockGrass{
    public BlockWindGrass(String name, Material material) {
        super(name, material);
    }

    public void onEntityCollidedWithBlock(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Entity entityIn)
    {
        entityIn.fallDistance=0;
        entityIn.motionY=0.8;
        for(int i=0;i<10;i++){
            worldIn.spawnParticle(EnumParticleTypes.CLOUD,pos.getX()+worldIn.rand.nextDouble(),pos.getY(),pos.getZ()+worldIn.rand.nextDouble(),0,0.1,0);
        }
    }
}
