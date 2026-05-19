package com.fuzhudada.error.entity.model;

import com.fuzhudada.error.ModDamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class EntityCrystalShield extends EntityThrowable {
    public EntityLivingBase ATTACKER;
    public float DAMAGE;
    private static final DataParameter<String> attackerName = EntityDataManager.createKey(EntityCrystalShield.class, DataSerializers.STRING);
    private static final DataParameter<Float> fx = EntityDataManager.createKey(EntityCrystalShield.class, DataSerializers.FLOAT);
    private static final DataParameter<Float> fy = EntityDataManager.createKey(EntityCrystalShield.class, DataSerializers.FLOAT);
    private static final DataParameter<Float> fz = EntityDataManager.createKey(EntityCrystalShield.class, DataSerializers.FLOAT);

    public EntityCrystalShield(World worldIn)
    {
        super(worldIn);
    }

    protected void entityInit()
    {
        this.dataManager.register(fx, 1F);
        this.dataManager.register(fy, 0F);
        this.dataManager.register(fz, 0F);
        dataManager.register(attackerName,"");
        super.entityInit();
    }

    public void setVec(float x,float y,float z)
    {
        Vec3d vec3d=new Vec3d(x,y,z).normalize();
        this.dataManager.set(fx,(float) vec3d.x);
        this.dataManager.set(fy,(float) vec3d.y);
        this.dataManager.set(fz,(float) vec3d.z);
    }

    public Vec3d getVec()
    {
        return new Vec3d(dataManager.get(fx),dataManager.get(fy),dataManager.get(fz));
    }

    public void setAttackerName(String attackerName)
    {
        this.dataManager.set(EntityCrystalShield.attackerName,attackerName);
    }

    public String getAttackerName()
    {
        return this.dataManager.get(attackerName);
    }

    protected void onImpact(@Nonnull RayTraceResult result) {}

    public void onUpdate()
    {
        Vec3d F=getVec();
        motionX=F.x;
        motionY=F.y;
        motionZ=F.z;

        if(this.ticksExisted > 30 && ATTACKER != null){
            setVec((float) (ATTACKER.posX-posX),(float) (ATTACKER.posY+ATTACKER.getEyeHeight()-posY),(float) (ATTACKER.posZ-posZ));

            if(getDistanceSq(ATTACKER)<3){
                setDead();
            }
        }

        if(world.isRemote){
            for(int i=0;i<(100-ticksExisted)/4;i++){
                double vx=0.2*Math.cos(ticksExisted*Math.PI/10);
                double vz=0.2*Math.sin(ticksExisted*Math.PI/10);
                world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK,posX+vx,posY,posZ+vz,vz,0,vz);
            }
        }

        List<Entity> G = world.getEntities(Entity.class,
                e -> getDistanceSq(e)<7
                        && (getAttackerName().isEmpty() || !e.getName().equals(getAttackerName()))
                        && e.isEntityAlive()
                        && !e.equals(this)
                        && !(e instanceof EntityItem)
                        && !(e instanceof EntityArmorStand));
        for(Entity e:G){
            e.attackEntityFrom(ModDamageSource.causeManaDamage(this,ATTACKER),DAMAGE);
            double d=getDistance(e);
            if(d>0){
                d=Math.min(5,d);
                e.motionX=0.2*(e.posX-posX)/d;
                e.motionY=0.2;
                e.motionZ=0.2*(e.posZ-posZ)/d;
            }
        }

        if(ticksExisted>120){
            this.setDead();
        }
        super.onUpdate();
    }
}
