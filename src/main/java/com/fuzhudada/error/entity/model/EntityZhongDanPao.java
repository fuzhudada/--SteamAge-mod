package com.fuzhudada.error.entity.model;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.enchantments.ModEnchantmentInit;
import com.fuzhudada.error.entity.weapon.EntityHuoLong;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class EntityZhongDanPao extends EntityCreature {
    public final EntityLivingBase Igniter;
    private final EntityLivingBase target;
    private final float damage;

    public EntityZhongDanPao(World worldIn) {
        super(worldIn);
        this.isImmuneToFire=true;
        this.Igniter=null;
        this.target=null;
        this.damage=320;
    }

    public EntityZhongDanPao(World worldIn,EntityLivingBase igniter,EntityLivingBase target,float damage){
        super(worldIn);
        this.isImmuneToFire=true;
        this.Igniter=igniter;
        this.target=target;
        this.damage=damage;
    }

    protected void entityInit()
    {
        super.entityInit();
    }

    public void onUpdate()
    {
        this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX, this.posY, this.posZ, 0, 0, 0);
        float f1=(float)Math.toDegrees(Math.atan(this.motionZ/this.motionX));
        float f2=this.motionX>0?f1-90:f1+90;
        this.rotationYaw=this.rotationYawHead=this.renderYawOffset=f2>90?f2-270:f2+90;

        float f5=(float)Math.sqrt(this.motionX*this.motionX+this.motionZ*this.motionZ);
        this.rotationPitch= (float)-Math.toDegrees(Math.atan(this.motionY/f5));
        this.setNoGravity(true);
        if(target != null) {
            this.motionX = this.motionX + 0.01*(target.posX-this.posX);
            this.motionY = this.motionY + 0.007*(target.posY+target.getEyeHeight()-this.posY);
            this.motionZ = this.motionZ + 0.01*(target.posZ-this.posZ);
        }

        if(this.ticksExisted>100 || (target != null && getDistance(target)<3)){
            explode();
        }
        super.onUpdate();
    }

    protected void collideWithEntity(@Nonnull Entity entityIn) {
        if(!entityIn.world.isRemote) {
            explode();
        }
        super.collideWithEntity(entityIn);
    }

    public void fall(float distance, float damageMultiplier)
    {
    }

    private void explode(){
        List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class,
                e -> e.getDistanceSq(this)<16
                        && e.isEntityAlive()
                        && (!(Igniter instanceof EntityPlayer) || e.equals(Igniter) || !(e instanceof EntityPlayer))
        && (e != Igniter || !ModEnchantmentInit.HasCiTiao(e,ModEnchantmentInit.ZHA_DAN_DA_SHI)) && !(e instanceof EntityArmorStand));
        for(EntityLivingBase e:G){
            e.attackEntityFrom(ModDamageSource.causeRpgDamage(this,Igniter), damage);
            if((this.posX - e.posX)*(this.posX - e.posX)+(this.posZ - e.posZ)*(this.posZ - e.posZ) != 0) {
                e.knockBack(this, 0.8f, this.posX - e.posX, this.posZ - e.posZ);
            }
        }
        this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4F, this.getRNG().nextFloat() * 0.1F + 0.9F);
        EntityHuoLong.spawnParticle(this.posX,this.posY,this.posZ,5,world);
        this.setDead();
    }
}
