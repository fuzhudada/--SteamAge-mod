package com.fuzhudada.error.entity.projectiles;

import net.minecraft.block.BlockBush;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.HashSet;

public class EntityHuangJinJian extends EntityThrowable {
    public EntityLivingBase ATTACKER;
    public float DAMAGE;
    public boolean Fire;
    public boolean noHurtCoolDown;
    public HashSet<Entity> set;

    public EntityHuangJinJian(World worldIn) {
        super(worldIn);
        this.set=new HashSet<>();
        noHurtCoolDown=false;
    }

    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null && result.entityHit != ATTACKER && !(result.entityHit instanceof EntityArmorStand) && !set.contains(result.entityHit))
        {
            if(noHurtCoolDown) result.entityHit.hurtResistantTime=0;
            set.add(result.entityHit);
            result.entityHit.attackEntityFrom((new EntityDamageSourceIndirect("arrow", this, ATTACKER)).setProjectile(), DAMAGE);
            if(Fire){
                result.entityHit.setFire(5);
            }
        }

        if (!this.world.isRemote && result.entityHit != ATTACKER && !(result.getBlockPos() != null && this.world.getBlockState(result.getBlockPos()).getBlock() instanceof BlockBush))
        {
            this.world.setEntityState(this, (byte)3);
        }
    }

    public void onUpdate()
    {
        this.world.spawnParticle(EnumParticleTypes.REDSTONE, this.posX, this.posY, this.posZ, 0, -0.1, 0);

        if(this.ticksExisted > 600){
            this.setDead();
        }
        super.onUpdate();
    }
}
