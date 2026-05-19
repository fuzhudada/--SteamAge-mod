package com.fuzhudada.error.entity.projectiles;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.entity.weapon.EntityHuoLong;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class EntityWindCharge extends EntityThrowable {
    public EntityLivingBase ATTACKER;
    public float DAMAGE;

    public EntityWindCharge(World worldIn) {
        super(worldIn);
    }

    protected void onImpact(@Nonnull RayTraceResult result) {
        if (!this.world.isRemote && !(result.getBlockPos() != null && this.world.getBlockState(result.getBlockPos()).getBlock() instanceof BlockBush)) {
            List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class , e -> e.isEntityAlive()
                    && getDistance(e) < 8 && !(e instanceof EntityArmorStand));
            for (int i = 0; i <= G.size() - 1; i++) {
                G.get(i).attackEntityFrom(ModDamageSource.causeManaDamage(this , ATTACKER) , DAMAGE);
                double d=getDistance(G.get(i));
                if(d==0){
                    G.get(i).motionY+=1.2;
                }else{
                    G.get(i).motionY += Math.min(3.2/d,1.2);
                    G.get(i).motionX = (G.get(i).posX-posX) * 2 / d;
                    G.get(i).motionZ = (G.get(i).posZ-posZ) * 2 / d;
                }
            }
            EntityHuoLong.spawnParticle(posX , posY , posZ , 5 , this.world);

            for(int x = (int) (posX-4); x<=posX+4; x++){
                for(int y = (int) (posY-4); y<=posY+4; y++){
                    for(int z = (int) (posZ-4); z<=posZ+4; z++){
                        BlockPos pos=new BlockPos(x,y,z);
                        IBlockState blockState=world.getBlockState(pos);
                        if(blockState.getBlock() == ModBlocks.BOMB_FRUIT_CROP){
                            int age= ((BlockCrops)blockState.getBlock()).getMetaFromState(blockState);
                            world.setBlockState(pos,ModBlocks.WIND_FRUIT_CROP.getDefaultState().withProperty(BlockCrops.AGE,age));
                        }
                    }
                }
            }

            this.world.setEntityState(this , (byte) 3);
            this.setDead();
        }
    }

    public void onUpdate() {
        if (this.ticksExisted > 600) {
            this.setDead();
        }
        super.onUpdate();
    }
}
