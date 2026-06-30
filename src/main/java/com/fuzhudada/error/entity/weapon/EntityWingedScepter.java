package com.fuzhudada.error.entity.weapon;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.packet.PacketHandler;
import com.fuzhudada.error.packet.ParticleAnimation.Line;
import com.somebody.idlframewok.potion.ModPotions;
import javafx.util.Pair;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.List;

public class EntityWingedScepter extends EntityCreature {
    public float damage, range;
    public EntityLivingBase attacker;
    public int num;

    public List<Pair<Double,Double>> pos;

    public EntityWingedScepter(World worldIn) {
        super(worldIn);
    }

    public EntityWingedScepter(World worldIn, EntityLivingBase attacker, float damage, float range, int num) {
        super(worldIn);
        this.attacker=attacker;
        this.damage=damage;
        this.range=range;
        this.num=num;
    }

    @Override
    public void onUpdate() {
        if(ticksExisted <= 5*num){
            if(ticksExisted % 5 == 0){
                addPos();
            }
        }else if(ticksExisted == 5*num+20){
            for(int i=0;i<pos.size();i++){
                double x=pos.get(i).getKey();
                double z=pos.get(i).getValue();

                List<EntityPlayerMP> P = world.getEntities(EntityPlayerMP.class, e->getDistance(e)<64);
                for (EntityPlayerMP player : P) {
                    PacketHandler.INSTANCE.sendTo(new Line("TOTEM",x,posY,z,x,posY+15,z,0,0,0,0,100) ,player);
                }

                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class,
                        e -> e.getDistance(x,posY,z) < 1
                                && !e.equals(attacker)
                                && e.isEntityAlive()
                                && !e.equals(this)
                                && !(e instanceof EntityArmorStand));
                for(EntityLivingBase e:G){
                    e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,40,10));
                    e.addPotionEffect(new PotionEffect(ModPotions.ZAI_SHENG_TING_ZHI,40,10));
                    e.attackEntityFrom(ModDamageSource.causeManaDamage(this,attacker),damage);
                }
            }

            this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE , SoundCategory.MASTER, 2F, this.getRNG().nextFloat() * 0.1F + 0.9F);
            setDead();
        }

        for(int i=0;i<pos.size();i++){
            double x=pos.get(i).getKey();
            double z=pos.get(i).getValue();

            List<EntityPlayerMP> P = world.getEntities(EntityPlayerMP.class, e->getDistance(e)<64);
            for (EntityPlayerMP player : P) {
                PacketHandler.INSTANCE.sendTo(new Line("END_ROD",x,posY,z,x,posY+15,z,0,0,0,0,100) ,player);
            }
        }

        super.onUpdate();
    }

    public void addPos(){
        double x=posX+2*Math.random()*range-range;
        double z=posZ+2*Math.random()*range-range;
        pos.add(new Pair<>(x, z));
    }
}
