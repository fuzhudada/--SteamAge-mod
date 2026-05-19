package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.entity.weapon.EntityDiCi;
import com.fuzhudada.error.item.wand.IWand;
import com.somebody.idlframewok.potion.ModPotions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemFuRongJian extends com.somebody.idlframewok.item.ItemSwordBase implements skillLevel, IWand {
    public ItemFuRongJian(String name, ToolMaterial material) {
        super(name, material);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(!canUse(playerIn,handIn)){
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0
                && !playerIn.isPotionActive(MobEffects.POISON)) {

            List<EntityLivingBase> G = worldIn.getEntities(EntityLivingBase.class,e -> inArea(e,playerIn)
                    && !(e instanceof EntityArmorStand)
                    && !(e instanceof EntityPlayer));
            boolean flag=false;
            if(mana(400,100,playerIn)){
                double r=getClosestEntityRange(G,8,playerIn);
                for(int i=0;i<4.5*r+5;i++) {

                    double x=playerIn.posX+r*Math.cos(i*2*Math.PI/(4*r+4));
                    double z=playerIn.posZ+r*Math.sin(i*2*Math.PI/(4*r+4));
                    EntityDiCi g = new EntityDiCi(worldIn, x,playerIn.posY,z,playerIn.rotationYaw,0,playerIn,90);
                    if(!worldIn.isRemote){
                        worldIn.spawnEntity(g);
                    }
                }
                flag=true;
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.EVOCATION_FANGS_ATTACK, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
            }

            if(playerIn.isPotionActive(ModPotions.CEN_DIAN)){
                for (int i = 0; i <= G.size() - 1; i++) {
                    if((playerIn.posX - G.get(i).posX != 0) || (playerIn.posZ - G.get(i).posZ != 0)) {
                        G.get(i).knockBack(playerIn, 2, playerIn.posX - G.get(i).posX, playerIn.posZ - G.get(i).posZ);
                    }
                    G.get(i).attackEntityFrom(DamageSource.causePlayerDamage(playerIn), 220);
                    G.get(i).addPotionEffect(new PotionEffect(ModPotions.JI_XING_ZHONG_DU, 99, 2));
                }

                Vec3d F = playerIn.getLookVec();
                F.addVector(0,-F.y,0);
                F=F.normalize();
                double a,x,z;
                final double atan = Math.atan(F.z / F.x);
                if(F.x>0){
                    a= atan;
                }else{
                    a= atan +Math.PI;
                }
                for(double b=a-Math.PI/2;b<=a+Math.PI/2+0.0001;b+=Math.PI/15) {
                    x=playerIn.posX+5*Math.cos(b);
                    z=playerIn.posZ+5*Math.sin(b);
                    playerIn.world.spawnParticle(EnumParticleTypes.SWEEP_ATTACK,x,playerIn.posY+playerIn.getEyeHeight(),z,0,0,0);
                }
                flag=true;
            }

            if(flag){
                playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 200);
            }

            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    private boolean inArea(EntityLivingBase e,EntityPlayer player){
        Vec3d F = player.getLookVec();
        F=F.addVector(0,-F.y,0).normalize();
        Vec3d F1=new Vec3d(e.posX-player.posX,0,e.posZ-player.posZ).normalize();
        return e.getDistance(player) < 8
                && Math.toDegrees(Math.acos(F.dotProduct(F1)/(F.lengthVector()*F1.lengthVector())))<90
                && Math.abs(e.posY-player.posY)<1;
    }

    private double getClosestEntityRange(List<EntityLivingBase> G, double max, Entity e){
        double r= 8;
        for(EntityLivingBase entityLivingBase:G){
            if(entityLivingBase.getDistance(e)<r && !entityLivingBase.equals(e)){
                r=entityLivingBase.getDistance(e);
            }
        }
        return r;
    }

    @Override
    public int getLevel() {
        return 3;
    }
}
