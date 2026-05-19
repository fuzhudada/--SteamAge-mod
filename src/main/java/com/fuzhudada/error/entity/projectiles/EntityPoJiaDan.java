package com.fuzhudada.error.entity.projectiles;

import com.fuzhudada.error.ModDamageSource;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityPoJiaDan extends EntityThrowable {
    public EntityPoJiaDan(World worldIn) {
        super(worldIn);
    }

    public EntityLivingBase ATTACKER;
    public float DAMAGE;

    protected void onImpact(RayTraceResult result) {
        if (result.entityHit != null && result.entityHit != ATTACKER && !(result.entityHit instanceof EntityArmorStand)) {
            result.entityHit.hurtResistantTime = 0;
            result.entityHit.attackEntityFrom(ModDamageSource.causeArmorBrokenDamage(ATTACKER), DAMAGE);
        }

        if (!this.world.isRemote && result.entityHit != ATTACKER && !(result.getBlockPos() != null && this.world.getBlockState(result.getBlockPos()).getBlock() instanceof BlockBush)) {
            this.world.setEntityState(this, (byte) 3);
            this.setDead();
        }
    }
    public void onUpdate()
    {
        if(this.ticksExisted > 600){
            this.setDead();
        }
        super.onUpdate();
    }
}

