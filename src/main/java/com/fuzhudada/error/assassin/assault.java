package com.fuzhudada.error.assassin;

import com.fuzhudada.error.packet.MessageAssault;
import com.fuzhudada.error.packet.PacketHandler;
import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

public class assault {
    public EntityPlayerMP player;
    public EntityLivingBase currentTarget;
    public ArrayList<EntityLivingBase> list;
    public int timer,assaultTimer;
    public boolean statue;

    public assault(EntityPlayerMP player){
        this.player=player;
        currentTarget=null;
        list=new ArrayList<>();
        timer=0;
        statue=true;
    }

    public void onUpdate() {
        if(statue){
            if(currentTarget != null && assaultTimer>0){
                int i=assaultTimer==6?0:(assaultTimer==1?2:1);

                PacketHandler.INSTANCE.sendTo(new MessageAssault(currentTarget.posX,currentTarget.posY,currentTarget.posZ,i),player);
                player.setAbsorptionAmount(player.getMaxHealth()/5);
                if(assaultTimer==1){
                    player.world.playSound(null, player.posX, player.posY, player.posZ, ModSoundHandler.ASSASSIN , SoundCategory.PLAYERS, 2F, player.getRNG().nextFloat() * 0.1F + 0.9F);
                    currentTarget.attackEntityFrom(DamageSource.causePlayerDamage(player) , (float)(player.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()) * 4.8F);
                    currentTarget=null;
                }

                assaultTimer--;
            }

            if(timer>100 || list.size()>=8){
                player.setAbsorptionAmount(0);
                player.removePotionEffect(MobEffects.INVISIBILITY);
                player.addPotionEffect(new PotionEffect(com.somebody.idlframewok.potion.ModPotions.XIAN_XIN,300,0));
                statue=false;
            }

            timer++;
        }
    }

    public void findTarget() {
        if (statue && currentTarget == null && assaultTimer<=0) {
            List<EntityLivingBase> G = player.getEntityWorld().getEntities(EntityLivingBase.class, e ->
                    e.isEntityAlive() && e != player && (list.size()==0 || !(e instanceof EntityPlayer))
                            && e.getDistance(player)<12 && e.getDistance(player)>0.0001
                            && !list.contains(e) && !(e instanceof EntityArmorStand)
                            && player.canEntityBeSeen(e) && player.posY-e.posY>-0.501 && player.posY-e.posY<1.01);
            double min=Math.PI;
            for (EntityLivingBase entityLivingBase:G) {
                Vec3d v1=player.getLookVec();
                Vec3d v2=new Vec3d(entityLivingBase.posX-player.posX,entityLivingBase.posY+entityLivingBase.getEyeHeight()-player.posY-player.getEyeHeight(),entityLivingBase.posZ-player.posZ).normalize();
                double theta=Math.acos(v1.dotProduct(v2)/v1.lengthVector()/v2.lengthVector());
                if(theta<min){
                    min=theta;
                    currentTarget=entityLivingBase;
                }
            }

            if(currentTarget != null){
                assaultTimer=6;
                list.add(currentTarget);
            }
        }
    }
}
