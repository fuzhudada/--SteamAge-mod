package com.fuzhudada.error.entity.weapon;

import com.fuzhudada.error.particle.ModParticleHandler;
import com.somebody.idlframewok.potion.ModPotions;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import java.util.List;

public class EntityPoison extends EntityCreature {
    private static final DataParameter<Integer> type = EntityDataManager.createKey(EntityPoison.class, DataSerializers.VARINT);
    public EntityLivingBase ATTACKER;

    public EntityPoison(World worldIn) {
        super(worldIn);
        this.setNoAI(true);
        this.setSize(0,0);
        this.setNoGravity(true);
        this.setSilent(true);
        this.experienceValue=0;
        setType(0);
    }

    public EntityPoison(World worldIn,int type) {
        super(worldIn);
        this.setNoAI(true);
        this.setSize(0,0);
        this.setNoGravity(true);
        this.setSilent(true);
        this.experienceValue=0;
        setType(type);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(type, 0);
    }

    public void setType(int T){
        this.dataManager.set(type,T);
    }

    public int getType(){
        return this.dataManager.get(type);
    }

    public void onLivingUpdate()
    {
        double r0=getType()==0?6:2;
        if(world.isRemote){
            for(int i=0;i<3;i++){
                double r=r0*getRNG().nextDouble();
                double a=2*Math.PI*getRNG().nextDouble();
                world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, posX+r*Math.cos(a), this.posY + 0.1, posZ+r*Math.sin(a), 0, 0.1, 0);

                r=r0*getRNG().nextDouble();
                a=2*Math.PI*getRNG().nextDouble();
                world.spawnParticle(ModParticleHandler.BUBBLE , posX+r*Math.cos(a), this.posY + 0.1, posZ+r*Math.sin(a), 0, 0, 0);
            }
        }else{
            List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, e-> e.isEntityAlive()
                    && (ATTACKER==null || e != ATTACKER)
                    && Math.abs(posY-e.posY)<1
                    && getDistance(e)<r0
                    && !(e instanceof EntityArmorStand)
                    && (!(ATTACKER instanceof EntityPlayer) || !(e instanceof EntityPlayer)));
            for(EntityLivingBase e:G){
                e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,30,1));
                if(ticksExisted%20==0){
                    if(getType()==0){
                        e.addPotionEffect(new PotionEffect(MobEffects.POISON,20,1));
                    }else{
                        e.addPotionEffect(new PotionEffect(ModPotions.JI_XING_ZHONG_DU ,20,1));
                    }
                }
            }
        }

        if(this.ticksExisted > 100){
            this.setDead();
        }
        super.onLivingUpdate();
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }
}
