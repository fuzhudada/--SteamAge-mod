package com.fuzhudada.error.entity.model;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityLightningCowHorn extends EntityCreature {
    public EntityLightningCowHorn(World worldIn) {
        super(worldIn);
        this.setSize(this.width, this.height * 2F);
        this.rotationYaw = (float) (Math.PI*3/2);
        this.rotationYawHead = this.rotationYaw;
        this.setNoAI(true);
        this.setSilent(true);
        this.experienceValue=0;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(100D);
    }

    @Override
    public boolean attackEntityFrom(@Nonnull DamageSource source , float amount) {
        return source.getDamageType().equals("electrical");
    }

    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn)
    {
        return null;
    }

    protected SoundEvent getDeathSound()
    {
        return null;
    }

    public boolean canBePushed()
    {
        return false;
    }

    protected void collideWithEntity(@Nonnull Entity entityIn) {}

    protected void collideWithNearbyEntities() {}

    @Override
    public void onUpdate() {
        super.onUpdate();
        if(ticksExisted>600) setDead();
    }

    @Override
    public float getEyeHeight() {
        return 3.75F;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox()
    {
        return this.isEntityAlive() ? this.getEntityBoundingBox() : null;
    }
}
