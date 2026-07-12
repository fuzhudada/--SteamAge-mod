package com.fuzhudada.error.entity.projectiles;

import net.minecraft.block.BlockBush;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityRobotBullet extends EntityThrowable {
    public EntityLivingBase ATTACKER;
    public float damage;
    private boolean impacted;

    public EntityRobotBullet(World worldIn) {
        super(worldIn);
        this.setSize(0.4F, 0.4F);
    }

    public EntityRobotBullet(World worldIn, EntityLivingBase ATTACKER, float damage) {
        super(worldIn);
        this.setSize(0.4F, 0.4F);
        this.ATTACKER = ATTACKER;
        this.damage = damage;
    }

    protected void entityInit() {
    }

    protected void onImpact(@Nonnull RayTraceResult result) {
        if (result.entityHit != null && result.entityHit != ATTACKER && !(result.entityHit instanceof EntityArmorStand))
        {
            result.entityHit.hurtResistantTime = 0;
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, ATTACKER), damage);
        }

        if (!this.world.isRemote && result.entityHit != ATTACKER && !(result.getBlockPos() != null && this.world.getBlockState(result.getBlockPos()).getBlock() instanceof BlockBush))
        {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }

    public void onUpdate() {
        world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY, this.posZ, 0, 0, 0);

        motionX /= 0.99;
        motionY /= 0.99;
        motionZ /= 0.99;

        if (this.ticksExisted >= 200) {
            this.setDead();
        } else {
            super.onUpdate();
        }
    }
}
