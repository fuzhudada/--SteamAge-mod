package com.fuzhudada.error.entity.model;

import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.List;

public class EntityFireHorse extends EntityHorse{
    private static final DataParameter<Integer> RunTimer = EntityDataManager.createKey(EntityRiderElephant.class, DataSerializers.VARINT);
    public int RunCoolDown;

    public EntityFireHorse(World worldIn) {
        super(worldIn);
        experienceValue=0;
        this.setHealth(this.getMaxHealth());
        this.stepHeight = 1.0F;
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1400.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(RunTimer, 0);
    }

    public void setRunTimer(int T){
        this.dataManager.set(RunTimer,T);
    }

    public int getRunTimer(){
        return this.dataManager.get(RunTimer);
    }

    @Override
    public void onUpdate() {
        int t1= getRunTimer();
        if(t1>0){
            setRunTimer(t1-1);

            List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, e->e.isEntityAlive()
                    && e.getDistanceSq(this)<3*3
                    && !e.equals(this)
                    && !(e instanceof EntityArmorStand));
            for (EntityLivingBase e:G) {
                if((posX - e.posX)*(posX - e.posX)+(posZ - e.posZ)*(posZ - e.posZ) != 0) {
                    e.knockBack(this, 1.2F, posX - e.posX, posZ - e.posZ);
                }
            }

            if(world.isRemote){
                for(int i=0;i<3;i++){
                    world.spawnParticle(EnumParticleTypes.FLAME,posX,posY+0.8,posZ,0.8*Math.random()-0.4,0.8*Math.random()-0.4,0.8*Math.random()-0.4);
                }
            }
        }

        if(RunCoolDown >0){
            RunCoolDown--;

            if(RunCoolDown==0){
                this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
            }
        }

        if(ticksExisted>2400) setDead();
        super.onUpdate();
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if(getRunTimer()>0){
            return false;
        }else{
            return super.attackEntityFrom(source, amount);
        }
    }

    public void startRun(){
        if(RunCoolDown <=0){
            world.playSound(null, this.posX, this.posY, this.posZ, ModSoundHandler.FIRE_HORSE , SoundCategory.MASTER, 2F, this.getRNG().nextFloat() * 0.1F + 0.9F);
            this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.7D);
            setRunTimer(200);
            RunCoolDown=400;
        }
    }
}