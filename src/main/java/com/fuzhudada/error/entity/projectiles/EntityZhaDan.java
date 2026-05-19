package com.fuzhudada.error.entity.projectiles;

import com.fuzhudada.error.ModDamageSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public class EntityZhaDan extends EntityThrowable {
    public EntityZhaDan(World worldIn)
    {
        super(worldIn);
    }

    public EntityLivingBase ATTACKER;

    protected void onImpact(RayTraceResult result)
    {
//        if (result.entityHit != null && result.entityHit != ATTACKER)
        if (result.entityHit != ATTACKER)
        {
            List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
            for (int i = 0; i <= G.size() - 1; i++) {
                if (Math.sqrt((G.get(i).posX - this.posX) * (G.get(i).posX - this.posX) + (G.get(i).posY - this.posY) * (G.get(i).posY - this.posY) + (G.get(i).posZ - this.posZ) * (G.get(i).posZ - this.posZ)) < 3
                        && G.get(i) != ATTACKER && G.get(i) != null && !(G.get(i) instanceof EntityArmorStand)) {
                    G.get(i).attackEntityFrom(ModDamageSource.causeManaDamage(this, ATTACKER), 35);
                }
            }
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
        }

        if (!this.world.isRemote && result.entityHit != ATTACKER)
        {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }
}
