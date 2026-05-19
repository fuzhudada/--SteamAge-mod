package com.fuzhudada.error.entity.weapon;

import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class EntityLieFengQiQiang extends EntityCreature {
    private static final DataParameter<String> attackerName = EntityDataManager.createKey(EntityLieFengQiQiang.class, DataSerializers.STRING);

    public EntityLieFengQiQiang(World worldIn) {
        super(worldIn);
        this.setNoAI(true);
        this.setSize(0, 0);
        this.setNoGravity(true);
        this.setSilent(true);
        this.experienceValue = 0;
    }

    protected void entityInit()
    {
        this.dataManager.register(attackerName, "");
        super.entityInit();
    }

    public void setAttackerName(String attackerName)
    {
        this.dataManager.set(EntityLieFengQiQiang.attackerName,attackerName);
    }

    public String getAttackerName()
    {
        return this.dataManager.get(attackerName);
    }

    public void onUpdate()
    {
        if(this.ticksExisted >= 60){
            this.setDead();
        }else{
            Vec3d F=this.getLookVec();
            F=F.addVector(0,-F.y,0).normalize();
            if(world.isRemote){
                for(int i=1;i<=3;i++){
                    spawnWindParticle(this.posX+3*F.x*i,this.posY,this.posZ+3*F.z*i);
                }
            }

            Vec3d F1=new Vec3d(-F.z,0,F.x).normalize();
            Vec3d F2=new Vec3d(F.z,0,-F.x).normalize();
            Vec3d F3;
            List<Entity> G = world.getEntities(Entity.class,
                    e -> getLineDistance(e,false)<10
                            && getLineDistance(e,true)<8
                            && getAngle(e)>0
                            && (getAttackerName().isEmpty() || !e.getName().equals(getAttackerName()))
                            && e.posY>=this.posY-1
                            && e.isEntityAlive()
                            && !e.equals(this)
                            && !e.isSneaking()
                            && !(e instanceof EntityArmorStand));
            for(Entity e:G){
                F3=new Vec3d(e.posX-this.posX,0,e.posZ-this.posZ).normalize().crossProduct(F).y>0?F2:F1;
                e.motionX=0.1*getLineDistance(e,true)*F3.x;
                e.motionZ=0.1*getLineDistance(e,true)*F3.z;
            }
            this.world.playSound(null, this.posX, this.posY, this.posZ, ModSoundHandler.WIND, SoundCategory.PLAYERS, 4F, this.getRNG().nextFloat() * 0.1F + 0.9F);
        }

        super.onUpdate();
    }

    private void spawnWindParticle(double x,double y,double z){
        for(int i=1;i<=4;i++){
            for(int j=0;j<10;j++){
                this.world.spawnParticle(EnumParticleTypes.CRIT,x+0.577*i*Math.cos(j*0.2*Math.PI),y+i,z+0.577*i*Math.sin(j*0.2*Math.PI),0,0,0);
            }
        }
        double x1,z1;
        for(int i=0;i<10;i++){
            x1=x+8*Math.random()-4;
            z1=z+8*Math.random()-4;
            this.world.spawnParticle(EnumParticleTypes.CRIT,x1,y+Math.random()*4,z1,0.1*(x-x1),0,0.1*(z-z1));
        }
    }

    private double getAngle(Entity entity){
        Vec3d F=new Vec3d(entity.posX-this.posX,0,entity.posZ-this.posZ).normalize();
        return this.getLookVec().dotProduct(F);
    }

    private double getLineDistance(Entity entity,boolean useSin){
        double angle=Math.acos(getAngle(entity));
        double d=this.getDistance(entity);
        return useSin?d*Math.sin(angle):d*Math.cos(angle);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }
}
