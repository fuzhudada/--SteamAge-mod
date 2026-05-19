package com.fuzhudada.error.entity.projectiles;

import com.fuzhudada.error.ModDamageSource;
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

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.List;

public class EntityShuangZi extends EntityThrowable {
    private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityShuangZi.class, DataSerializers.VARINT);
    public EntityLivingBase ATTACKER;
    public float DAMAGE;
    public HashSet<Entity> set;

    public EntityShuangZi(World worldIn)
    {
        super(worldIn);
        this.setNoGravity(true);
        DAMAGE=82;
        this.set=new HashSet<>();
    }

    public void setType(int type)
    {
        this.dataManager.set(TYPE,type);
    }

    public int getType()
    {
        return (this.dataManager.get(TYPE));
    }

    protected void entityInit()
    {
        this.dataManager.register(TYPE, 0);
    }

    protected void onImpact(@Nonnull RayTraceResult result)
    {
        if (getType()==0 && result.entityHit != null && result.entityHit != ATTACKER && !(result.entityHit instanceof EntityArmorStand) && !set.contains(result.entityHit))
        {
            result.entityHit.hurtResistantTime = 0;
            result.entityHit.attackEntityFrom(ModDamageSource.causeManaDamage(this, ATTACKER), DAMAGE);
            set.add(result.entityHit);
        }
    }

    public void onUpdate()
  {
      int type=getType();
      if(type==0){
          for (int i = 0; i < 10; i++) {
              this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX + this.motionX * 2 * Math.sin(i * 0.1 * Math.PI), this.posY + i * 0.2, this.posZ + this.motionZ * 2 * Math.sin(i * 0.1 * Math.PI), 0, 0, 0);
          }
      }else if(type==1){
          Vec3d F = new Vec3d(motionX,0,motionZ).normalize();
          double a;
          final double atan = Math.atan(F.z / F.x);
          if(F.x>0){
              a= atan;
          }else{
              a= atan +Math.PI;
          }
          for (int i = 0; i < 10; i++) {
              this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX + Math.sin(a-Math.PI+i*0.2*Math.PI), this.posY + 1, this.posZ + 2 * Math.cos(a-Math.PI+i*0.2*Math.PI), 0, 0, 0);
          }
      }

      if(getType()==1){
          List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class,
                  e -> getDistanceSq(e)<7
                          && e != ATTACKER
                          && e.isEntityAlive()
                          && !(e instanceof EntityArmorStand)
                          && !set.contains(e));
          for(EntityLivingBase e:G){
              e.attackEntityFrom(ModDamageSource.causeManaDamage(this,ATTACKER),DAMAGE);
              set.add(e);
          }
      }

      if(this.ticksExisted > 20){
          this.setDead();
      }
      super.onUpdate();
  }
}
