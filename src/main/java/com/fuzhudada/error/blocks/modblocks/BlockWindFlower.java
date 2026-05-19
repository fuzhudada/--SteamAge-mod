package com.fuzhudada.error.blocks.modblocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BlockWindFlower extends BlockModDoublePlant{
    public BlockWindFlower(String name){
        super(name);
    }

    public void onEntityCollidedWithBlock(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Entity entityIn)
    {
        if(state.getValue(HALF)==EnumBlockHalf.UPPER){
            EnumFacing face=state.getValue(FACING);
            int x=face.getDirectionVec().getX();
            int z=face.getDirectionVec().getZ();
            entityIn.motionX=-x;
            entityIn.motionY=0.5;
            entityIn.motionZ=-z;
            for(int i=0;i<10;i++){
                worldIn.spawnParticle(EnumParticleTypes.CLOUD,pos.getX()+worldIn.rand.nextDouble(),pos.getY()+worldIn.rand.nextDouble(),pos.getZ()+worldIn.rand.nextDouble(),-x*0.5,0,-z*0.5);
            }
        }
    }
}
