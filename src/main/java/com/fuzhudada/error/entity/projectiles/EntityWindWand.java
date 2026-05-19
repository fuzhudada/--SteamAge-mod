package com.fuzhudada.error.entity.projectiles;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.entity.weapon.EntityWind;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.HashSet;

public class EntityWindWand extends EntityThrowable {
    public EntityWindWand(World worldIn)
    {
        super(worldIn);
        this.set=new HashSet<>();
    }

    public EntityLivingBase ATTACKER;
    public float DAMAGE;
    public HashSet<Entity> set;

    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null && result.entityHit != ATTACKER && !(result.entityHit instanceof EntityArmorStand) && !set.contains(result.entityHit))
        {
            result.entityHit.attackEntityFrom(ModDamageSource.causeManaDamage(this, ATTACKER), DAMAGE);
            set.add(result.entityHit);
        }

        if (!this.world.isRemote && result.entityHit == null && !(result.getBlockPos() != null
                && this.world.getBlockState(result.getBlockPos()).getBlock() instanceof BlockBush))
        {
            this.world.setEntityState(this, (byte)3);
            EntityWind wind=new EntityWind(world);
            wind.setPosition(this.posX,this.posY,this.posZ);
            if(!this.world.isRemote){
                world.spawnEntity(wind);
            }
            this.setDead();
        }
    }

    public void onUpdate()
    {
        this.world.spawnParticle(EnumParticleTypes.CLOUD, this.posX, this.posY, this.posZ, 0,0,0);
        if(this.ticksExisted > 600){
            this.setDead();
        }
        super.onUpdate();
    }
}
