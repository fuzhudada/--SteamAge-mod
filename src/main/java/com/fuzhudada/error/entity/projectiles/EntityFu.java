package com.fuzhudada.error.entity.projectiles;

import com.fuzhudada.error.entity.weapon.EntityPoison;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityFu extends EntityThrowable {
    public EntityFu(World worldIn)
    {
        super(worldIn);
    }

    public EntityLivingBase ATTACKER;
    public float DAMAGE;
    public boolean flag;

    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null && result.entityHit != ATTACKER && !(result.entityHit instanceof EntityArmorStand))
        {
            result.entityHit.hurtResistantTime = 0;
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, ATTACKER), DAMAGE);
        }

        if (!this.world.isRemote && result.entityHit != ATTACKER && !(result.getBlockPos() != null && this.world.getBlockState(result.getBlockPos()).getBlock() instanceof BlockBush))
        {
            if(flag){
                double y=result.entityHit==null?posY:result.entityHit.posY;

                EntityPoison g=new EntityPoison(world);
                g.ATTACKER=ATTACKER;
                g.setPosition(posX,y,posZ);
                if(!world.isRemote){
                    world.spawnEntity(g);
                }
            }

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
