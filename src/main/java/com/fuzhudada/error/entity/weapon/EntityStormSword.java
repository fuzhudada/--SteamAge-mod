package com.fuzhudada.error.entity.weapon;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.particle.ModParticleHandler;
import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class EntityStormSword extends EntityCreature {
    private static final DataParameter<String> attackerName = EntityDataManager.createKey(EntityStormSword.class, DataSerializers.STRING);
    private static final DataParameter<Float> range = EntityDataManager.createKey(EntityStormSword.class, DataSerializers.FLOAT);
    private static final DataParameter<Integer> type = EntityDataManager.createKey(EntityStormSword.class, DataSerializers.VARINT);
    public float damage;
    public EntityLivingBase attacker;

    public EntityStormSword(World worldIn) {
        super(worldIn);
        this.setNoAI(true);
        this.setSize(0, 0);
        this.setNoGravity(true);
        this.setSilent(true);
        this.experienceValue = 0;
    }

    public EntityStormSword(World worldIn, EntityLivingBase attacker, float damage,float range){
        this(worldIn);
        setAttackerName(attacker.getName());
        this.damage=damage;
        this.attacker=attacker;
        setRange(range);
    }

    public EntityStormSword(World worldIn, EntityLivingBase attacker, float damage,float range,int type){
        this(worldIn,attacker,damage,range);
        setType(type);
    }

    protected void entityInit()
    {
        dataManager.register(attackerName, "");
        dataManager.register(range,0F);
        dataManager.register(type,0);
        super.entityInit();
    }

    public void setAttackerName(String attackerName)
    {
        dataManager.set(EntityStormSword.attackerName,attackerName);
    }

    public String getAttackerName()
    {
        return dataManager.get(attackerName);
    }

    public void setRange(float range)
    {
        dataManager.set(EntityStormSword.range,range);
    }

    public float getRange()
    {
        return dataManager.get(range);
    }

    public void setType(int type)
    {
        dataManager.set(EntityStormSword.type,type);
    }

    public int getType()
    {
        return dataManager.get(type);
    }

    public void onUpdate()
    {
        int type=getType();
        if(ticksExisted >= getExistTime()){
            if(type==1){
                List<Entity> G = world.getEntities(EntityPlayer.class,
                        e -> getDistance(e)<getRange()
                                && e.posY>=this.posY-1
                                && e.isEntityAlive());
                for(Entity e:G){
                    ((EntityPlayer)e).heal(685);
                }
            }

            this.setDead();
        }else{
            if(world.isRemote){
                spawnWindParticle();
            }

            List<Entity> G = world.getEntities(Entity.class,
                    e -> getDistance(e)<getRange()
                            && (getAttackerName().isEmpty() || !e.getName().equals(getAttackerName()))
                            && e.posY>=this.posY-1
                            && e.isEntityAlive()
                            && !e.equals(this)
                            && !(e instanceof EntityItem)
                            && !(e instanceof EntityArmorStand)
                            && (!(attacker instanceof EntityPlayer) || !(e instanceof EntityPlayer)));
            for(Entity e:G){
                if(type != 1) e.attackEntityFrom(ModDamageSource.causeManaDamage(this,attacker),damage);
                double d=getDistance(e);
                if(d>0){
                    d=Math.min(5,d);
                    e.motionX=(e.posX-posX)/d;
                    e.motionY=1;
                    e.motionZ=(e.posZ-posZ)/d;
                }
            }

            if(ticksExisted==1){
                if(type==0){
                    world.playSound(null, this.posX, this.posY, this.posZ, ModSoundHandler.STORM_SWORD, SoundCategory.PLAYERS, 4F, this.getRNG().nextFloat() * 0.1F + 0.9F);
                }
            }
        }

        super.onUpdate();
    }

    private void spawnWindParticle(){
        int type=getType();
        if(type==0){
            float range=getRange();
            for(int j=0;j<range*3;j++){
                double dx=Math.cos(j*2*Math.PI/range/3);
                double dz=Math.sin(j*2*Math.PI/range/3);
                world.spawnParticle(ModParticleHandler.CLOUD ,posX+range*dx,posY,posZ+range*dz,-0.4*dz,0.6,0.4*dx);
            }
        }else if(type==1){
            float range=getRange();
            int N=15,M=3;
            double f=0.045;
            boolean flag=ticksExisted>=getExistTime()-3;
            EnumParticleTypes particle=flag?ModParticleHandler.BLOOD:EnumParticleTypes.REDSTONE;
            for(int j=0;j<range*N;j++){
                double dx=Math.cos(j*2*Math.PI/range/N);
                double dz=Math.sin(j*2*Math.PI/range/N);
                for(int k=0;k<M;k++){
                    double x=posX+range*dx+Math.random()*0.3-0.15;
                    double y=posY+1+Math.random()*0.3-0.15;
                    double z=posZ+range*dz+Math.random()*0.3-0.15;
                    world.spawnParticle(particle,x,y,z,flag?(posX-x)*f:0,flag?(posY+1-y)*f:0,flag?(posZ-z)*f:0);
                }
            }
            for(int j=0;j<range*N;j++){
                double dx=Math.cos(j*2*Math.PI/range/N);
                double dy=Math.sin(j*2*Math.PI/range/N);
                for(int k=0;k<M;k++){
                    double x=posX+range*dx+Math.random()*0.3-0.15;
                    double y=posY+1+range*dy+Math.random()*0.3-0.15;
                    double z=posZ+Math.random()*0.3-0.15;
                    world.spawnParticle(particle,x,y,z,flag?(posX-x)*f:0,flag?(posY+1-y)*f:0,flag?(posZ-z)*f:0);
                }
            }
            for(int j=0;j<range*N;j++){
                double dy=Math.cos(j*2*Math.PI/range/N);
                double dz=Math.sin(j*2*Math.PI/range/N);
                for(int k=0;k<M;k++){
                    double x=posX+Math.random()*0.3-0.15;
                    double y=posY+1+range*dy+Math.random()*0.3-0.15;
                    double z=posZ+range*dz+Math.random()*0.3-0.15;
                    world.spawnParticle(particle,x,y,z,flag?(posX-x)*f:0,flag?(posY+1-y)*f:0,flag?(posZ-z)*f:0);
                }
            }
        }
    }

    int getExistTime(){
        int type=getType();
        if(type==1){
            return 200;
        }
        return 100;
    }

    @Override
    public boolean attackEntityFrom(@Nonnull DamageSource source, float amount) {
        return false;
    }
}
