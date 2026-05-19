package com.fuzhudada.error.entity.projectiles;

import com.fuzhudada.error.ModDamageSource;
import com.somebody.idlframewok.potion.ModPotions;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityWuZuoBianTa extends EntityThrowable {
    public EntityWuZuoBianTa(World worldIn)
    {
        super(worldIn);
    }

    public EntityLivingBase ATTACKER;
    public float DAMAGE;
    public boolean BLOOD;

    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null && result.entityHit != ATTACKER && !(result.entityHit instanceof EntityArmorStand))
        {
            result.entityHit.hurtResistantTime = 0;
            result.entityHit.attackEntityFrom(ModDamageSource.causeManaDamage(this, ATTACKER), DAMAGE);
            if(BLOOD && result.entityHit instanceof EntityLivingBase) ((EntityLivingBase)result.entityHit).addPotionEffect(new PotionEffect(ModPotions.JI_XING_ZHONG_DU,99,2));
        }

        if (!this.world.isRemote && result.entityHit != ATTACKER && !(result.getBlockPos() != null && this.world.getBlockState(result.getBlockPos()).getBlock() instanceof BlockBush))
        {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }

    public void onUpdate()
    {
        this.world.spawnParticle(EnumParticleTypes.REDSTONE, this.posX, this.posY, this.posZ, 0, 0, 0);

        if(this.ticksExisted > 600){
            this.setDead();
        }
        super.onUpdate();
    }
}
