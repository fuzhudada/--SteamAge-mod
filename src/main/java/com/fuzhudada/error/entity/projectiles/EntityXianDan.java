package com.fuzhudada.error.entity.projectiles;

import com.fuzhudada.error.ModDamageSource;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public class EntityXianDan extends EntityThrowable {
    private EntityLivingBase attacker;
    public EntityXianDan(World worldIn)
    {
        super(worldIn);
    }

    public EntityXianDan(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
        this.attacker=throwerIn;
    }

    public float DAMAGE;

    protected void onImpact(RayTraceResult result)
    {
        if (!this.world.isRemote && result.entityHit instanceof EntityLivingBase
                && !(result.entityHit instanceof EntityArmorStand) && this.attacker != result.entityHit)
        {
                float d = this.attacker.getDistance(result.entityHit);
                if(d > 3) {
                    List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
                    if (d < 12) {
                        for (int i = 0; i <= G.size() - 1; i++) {
                            if (Math.sqrt((G.get(i).posX - result.entityHit.posX) * (G.get(i).posX - result.entityHit.posX) + (G.get(i).posY - result.entityHit.posY) * (G.get(i).posY - result.entityHit.posY) + (G.get(i).posZ - result.entityHit.posZ) * (G.get(i).posZ - result.entityHit.posZ)) < 0.3 * d
                                    && G.get(i) != this.getThrower() && G.get(i) != null && !(G.get(i) instanceof EntityArmorStand)) {
                                G.get(i).attackEntityFrom(ModDamageSource.causeShotGunDamage(this, this.getThrower()), (float) (this.DAMAGE * (1.3 - 0.1 * d)));
                            }
                        }
                    } else {
                        for (int i = 0; i <= G.size() - 1; i++) {
                            if (Math.sqrt((G.get(i).posX - result.entityHit.posX) * (G.get(i).posX - result.entityHit.posX) + (G.get(i).posY - result.entityHit.posY) * (G.get(i).posY - result.entityHit.posY) + (G.get(i).posZ - result.entityHit.posZ) * (G.get(i).posZ - result.entityHit.posZ)) < 3
                                    && G.get(i) != this.getThrower() && G.get(i) != null && !(G.get(i) instanceof EntityArmorStand)) {
                                G.get(i).attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) (this.DAMAGE * 0.1));
                            }
                        }
                    }
                }
        }

        if (!this.world.isRemote && this.attacker != result.entityHit && !(result.getBlockPos() != null && this.world.getBlockState(result.getBlockPos()).getBlock() instanceof BlockBush))
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
