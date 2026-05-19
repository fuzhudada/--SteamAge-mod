package com.fuzhudada.error.entity.projectiles;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.entity.weapon.EntityHuoLong;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public class EntityBombFruit extends EntityThrowable {
    public EntityBombFruit(World worldIn)
    {
        super(worldIn);
    }

    public EntityLivingBase ATTACKER;
    public float DAMAGE;

    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null && result.entityHit != ATTACKER && !(result.entityHit instanceof EntityArmorStand))
        {
            List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, e -> e.isEntityAlive()
            && e.getDistance(result.entityHit)<3 && e != ATTACKER && !(e instanceof EntityArmorStand));
            for (int i = 0; i <= G.size() - 1; i++) {
                G.get(i).attackEntityFrom(ModDamageSource.causeFireBulletDamage(this, ATTACKER), DAMAGE);
                G.get(i).hurtResistantTime = 0;

                double d=G.get(i).getDistance(result.entityHit);
                G.get(i).motionY+=0.4;
                if(d != 0){
                    G.get(i).motionX=(G.get(i).posX-result.entityHit.posX)*1.8/d/d;
                    G.get(i).motionZ=(G.get(i).posZ-result.entityHit.posZ)*1.8/d/d;
                }
            }
            EntityHuoLong.spawnParticle(result.entityHit.posX,result.entityHit.posY,result.entityHit.posZ,2,this.world);
        }

        if (!this.world.isRemote && result.entityHit != ATTACKER && !(result.getBlockPos() != null && this.world.getBlockState(result.getBlockPos()).getBlock() instanceof BlockBush))
        {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }

    public void onUpdate()
    {
        this.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.posX, this.posY, this.posZ, 0, -0.5, 0);

        if(this.ticksExisted > 600){
            this.setDead();
        }
        super.onUpdate();
    }
}
