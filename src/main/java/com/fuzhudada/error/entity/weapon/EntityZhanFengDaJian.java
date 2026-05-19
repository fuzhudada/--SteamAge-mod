package com.fuzhudada.error.entity.weapon;

import com.fuzhudada.error.ModDamageSource;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.List;

public class EntityZhanFengDaJian extends EntityCreature {
    public EntityZhanFengDaJian(World worldIn) {
        super(worldIn);
        this.setNoAI(true);
        this.setSize(0,0);
        this.setNoGravity(true);
        this.setSilent(true);
        this.experienceValue=0;
        this.set=new HashSet<>();
    }

    public EntityLivingBase attacker;
    HashSet<EntityLivingBase> set;

    @Override
    public boolean attackEntityFrom(@Nonnull DamageSource source , float amount) {
        return false;
    }

    public void onUpdate() {
        super.onUpdate();

        for(int i=0;i<3;i++){
            double a=world.rand.nextDouble()*2*Math.PI;
            double x=posX+5*Math.cos(a);
            double y=posY+3*world.rand.nextDouble();
            double z=posZ+5*Math.sin(a);
            world.spawnParticle(EnumParticleTypes.DRAGON_BREATH,x,y,z,0.1*(posX-x),0.1*(posY-y),0.1*(posZ-z));
        }

        if(this.ticksExisted % 3 == 0){
            drawCircle(ticksExisted/10F,0,0);
        }

        if(ticksExisted>=200){
            setDead();
        }
    }

    private void drawCircle(double r,double f1,double f2){
        for(int i=0;i<10*r;i++){
            final boolean b1 = i > r * (f1 + f2) / 36;
            if(f1>f2){
                boolean b = i < r * (f1 - f2) / 36;
                if(f1+f2<=360){
                    if(b || b1) drawPoint(r,i);
                }else{
                    if(i>r*(f1+f2)/36-360 && b) drawPoint(r,i);
                }
            }else{
                if(b1 && i<r*(f1-f2)/36+360) drawPoint(r,i);
            }
        }

        if(attacker != null) handleDamage(r,f1,f2);
    }

    private void drawPoint(double r,double i){
        double x=posX+0.5+r*Math.cos(i*Math.PI/5/r);
        double z=posZ+0.5+r*Math.sin(i*Math.PI/5/r);
        world.spawnParticle(EnumParticleTypes.END_ROD,x,posY+1,z,0,0,0);
    }

    private void handleDamage(double r,double f1,double f2){
        List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, e->e.isEntityAlive()
                && e != attacker
                && !(e instanceof EntityArmorStand)
                && !set.contains(e)
                && isPosCanBeDamage(r,f1,f2,e)
                && (!(attacker instanceof EntityPlayer) || !(e instanceof EntityPlayer)));
        for (int i = 0; i <= G.size() - 1; i++) {
            G.get(i).attackEntityFrom(ModDamageSource.causeManaDamage(this, attacker), 470);
            set.add(G.get(i));
        }
    }

    private boolean isPosCanBeDamage(double r,double f1,double f2,EntityLivingBase b){
        if(posY+1-b.posY<-0.5 || posY+1-b.posY>2.5) return false;

        double dis=Math.sqrt((posX-b.posX)*(posX-b.posX)+(posZ-b.posZ)*(posZ-b.posZ));
        if(r-dis<0 || r-dis>1) return false;

        double x1=Math.cos((f1-f2)*Math.PI/180);
        double z1=Math.sin((f1-f2)*Math.PI/180);
        double x2=Math.cos((f1+f2)*Math.PI/180);
        double z2=Math.sin((f1+f2)*Math.PI/180);
        double x3=b.posX-posX;
        double z3=b.posZ-posZ;
        return Math.abs(Math.acos((x1*x3+z1*z3)/dis)+Math.acos((x2*x3+z2*z3)/dis)-Math.PI*f2/90)>0.0001;
    }
}
