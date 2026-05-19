package com.fuzhudada.error.entity.projectiles;

import com.fuzhudada.error.ModDamageSource;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityQiShiDao extends EntityThrowable {
    private static final DataParameter<Integer> type = EntityDataManager.createKey(EntityQiShiDao.class, DataSerializers.VARINT);
    public EntityLivingBase ATTACKER;
    public float DAMAGE;

    public EntityQiShiDao(World worldIn) {
        super(worldIn);
    }

    public void setType(int type)
    {
        this.dataManager.set(EntityQiShiDao.type,type);
    }

    public int getType()
    {
        return this.dataManager.get(type);
    }

    protected void entityInit()
    {
        this.dataManager.register(type, 0);
    }

    @Override
    protected void onImpact(@Nonnull RayTraceResult result) {
        if (result.entityHit != null && result.entityHit != ATTACKER && !(result.entityHit instanceof EntityArmorStand))
        {
            result.entityHit.attackEntityFrom(ModDamageSource.causeManaDamage(this, ATTACKER), DAMAGE);
        }

        if (!this.world.isRemote && result.entityHit != ATTACKER && !(result.getBlockPos() != null && this.world.getBlockState(result.getBlockPos()).getBlock() instanceof BlockBush))
        {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }

    public void onUpdate()
    {
        if(getType()==0){
            this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY, this.posZ, 0, 0, 0);
        }else if(getType()==1){
            this.world.spawnParticle(EnumParticleTypes.END_ROD, this.posX, this.posY, this.posZ, 0, 0, 0);
        }

        if(this.ticksExisted > 30){
            this.setDead();
        }
        super.onUpdate();
    }
}
