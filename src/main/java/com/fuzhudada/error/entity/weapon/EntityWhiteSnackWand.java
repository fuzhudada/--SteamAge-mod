package com.fuzhudada.error.entity.weapon;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.packet.PacketHandler;
import com.fuzhudada.error.packet.ParticleAnimation.Circle;
import com.fuzhudada.error.packet.ParticleAnimation.Line;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.List;

public class EntityWhiteSnackWand extends EntityCreature {
    public EntityWhiteSnackWand(World worldIn) {
        super(worldIn);
        this.setNoAI(true);
        this.setSize(0,0);
        this.setNoGravity(true);
        this.setSilent(true);
        this.experienceValue=0;
    }

    public EntityLivingBase ATTACKER;
    public float DAMAGE;

    public void onLivingUpdate()
    {
        List<EntityPlayerMP> P = world.getEntities(EntityPlayerMP.class, e->getDistance(e)<64);
        for (EntityPlayerMP player : P) {
            PacketHandler.INSTANCE.sendTo(new Circle("END_ROD",posX,posY+0.1,posZ,0,0,0,0,1,0,5,80),player);
            PacketHandler.INSTANCE.sendTo(new Circle("END_ROD",posX,posY+0.1,posZ,0,0,0,0,1,0,4.5,72),player);
            PacketHandler.INSTANCE.sendTo(new Line("END_ROD",posX+5,posY+0.1,posZ,posX-2.5,posY+0.1,posZ+4.330127,0,0,0,0.1,20),player);
            PacketHandler.INSTANCE.sendTo(new Line("END_ROD",posX-2.5,posY+0.1,posZ-4.330127,posX-2.5,posY+0.1,posZ+4.330127,0,0,0,0.1,20),player);
            PacketHandler.INSTANCE.sendTo(new Line("END_ROD",posX+5,posY+0.1,posZ,posX-2.5,posY+0.1,posZ-4.330127,0,0,0,0.1,20),player);
            PacketHandler.INSTANCE.sendTo(new Line("END_ROD",posX+2.5,posY+0.1,posZ+4.330127,posX-5,posY+0.1,posZ,0,0,0,0.1,20),player);
            PacketHandler.INSTANCE.sendTo(new Line("END_ROD",posX+2.5,posY+0.1,posZ-4.330127,posX-5,posY+0.1,posZ,0,0,0,0.1,20),player);
            PacketHandler.INSTANCE.sendTo(new Line("END_ROD",posX+2.5,posY+0.1,posZ+4.330127,posX+2.5,posY+0.1,posZ-4.330127,0,0,0,0.1,20),player);
        }

        if(ticksExisted==1 && ATTACKER != null){
            List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, e-> e.isEntityAlive()
                    && e != ATTACKER
                    && Math.abs(posY-e.posY)<1
                    && getDistance(e)<5
                    && !(e instanceof EntityArmorStand)
                    && !(e instanceof EntityWhiteSnackWand));
            for(EntityLivingBase e:G){
                e.attackEntityFrom(ModDamageSource.causeManaDamage(this, ATTACKER), DAMAGE);
            }
        }

        List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class, e-> e.isEntityAlive()
                && e != ATTACKER
                && Math.abs(posY-e.posY)<1
                && getDistance(e)<5
                && !(e instanceof EntityArmorStand)
                && !(e instanceof EntityWhiteSnackWand));
        for(EntityLivingBase e:G){
            e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,2,10));
        }

        if(ticksExisted==60){
            setDead();
        }

        super.onLivingUpdate();
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }
}
