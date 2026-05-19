package com.fuzhudada.error.entity.projectiles;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.particle.ModParticleHandler;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public class EntityNaturalSoul extends EntityThrowable {
    public EntityLivingBase ATTACKER;
    public float Damage;

    public EntityNaturalSoul(World worldIn) {
        super(worldIn);
    }

    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null && result.entityHit != ATTACKER && !(result.entityHit instanceof EntityArmorStand))
        {
            List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
            for (int i = 0; i <= G.size() - 1; i++) {
                if (Math.sqrt((G.get(i).posX - result.entityHit.posX) * (G.get(i).posX - result.entityHit.posX) + (G.get(i).posY - result.entityHit.posY) * (G.get(i).posY - result.entityHit.posY) + (G.get(i).posZ - result.entityHit.posZ) * (G.get(i).posZ - result.entityHit.posZ)) < 3
                        && G.get(i) != ATTACKER && G.get(i) != null && !(G.get(i) instanceof EntityArmorStand)) {
                    G.get(i).hurtResistantTime = 0;
                    G.get(i).attackEntityFrom(ModDamageSource.causeFireBulletDamage(this, ATTACKER), Damage);
                }
            }
            if(world.isRemote){
                for(int a=0;a<100;a++){
                    this.world.spawnParticle(ModParticleHandler.TEST , this.posX + (this.rand.nextDouble() - 0.5D) * 3, this.posY + (this.rand.nextDouble() - 0.5D) * 3, this.posZ + (this.rand.nextDouble() - 0.5D) * 3, 0.0D, 0.0D, 0.0D);
                }
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
        if(world.isRemote){
            if(ticksExisted%2==0) this.world.spawnParticle(EnumParticleTypes.LAVA, this.posX, this.posY, this.posZ, 0, 0, 0);
            this.world.spawnParticle(ModParticleHandler.TEST, this.posX, this.posY, this.posZ, 0.5*Math.random()-0.25, 0.5*Math.random()-0.25, 0.5*Math.random()-0.25);
            this.world.spawnParticle(ModParticleHandler.TEST, this.posX, this.posY, this.posZ, 0.5*Math.random()-0.25, 0.5*Math.random()-0.25, 0.5*Math.random()-0.25);
        }

        if(this.ticksExisted > 600){
            this.setDead();
        }
        super.onUpdate();
    }
}
