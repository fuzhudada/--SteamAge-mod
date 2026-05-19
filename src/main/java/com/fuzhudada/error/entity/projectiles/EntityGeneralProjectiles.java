package com.fuzhudada.error.entity.projectiles;

import com.fuzhudada.error.ModDamageSource;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Objects;

public class EntityGeneralProjectiles extends EntityThrowable {
    public EntityLivingBase attacker;
    public ModDamageSource.EnumModDamageSource damageSource;
    public float damage;
    public int timeExisting;
    public boolean hasHurtCoolDown;
    public boolean isPenetrated;
    public impactHandler impactHandler;
    private static final DataParameter<String> particleType = EntityDataManager.createKey(EntityGeneralProjectiles.class, DataSerializers.STRING);
    public HashSet<Entity> set;

    public EntityGeneralProjectiles(World world){
        super(world);
        this.set=new HashSet<>();
    }

    public EntityGeneralProjectiles(World world, EntityLivingBase attacker, ModDamageSource.EnumModDamageSource damageSource, float damage, int timeExisting, boolean hasHurtCoolDown, boolean isPenetrated, impactHandler impactHandler){
        super(world);
        this.attacker = attacker;
        this.damageSource = damageSource;
        this.damage = damage;
        this.timeExisting = timeExisting;
        this.hasHurtCoolDown = hasHurtCoolDown;
        this.isPenetrated = isPenetrated;
        this.impactHandler=impactHandler;
        this.set=new HashSet<>();
    }

    protected void entityInit()
    {
        this.dataManager.register(particleType, "flame");
    }

    public void setParticleType(String particleType)
    {
        this.dataManager.set(EntityGeneralProjectiles.particleType,particleType);
    }

    public String getParticleType()
    {
        return this.dataManager.get(particleType);
    }

    protected void onImpact(RayTraceResult result) {
        if (result.entityHit != null
                && result.entityHit != attacker
                && !(result.entityHit instanceof EntityArmorStand)
                && !set.contains(result.entityHit))
        {
            if(!hasHurtCoolDown) {
                result.entityHit.hurtResistantTime = 0;
            }

            DamageSource source=null;
            if(damageSource != null){
                source=ModDamageSource.getDamageSource(damageSource,attacker,this);
            }

            if(source != null) {
                result.entityHit.attackEntityFrom(source, damage);
            }else{
                result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this,attacker), damage);
            }
            set.add(result.entityHit);

            if(!hasHurtCoolDown) {
                result.entityHit.motionX = 0;
                result.entityHit.motionZ = 0;
            }
        }

        if(impactHandler != null) {
            impactHandler.impact(this, result.entityHit);
        }

        if (!isPenetrated
                && !this.world.isRemote
                && result.entityHit != attacker
                && !(result.getBlockPos() != null && this.world.getBlockState(result.getBlockPos()).getBlock() instanceof BlockBush))
        {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }

    public void onUpdate()
    {
        this.world.spawnParticle(Objects.requireNonNull(EnumParticleTypes.getByName(this.getParticleType())), this.posX, this.posY, this.posZ, 0, 0, 0);

        if(this.ticksExisted > timeExisting && !this.world.isRemote){
            this.setDead();
        }
        super.onUpdate();
    }

    public static void shoot(World world, EntityLivingBase attacker, ModDamageSource.EnumModDamageSource damageSource, float damage, int timeExisting, String particle, boolean noGravity, float velocity, boolean hasHurtCoolDown, boolean isPenetrated, impactHandler impactHandler){
        Vec3d F = attacker.getLookVec();

        EntityGeneralProjectiles g=new EntityGeneralProjectiles(world,attacker,damageSource,damage,timeExisting,hasHurtCoolDown,isPenetrated,impactHandler);
        g.shoot(attacker, attacker.rotationPitch, attacker.rotationYaw, 0.0F, velocity, 0);
        g.setNoGravity(noGravity);
        g.setParticleType(particle);
        g.setPosition(attacker.posX + 1.5 * F.x, attacker.posY + attacker.getEyeHeight() + 1.5 * F.y, attacker.posZ + 1.5 * F.z);
        if (!world.isRemote) {
            world.spawnEntity(g);
        }
    }

    public static void shoot(World world, EntityLivingBase attacker, ModDamageSource.EnumModDamageSource damageSource, float damage, String particle){
        shoot(world,attacker,damageSource,damage,600,particle,true,2F,true,false,null);
    }

    public static void shootInSpecificDirection(World world, EntityLivingBase attacker, ModDamageSource.EnumModDamageSource damageSource, float damage, int timeExisting, String particle, boolean noGravity, float vx, float vy, float vz, boolean hasHurtCoolDown, boolean isPenetrated, impactHandler impactHandler){
        Vec3d F = new Vec3d(vx,vy,vz).normalize();

        EntityGeneralProjectiles g=new EntityGeneralProjectiles(world,attacker,damageSource,damage,timeExisting,hasHurtCoolDown,isPenetrated,impactHandler);
        g.motionX=attacker.motionX+vx;
        g.motionZ=attacker.motionZ+vz;
        g.motionY=vy;
        if(!attacker.onGround){
            g.motionY+=attacker.motionY;
        }
        g.setNoGravity(noGravity);
        g.setParticleType(particle);
        g.setPosition(attacker.posX + 1.5 * F.x, attacker.posY + attacker.getEyeHeight() + 1.5 * F.y, attacker.posZ + 1.5 * F.z);
        if (!world.isRemote) {
            world.spawnEntity(g);
        }
    }

    public static void shootInSpecificDirection(World world, EntityLivingBase attacker, ModDamageSource.EnumModDamageSource damageSource, float damage, String particle, float vx, float vy, float vz){
        shootInSpecificDirection(world,attacker,damageSource,damage,600,particle,true,vx,vy,vz,true,false,null);
    }

    public interface impactHandler{
        void impact(EntityGeneralProjectiles projectiles, Entity entityHit);
    }
}
