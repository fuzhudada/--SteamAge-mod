package com.fuzhudada.error.entity.weapon;

import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.List;

public class EntityWind extends EntityCreature {

    public EntityWind(World worldIn) {
        super(worldIn);
        this.setNoAI(true);
        this.setSize(0, 0);
        this.setNoGravity(true);
        this.setSilent(true);
        this.experienceValue = 0;
    }

    public void onUpdate()
    {
        if(this.ticksExisted >= 40){
            this.setDead();
        }else{
            for(int i=1;i<=8;i++){
                for(int j=0;j<20;j++){
                    this.world.spawnParticle(EnumParticleTypes.CRIT,this.posX+0.577*i*Math.cos(j*0.1*Math.PI),this.posY+i,this.posZ+0.577*i*Math.sin(j*0.1*Math.PI),0,0,0);
                }
            }
            double x,z;
            for(int i=0;i<10;i++){
                x=this.posX+16*Math.random()-8;
                z=this.posZ+16*Math.random()-8;
                this.world.spawnParticle(EnumParticleTypes.CRIT,x,this.posY+Math.random()*8,z,0.1*(this.posX-x),0,0.1*(this.posZ-z));
            }
            List<Entity> G = world.getEntities(Entity.class,
                    e -> e.getDistance(this)<16 && e.posY>=this.posY-1 && e.isEntityAlive() && !e.equals(this) && !e.isSneaking() && !(e instanceof EntityArmorStand));
            for(Entity e:G){
                e.motionX=0.1*(this.posX-e.posX);
                e.motionY=0.1;
                e.motionZ=0.1*(this.posZ-e.posZ);
            }
            this.world.playSound(null, this.posX, this.posY, this.posZ, ModSoundHandler.WIND, SoundCategory.PLAYERS, 4F, this.getRNG().nextFloat() * 0.1F + 0.9F);
        }

        super.onUpdate();
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }
}
