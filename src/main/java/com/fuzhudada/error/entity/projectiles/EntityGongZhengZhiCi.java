package com.fuzhudada.error.entity.projectiles;

import com.fuzhudada.error.ModDamageSource;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.HashSet;

public class EntityGongZhengZhiCi extends EntityThrowable {
    public EntityGongZhengZhiCi(World worldIn)
    {
        super(worldIn);
        this.set=new HashSet<>();
    }

    public EntityLivingBase ATTACKER;
    public float DAMAGE;
    private static final DataParameter<Boolean> explode = EntityDataManager.createKey(EntityGongZhengZhiCi.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> fire = EntityDataManager.createKey(EntityGongZhengZhiCi.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> hurtTime = EntityDataManager.createKey(EntityGongZhengZhiCi.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> pass = EntityDataManager.createKey(EntityGongZhengZhiCi.class, DataSerializers.BOOLEAN);
    public HashSet<Entity> set;

    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null && result.entityHit != ATTACKER && !(result.entityHit instanceof EntityArmorStand) && !set.contains(result.entityHit))
        {
            result.entityHit.attackEntityFrom(ModDamageSource.causeManaDamage(this, ATTACKER), DAMAGE);
            set.add(result.entityHit);
            if(getFire()){
                result.entityHit.setFire(5);
            }
            if(!getHurtTime()) {
                result.entityHit.hurtResistantTime = 0;
                result.entityHit.motionX=0;
                result.entityHit.motionZ=0;
            }
        }
        assert result.entityHit != null;
        if(result.entityHit instanceof EntityLivingBase && !result.entityHit.isEntityAlive() && getExplode()){
            for(int i=0;i<100;i++) {
                Vec3d F=new Vec3d(Math.random()-0.5, Math.random()-0.5, Math.random()-0.5).normalize();
                this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY+1, this.posZ, 0.4*F.x,0.4*F.y,0.4*F.z);
            }
        }

        if(!getPass()){
            if (!this.world.isRemote && result.entityHit != ATTACKER && !(result.getBlockPos() != null && this.world.getBlockState(result.getBlockPos()).getBlock() instanceof BlockBush))
            {
                this.world.setEntityState(this, (byte)3);
                this.setDead();
            }
        }
    }

    public void setExplode(boolean explode)
    {
        this.dataManager.set(EntityGongZhengZhiCi.explode,explode);
    }

    public boolean getExplode()
    {
        return (this.dataManager.get(explode));
    }

    public void setFire(boolean fire)
    {
        this.dataManager.set(EntityGongZhengZhiCi.fire,fire);
    }

    public boolean getFire()
    {
        return (this.dataManager.get(fire));
    }

    public void setHurtTime(boolean hurtTime)
    {
        this.dataManager.set(EntityGongZhengZhiCi.hurtTime,hurtTime);
    }

    public boolean getHurtTime()
    {
        return (this.dataManager.get(hurtTime));
    }

    public void setPass(boolean pass)
    {
        this.dataManager.set(EntityGongZhengZhiCi.pass,pass);
    }

    public boolean getPass()
    {
        return (this.dataManager.get(pass));
    }

    protected void entityInit()
    {
        this.dataManager.register(explode, true);
        this.dataManager.register(fire, false);
        this.dataManager.register(hurtTime,true);
        this.dataManager.register(pass,true);
    }

    public void onUpdate()
    {
        this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY, this.posZ, 0, 0, 0);

        if(this.ticksExisted > 30){
            this.setDead();
        }
        super.onUpdate();
    }
}
