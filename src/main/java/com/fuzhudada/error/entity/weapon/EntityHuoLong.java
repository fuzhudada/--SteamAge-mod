package com.fuzhudada.error.entity.weapon;

import net.minecraft.entity.EntityCreature;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityHuoLong extends EntityCreature {
    private static final DataParameter<Integer> type = EntityDataManager.createKey(EntityHuoLong.class, DataSerializers.VARINT);
    public EntityHuoLong(World worldIn) {
        super(worldIn);
        this.setNoAI(true);
        this.setSize(0,0);
        this.setNoGravity(true);
        this.setSilent(true);
        this.experienceValue=0;
    }

    public static void spawnParticle(double x,double y,double z,int type,World world){
        EntityHuoLong huoLong=new EntityHuoLong(world);
        huoLong.setPosition(x,y,z);
        huoLong.setType(type);
        if(!world.isRemote){
            world.spawnEntity(huoLong);
        }
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(type, -1);
    }

    public void setType(int T){
        this.dataManager.set(type,T);
    }

    public int getType(){
        return this.dataManager.get(type);
    }

    public void onLivingUpdate()
    {
        if(getType()==0) {
            for (int i = 0; i < 36; i++) {
                this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY + 1.6, this.posZ, Math.cos(i * Math.PI / 18), 0, Math.sin(i * Math.PI / 18));
            }
        }else if(getType()==1){
            double x,y,z;
            for (int i = 0; i < 36; i++) {
                x=this.posX+Math.random()*4-2;
                y=this.posY+Math.random()*4-0.4;
                z=this.posZ+Math.random()*4-2;
                this.world.spawnParticle(EnumParticleTypes.TOTEM, x, y, z, 0.8*(x-this.posX), 0.8*(y-this.posY-1.6), 0.8*(z-this.posZ));
            }
        }else if(getType()==2){
            for (int i = 0; i < 36; i++) {
                this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX+Math.cos(i * Math.PI / 18), this.posY + 0.1, this.posZ+Math.sin(i * Math.PI / 18), 0, 0, 0);
                this.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.posX+Math.random()*Math.cos(Math.random()*2*Math.PI), this.posY+0.1, this.posZ+Math.random()*Math.sin(Math.random()*2*Math.PI), 0, 1, 0);
            }
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX, this.posY+0.5, this.posZ, 0, 0, 0);
        }else if(getType()==3){
            for(int i=0;i<15;i++) {
                this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY + 0.625, this.posZ, Math.random()*0.6-0.3, Math.random()*0.6-0.3, Math.random()*0.6-0.3,1);
            }
        }else if(getType()==4){
            for(int i=0;i<300;i++){
                double x=this.posX+Math.random()*Math.cos(2*Math.PI*Math.random());
                double y=this.posY+8*Math.random();
                double z=this.posZ+Math.random()*Math.sin(2*Math.PI*Math.random());
                this.world.spawnParticle(EnumParticleTypes.REDSTONE, x, y, z, 0, -0.2, 0);
            }

            for (int i = 0; i < 36; i++) {
                this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX+Math.cos(i * Math.PI / 18), this.posY + 0.1, this.posZ+Math.sin(i * Math.PI / 18), 0, 0, 0);
            }
        }else if(getType()==5){
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE,this.posX,this.posY,this.posZ,0,0,0);
        }else if(getType()==6){
            for (int i = 0; i < 36; i++) {
                this.world.spawnParticle(EnumParticleTypes.END_ROD, this.posX, this.posY, this.posZ, 0.15*Math.cos(i * Math.PI / 18), 0, 0.15*Math.sin(i * Math.PI / 18));
            }
        }else if(getType()==7){
            double x,y,z;
            for (int i = 0; i < 8; i++) {
                x=this.posX+3*Math.random()-1.5;
                y=this.posY+3*Math.random()-1.5;
                z=this.posZ+3*Math.random()-1.5;
                for(double j=0; j < 1; j+=0.07) {
                    this.world.spawnParticle(EnumParticleTypes.END_ROD, (1-j)*this.posX+j*x, (1-j)*this.posY+j*y, (1-j)*this.posZ+j*z, 0, 0, 0);
                }
            }
        }else if(getType()==8){
            for (int a = 0; a < 150; a++) {
                this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, Math.random() - 0.5, 0, Math.random() - 0.5);
            }
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE,this.posX,this.posY,this.posZ,0,0,0);
        }else if(getType()==9){
            for (int a = 0; a < 150; a++) {
                this.world.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.posX, this.posY, this.posZ, Math.random() - 0.5, 0, Math.random() - 0.5);
            }
        }else if(getType()==10){
            Vec3d F=this.getLookVec();
            for(int i=0;i<40;i++){
                this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX+i*F.x/2, this.posY+0.2, this.posZ+i*F.z/2, 0, 0, 0);
            }
        }else if(getType()==11){
            for(int i=0;i<40;i++){
                world.spawnParticle(EnumParticleTypes.SMOKE_LARGE,this.posX,this.posY,this.posZ,0.2*Math.random()-0.1,-0.5,0.2*Math.random()-0.1);
            }
            for(int i=0;i<15;i++){
                world.spawnParticle(EnumParticleTypes.LAVA,this.posX,this.posY+1,this.posZ,0.2*Math.random()-0.1,-0.5,0.2*Math.random()-0.1);
            }
            for(int i=0;i<50;i++){
                world.spawnParticle(EnumParticleTypes.SMOKE_LARGE,this.posX,this.posY+0.8+0.4*Math.random(),this.posZ,0.6*Math.random()-0.3,0,0.6*Math.random()-0.3);
            }
        }else if(getType()==12){
            for (int a = 0; a < 50; a++) {
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX + Math.random() - 0.5, posY + 1, posZ + Math.random() - 0.5, 0, 0.2, 0);
            }
        }else if(getType()==13){
            for (int i = 0; i < 72; i++) {
                this.world.spawnParticle(EnumParticleTypes.CLOUD, this.posX, this.posY, this.posZ, Math.cos(i * Math.PI / 36), Math.random()*0.3-0.15, Math.sin(i * Math.PI / 36));
            }
        }else if(getType()==14){
            for (int i = 0; i < 30; i++) {
                double x=posX+Math.random()*2-1;
                double y=posY+Math.random()*2-1;
                double z=posZ+Math.random()*2-1;
                this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, x,y,z, 0.1*(posX-x),0.1*(posY-y),0.1*(posZ-z));
            }
        }else if(getType()==15){
            for (int i = 0; i < 30; i++) {
                this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX, this.posY, this.posZ, 0.5*Math.random()-0.25, 0.5*Math.random()-0.25, 0.5*Math.random()-0.25);
            }
        }
        this.setDead();
        super.onLivingUpdate();
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }
}
