package com.fuzhudada.error.entity.projectiles;

import net.minecraft.block.BlockBush;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityFeiDao extends EntityThrowable {
    public EntityLivingBase ATTACKER;
    public float DAMAGE;

    public EntityFeiDao(World worldIn)
    {
        super(worldIn);
    }

    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null && result.entityHit != ATTACKER && !(result.entityHit instanceof EntityArmorStand))
        {
            result.entityHit.hurtResistantTime = 0;
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, ATTACKER), DAMAGE);
            result.entityHit.motionX=0;
            result.entityHit.motionZ=0;
        }

        if (!this.world.isRemote && result.entityHit != ATTACKER && !(result.getBlockPos() != null && this.world.getBlockState(result.getBlockPos()).getBlock() instanceof BlockBush))
        {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }

    public void onUpdate()
    {
        if(this.ticksExisted > 60){
            this.setDead();
        }
        super.onUpdate();
    }
}
