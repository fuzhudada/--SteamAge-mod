package com.fuzhudada.error.entity.projectiles;

import net.minecraft.block.BlockBush;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityFeather extends EntityThrowable {
    public EntityLivingBase ATTACKER;
    public float damage;

    public EntityFeather(World worldIn) {
        super(worldIn);
    }

    public EntityFeather(World worldIn,EntityLivingBase ATTACKER,float damage){
        super(worldIn);
        this.ATTACKER=ATTACKER;
        this.damage=damage;
    }

    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit instanceof EntityLivingBase && ATTACKER != null && result.entityHit != ATTACKER && !(result.entityHit instanceof EntityArmorStand))
        {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, ATTACKER), damage);
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
