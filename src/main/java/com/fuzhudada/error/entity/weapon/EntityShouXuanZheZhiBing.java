package com.fuzhudada.error.entity.weapon;

import com.fuzhudada.error.ModDamageSource;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class EntityShouXuanZheZhiBing extends EntityCreature {
    public EntityShouXuanZheZhiBing(World worldIn) {
        super(worldIn);
        this.setNoAI(true);
        this.setSize(0,0);
        this.setNoGravity(true);
        this.setSilent(true);
        this.experienceValue=0;
    }

    public EntityLivingBase ATTACKER;
    public float DAMAGE;

    public void onLivingUpdate()
    {
        Vec3d F=this.getLookVec();
        F=F.addVector(0,-F.y,0).normalize();
        double p=F.x==0?Math.PI/2:Math.atan(F.z/F.x);
        for(double r=0;r<12;r+=0.5){
            for(int p1=0;p1<6;p1++){
                this.world.spawnParticle(EnumParticleTypes.END_ROD, posX+r*Math.cos(p+p1*Math.PI/3), this.posY + 0.1, posZ+r*Math.sin(p+p1*Math.PI/3), 0, 0, 0);
            }
        }

        if(this.ticksExisted > 20){
            this.setDead();
            if(ATTACKER != null){
                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, e->
                        e.isEntityAlive() && e != ATTACKER && Math.abs(posY-e.posY)<1 && getDistance(e)<12 && !(e instanceof EntityArmorStand));
                for(EntityLivingBase e:G){
                    for(int p1=0;p1<3;p1++){
                        Vec3d F1=new Vec3d(Math.cos(p+p1*Math.PI/3),0,Math.sin(p+p1*Math.PI/3));
                        Vec3d F2=new Vec3d(e.posX-posX,0,e.posZ-posZ);
                        double dis=F2.lengthVector();
                        double angle=Math.acos(F1.dotProduct(F2)/dis);
                        double dis1=dis*Math.sin(angle);
                        if(dis1<1){
                            e.attackEntityFrom(ModDamageSource.causeManaDamage(this,ATTACKER),DAMAGE);
                        }
                    }
                }
            }
        }
        super.onLivingUpdate();
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }
}
