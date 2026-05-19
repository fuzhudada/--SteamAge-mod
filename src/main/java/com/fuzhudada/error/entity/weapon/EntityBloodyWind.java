package com.fuzhudada.error.entity.weapon;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.particle.ModParticleHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.List;

public class EntityBloodyWind extends EntityCreature {
    private static final DataParameter<String> attackerName = EntityDataManager.createKey(EntityBloodyWind.class, DataSerializers.STRING);
    public float damage;
    public EntityLivingBase attacker;
    public HashSet<Entity> set;

    public EntityBloodyWind(World worldIn) {
        super(worldIn);
        this.setNoAI(true);
        this.setSize(0,0);
        this.setNoGravity(true);
        this.setSilent(true);
        this.experienceValue=0;
        this.set=new HashSet<>();
    }

    public EntityBloodyWind(World worldIn, EntityLivingBase attacker, float damage){
        this(worldIn);
        setAttackerName(attacker.getName());
        this.damage=damage;
        this.attacker=attacker;
    }

    protected void entityInit()
    {
        this.dataManager.register(attackerName, "");
        super.entityInit();
    }

    public void setAttackerName(String attackerName)
    {
        this.dataManager.set(EntityBloodyWind.attackerName,attackerName);
    }

    public String getAttackerName()
    {
        return this.dataManager.get(attackerName);
    }

    public double getVx(){
        return getLookVec().x;
    }

    public double getVy(){
        return getLookVec().y;
    }

    public double getVz(){
        return getLookVec().z;
    }

    public void onUpdate()
    {
        if(this.ticksExisted >= 100){
            this.setDead();
        }else{
            setPosition(posX+0.15*getVx(),posY+0.15*getVy(),posZ+0.15*getVz());

            if(world.isRemote){
                spawnWindParticle();

                for(int i=0;i<(100-ticksExisted)/4;i++){
                    world.spawnParticle(ticksExisted==99? EnumParticleTypes.REDSTONE:ModParticleHandler.BLOODY_WIND,posX+i*getVx()*0.6,posY+0.1+i*getVy()*0.6,posZ+i*getVz()*0.6,0,0,0);
                }
            }

            List<Entity> G = world.getEntities(Entity.class,
                    e -> getDistance(e)<3
                            && (getAttackerName().isEmpty() || !e.getName().equals(getAttackerName()))
                            && e.posY>=this.posY-1
                            && e.isEntityAlive()
                            && !e.equals(this)
                            && !(e instanceof EntityItem)
                            && !(e instanceof EntityArmorStand));
            for(Entity e:G){
                if(!set.contains(e)){
                    e.attackEntityFrom(ModDamageSource.causeManaDamage(this,attacker),damage);
                    set.add(e);
                }
                double d=getDistance(e);
                if(d>0){
                    d=Math.min(5,d);
                    e.motionX=(e.posX-posX)/d;
                    e.motionY=1;
                    e.motionZ=(e.posZ-posZ)/d;
                }
                world.playSound(null, posX, posY, posZ, SoundEvents.ENTITY_WITHER_HURT, SoundCategory.PLAYERS, 2F, world.rand.nextFloat() * 0.1F + 0.9F);
            }
        }

        super.onUpdate();
    }

    private void spawnWindParticle(){
        for(int i=1;i<=6;i++){
            for(int j=0;j<20;j++){
                world.spawnParticle(ticksExisted==99? EnumParticleTypes.REDSTONE:ModParticleHandler.BLOODY_WIND,posX+0.2885*i*Math.cos(j*0.2*Math.PI),posY+0.5*i,posZ+0.2885*i*Math.sin(j*0.2*Math.PI),0,0,0);
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }
}
