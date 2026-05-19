package com.fuzhudada.error.entity.projectiles;

import com.fuzhudada.error.ModDamageSource;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public class EntityHuoYanZiDan extends EntityThrowable {
    public EntityLivingBase ATTACKER;
    public float Damage;

    public EntityHuoYanZiDan(World worldIn) {
        super(worldIn);
    }

    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null && result.entityHit != ATTACKER && !(result.entityHit instanceof EntityArmorStand))
        {
            List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
            for (int i = 0; i <= G.size() - 1; i++) {
                if (result.entityHit.getDistanceSq(G.get(i)) < 9
                        && G.get(i) != ATTACKER
                        && !(G.get(i) instanceof EntityArmorStand)) {
                    G.get(i).hurtResistantTime = 0;
                    G.get(i).attackEntityFrom(ModDamageSource.causeFireBulletDamage(this, ATTACKER), Damage);
                }
            }
            for(int a=0;a<100;a++){
                this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX + (this.rand.nextDouble() - 0.5D) * 3, this.posY + (this.rand.nextDouble() - 0.5D) * 3, this.posZ + (this.rand.nextDouble() - 0.5D) * 3, 0.0D, 0.0D, 0.0D);
            }
        }

        if (!this.world.isRemote && result.entityHit != ATTACKER && !(result.getBlockPos() != null && this.world.getBlockState(result.getBlockPos()).getBlock() instanceof BlockBush))
        {
            this.world.setEntityState(this, (byte)3);
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
