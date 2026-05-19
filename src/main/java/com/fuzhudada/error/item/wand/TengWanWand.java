package com.fuzhudada.error.item.wand;

import com.fuzhudada.error.packet.MessageParticle;
import com.fuzhudada.error.packet.PacketHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class TengWanWand extends com.somebody.idlframewok.item.ItemBase implements IWand{
    public TengWanWand(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0
                && mana(90,2,playerIn)) {
            Vec3d F = playerIn.getLookVec();
            F=F.addVector(0,-F.y,0).normalize();
            List<EntityPlayerMP> P = playerIn.world.getEntities(EntityPlayerMP.class, e->e.getDistance(playerIn)<64);
            for (EntityPlayerMP player : P) {
                PacketHandler.INSTANCE.sendTo(new MessageParticle(playerIn.posX,playerIn.posY+1,playerIn.posZ,F,1),player);
            }

            List<EntityLivingBase> G = playerIn.world.getEntities(EntityLivingBase.class, e -> inArea(e,playerIn));
            for (EntityLivingBase entityLivingBase : G) {
                entityLivingBase.motionX=0;
                entityLivingBase.motionY=0;
                entityLivingBase.motionZ=0;
                entityLivingBase.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,4,10));
            }
            playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 2);
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    private boolean inArea(EntityLivingBase e,EntityPlayer player){
        Vec3d F = player.getLookVec();
        F=F.addVector(0,-F.y,0).normalize();
        Vec3d F1=new Vec3d(e.posX-player.posX,0,e.posZ-player.posZ).normalize();
        return e.getDistance(player) < 8
                && Math.toDegrees(Math.acos(F.dotProduct(F1)/(F.lengthVector()*F1.lengthVector())))<10
                && Math.abs(e.posY-player.posY)<1;
    }
}
