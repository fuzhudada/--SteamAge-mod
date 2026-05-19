package com.fuzhudada.error.item.Item;

import com.fuzhudada.error.particle.ModParticleHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemTeXiao extends com.somebody.idlframewok.item.ItemBase {
    public ItemTeXiao(String name) {
        super(name);
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
//        for(int i=0;i<10;i++){
//            Vec3d F = playerIn.getLookVec();
//
//            EntityTeXiao bomb=new EntityTeXiao(worldIn,playerIn,getTarget(worldIn,playerIn,0,32));
//            bomb.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + playerIn.getEyeHeight() + 1.5 * F.y, playerIn.posZ + 1.5 * F.z);
//            bomb.motionX = 2*Math.random()-1;
//            bomb.motionY = 2*Math.random()-1;
//            bomb.motionZ = 2*Math.random()-1;
//            bomb.setRotationYawHead((float)Math.toDegrees(Math.atan(F.z/F.x)));
//            bomb.setNoGravity(true);
//            if(!worldIn.isRemote){
//                worldIn.spawnEntity(bomb);
//            }
//        }
        for(int i=0;i<80;i++){
            worldIn.spawnParticle(ModParticleHandler.TEST,playerIn.posX,playerIn.posY+1,playerIn.posZ,worldIn.rand.nextDouble()-0.5,worldIn.rand.nextDouble()-0.5,worldIn.rand.nextDouble()-0.5);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

//    private EntityLivingBase getTarget(World world,EntityPlayer player,double Dmin,double Dmax){
//        List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class,
//                e -> e.getDistance(player)<Dmax && e.getDistance(player)>Dmin && e.isEntityAlive() && !(e instanceof EntityArmorStand));
//        double min=Math.PI;
//        EntityLivingBase target=null;
//        for (EntityLivingBase entityLivingBase:G) {
//            Vec3d v1=player.getLookVec();
//            Vec3d v2=new Vec3d(entityLivingBase.posX-player.posX,entityLivingBase.posY+entityLivingBase.getEyeHeight()-player.posY-player.getEyeHeight(),entityLivingBase.posZ-player.posZ).normalize();
//            double theta=Math.acos(v1.dotProduct(v2)/v1.lengthVector()/v2.lengthVector());
//            if(theta<min){
//                min=theta;
//                target=entityLivingBase;
//            }
//        }
//        return target;
//    }
}
