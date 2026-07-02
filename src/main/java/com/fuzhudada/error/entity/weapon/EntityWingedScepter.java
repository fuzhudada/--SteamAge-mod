package com.fuzhudada.error.entity.weapon;

import com.fuzhudada.error.ModDamageSource;
import com.somebody.idlframewok.potion.ModPotions;
import javafx.util.Pair;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.ArrayList;
import java.util.List;

public class EntityWingedScepter extends EntityCreature {
    public float damage, range;
    public EntityLivingBase attacker;
    public int num, freq;

    public List<Pair<Double,Double>> warps;

    public EntityWingedScepter(World worldIn) {
        super(worldIn);
        this.setNoAI(true);
        this.setSize(0,0);
        this.setNoGravity(true);
        this.setSilent(true);
        this.experienceValue=0;
    }

    public EntityWingedScepter(World worldIn, EntityLivingBase attacker, float damage, float range, int num,int freq) {
        super(worldIn);
        this.attacker=attacker;
        this.damage=damage;
        this.range=range;
        this.num=num;
        if(freq==0) freq++;
        this.freq=freq;
        warps =new ArrayList<>();

        this.setNoAI(true);
        this.setSize(0,0);
        this.setNoGravity(true);
        this.setSilent(true);
        this.experienceValue=0;
    }

    @Override
    public void onUpdate() {
        if(!world.isRemote){
            if(ticksExisted <= freq*num){
                if(ticksExisted % freq == 0){
                    addPos();
                    this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL , SoundCategory.MASTER, 2F, this.getRNG().nextFloat() * 0.1F + 0.9F);
                }
            }else if(ticksExisted == freq*num+20){
                if(warps != null){
                    for (Pair<Double, Double> po : warps) {
                        double x = po.getKey();
                        double z = po.getValue();

                        ((WorldServer)world).spawnParticle(EnumParticleTypes.TOTEM,x,posY,z,150,0,10,0,0.2);

                        List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class,
                                e -> e.getDistance(x, posY, z) < 2
                                        && !e.equals(attacker)
                                        && e.isEntityAlive()
                                        && !e.equals(this)
                                        && !(e instanceof EntityArmorStand));
                        for (EntityLivingBase e : G) {
                            e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 10));
                            e.addPotionEffect(new PotionEffect(ModPotions.ZAI_SHENG_TING_ZHI, 40, 10));
                            e.attackEntityFrom(ModDamageSource.causeManaDamage(this, attacker), damage);
                        }
                    }
                }

                this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE , SoundCategory.MASTER, 2F, this.getRNG().nextFloat() * 0.1F + 0.9F);
                setDead();
            }

            if(warps != null){
                for (Pair<Double, Double> po : warps) {
                    double x = po.getKey();
                    double z = po.getValue();

                    ((WorldServer)world).spawnParticle(EnumParticleTypes.END_ROD,x,posY,z,30,0,10,0,0.01);
                }
            }
        }

        super.onUpdate();
    }

    public void addPos(){
        double x=posX+2*Math.random()*range-range;
        double z=posZ+2*Math.random()*range-range;
        warps.add(new Pair<>(x, z));
    }
}
