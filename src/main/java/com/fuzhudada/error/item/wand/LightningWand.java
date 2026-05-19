package com.fuzhudada.error.item.wand;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.entity.model.EntityLightningCowHorn;
import com.fuzhudada.error.packet.MessageParticle;
import com.fuzhudada.error.packet.PacketHandler;
import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.List;

public class LightningWand extends com.somebody.idlframewok.item.ItemBase implements IWand {
    public static final float damage=225;
    public boolean isCreative;

    public LightningWand(String name,boolean isCreative) {
        super(name);
        this.isCreative=isCreative;
        setMaxStackSize(1);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0) {
            List<EntityLivingBase> G = playerIn.world.getEntities(EntityLivingBase.class, e -> !(e instanceof EntityArmorStand)
                    && !e.equals(playerIn) && e.getDistanceSq(playerIn)<4096 && e.isEntityAlive());

            EntityLivingBase xnj=null;
            for (EntityLivingBase e : G) {
                if(e.getDistanceSq(playerIn)<256 && e instanceof EntityLightningCowHorn){
                    xnj=e;
                    break;
                }
            }

            if(xnj != null && (isCreative || mana(550,40,playerIn))){
                xnj.attackEntityFrom(ModDamageSource.causeElectricalDamage(playerIn),damage);
                spawnLightning(worldIn,playerIn,xnj,-0.8125);
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundHandler.ELECTRICITY, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 40);
                return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }else{
                EntityLivingBase e1=null;
                for(EntityLivingBase e:G){
                    double a1=getAngle(e,playerIn),a2=0;
                    if(e1 != null) a2=getAngle(e1,playerIn);
                    if(e.getDistanceSq(playerIn)<256 && a1<10  && (e1==null || a1<a2)){
                        e1=e;
                    }
                }

                if(e1 != null && (isCreative || mana(550,40,playerIn))){
                    e1.attackEntityFrom(ModDamageSource.causeElectricalDamage(playerIn),damage);
                    e1.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,20,10,false,false));
                    spawnLightning(worldIn,playerIn,e1,0);

                    HashSet<EntityLivingBase> set=new HashSet<>();
                    for(int k=0;k<(isCreative?1000:2);k++){
                        set.add(e1);
                        xnj=null;
                        for(EntityLivingBase e:G){
                            if(e.getDistanceSq(e1)<256 && e instanceof EntityLightningCowHorn){
                                xnj=e;
                                break;
                            }
                        }

                        if(xnj != null) {
                            xnj.attackEntityFrom(ModDamageSource.causeElectricalDamage(playerIn) , damage);
                            spawnLightning(worldIn,e1,xnj,-0.8125);
                            break;
                        }else{
                            EntityLivingBase e2=null;
                            for(EntityLivingBase e:G){
                                if(!set.contains(e) && !(e instanceof EntityPlayer) && e.getDistanceSq(e1)<256 && (e2==null || e.getDistanceSq(e1)<e2.getDistanceSq(e1))){
                                    e2=e;
                                }
                            }

                            if(e2 != null){
                                e2.attackEntityFrom(ModDamageSource.causeElectricalDamage(playerIn),damage);
                                e2.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,20,10,false,false));
                                spawnLightning(worldIn,e1,e2,0);
                            }else{
                                break;
                            }

                            e1=e2;
                        }
                    }

                    worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundHandler.ELECTRICITY, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                    if(!isCreative) playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 40);
                    return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
                }
            }


        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    private double getAngle(EntityLivingBase e,EntityPlayer player){
        Vec3d F = player.getLookVec();
        Vec3d F1=new Vec3d(e.posX-player.posX,e.posY-player.posY,e.posZ-player.posZ).normalize();
        return Math.toDegrees(Math.acos(F.dotProduct(F1)/(F.lengthVector()*F1.lengthVector())));
    }

    private void spawnLightning(World world,EntityLivingBase e1,EntityLivingBase e2,double dx){
        List<EntityPlayerMP> P = world.getEntities(EntityPlayerMP.class, e->e.getDistanceSq(e1)<4096);
        for (EntityPlayerMP player : P) {
            PacketHandler.INSTANCE.sendTo(new MessageParticle(e1.posX,e1.posY+e1.getEyeHeight(),e1.posZ,new Vec3d(e2.posX+dx,e2.posY+e2.getEyeHeight(),e2.posZ),8),player);
        }
    }
}
