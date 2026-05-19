package com.fuzhudada.error.entity.projectiles;

import net.minecraft.block.BlockBush;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public class EntityNuMu extends EntityThrowable {
    public EntityNuMu(World worldIn)
    {
        super(worldIn);
    }

    public EntityLivingBase ATTACKER;
    private static final DataParameter<Boolean> critical = EntityDataManager.createKey(EntityNuMu.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> type = EntityDataManager.createKey(EntityNuMu.class, DataSerializers.VARINT);
    public float DAMAGE;
    public boolean huoshi;

    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null && result.entityHit != ATTACKER && !(result.entityHit instanceof EntityArmorStand))
        {
            result.entityHit.hurtResistantTime = 0;
            if(!getIsCritical()) {
                result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, ATTACKER), DAMAGE);
            }
            if(result.entityHit instanceof EntityLiving && getType()==2){
                EntityLiving entity = (EntityLiving)result.entityHit;
                entity.addPotionEffect(new PotionEffect(com.somebody.idlframewok.potion.ModPotions.ZHI_TI_PO_HUAI, 60, 1));
            }
            if(getType()==1 || huoshi){
                result.entityHit.setFire(5);
            }
            if (result.entityHit != null && result.entityHit != ATTACKER && getIsCritical())
            {
                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
                for (int i = 0; i <= G.size() - 1; i++) {
                    if (Math.sqrt((G.get(i).posX - result.entityHit.posX) * (G.get(i).posX - result.entityHit.posX) + (G.get(i).posY - result.entityHit.posY) * (G.get(i).posY - result.entityHit.posY) + (G.get(i).posZ - result.entityHit.posZ) * (G.get(i).posZ - result.entityHit.posZ)) < 3
                            && G.get(i) != ATTACKER && G.get(i) != null && !(G.get(i) instanceof EntityArmorStand)) {
                        G.get(i).attackEntityFrom(DamageSource.causeThrownDamage(this,ATTACKER),DAMAGE);
                        if(getType()==2){
                            G.get(i).setFire(5);
                        }
                    }
                }
                if(getType()==2){
                    for(int a=0;a<100;a++){
                        this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX + (this.rand.nextDouble() - 0.5D) * 3, this.posY + (this.rand.nextDouble() - 0.5D) * 3, this.posZ + (this.rand.nextDouble() - 0.5D) * 3, 0.0D, 0.0D, 0.0D);
                    }
                }else{
                    this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
                }
            }
        }

        if (!this.world.isRemote && result.entityHit != ATTACKER && !(result.getBlockPos() != null && this.world.getBlockState(result.getBlockPos()).getBlock() instanceof BlockBush))
        {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }

    protected void entityInit()
    {
        this.dataManager.register(critical, false);
        this.dataManager.register(type, 0);
    }

    public void setIsCritical(boolean critical)
    {
        this.dataManager.set(EntityNuMu.critical,critical);
    }

    public boolean getIsCritical()
    {
        return (this.dataManager.get(critical));
    }

    public void setType(int T){
        this.dataManager.set(type,T);
    }

    public int getType(){
        return this.dataManager.get(type);
    }

    public void onUpdate()
    {
        if(getIsCritical()){
            this.world.spawnParticle(EnumParticleTypes.REDSTONE, this.posX, this.posY, this.posZ, 0,0,0);
        }else{
            this.world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, this.posX, this.posY, this.posZ, 0,0,0);
        }
        if(this.ticksExisted > 600){
            this.setDead();
        }
        super.onUpdate();
    }
}
