package com.fuzhudada.error.item.Item;

import com.fuzhudada.error.item.sword.ISteamWeapon;
import com.fuzhudada.error.packet.MessageParticle;
import com.fuzhudada.error.packet.PacketHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemPenHuoQi extends com.somebody.idlframewok.item.ItemBase implements ISteamWeapon {
    public float damage;

    public ItemPenHuoQi(String name,float damage) {
        super(name);
        this.setMaxStackSize(1);
        this.setMaxDamage(600);
        this.damage=damage;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn))==0
        && ISteamWeapon.shrinkSteam(playerIn.getHeldItem(handIn),damage==4.0F?0.16F:0.4F)) {
            Vec3d F = playerIn.getLookVec();
            List<EntityPlayerMP> P = playerIn.world.getEntities(EntityPlayerMP.class, e->e.getDistance(playerIn)<64);
            for (EntityPlayerMP player : P) {
                PacketHandler.INSTANCE.sendTo(new MessageParticle(playerIn.posX,playerIn.posY+playerIn.getEyeHeight(),playerIn.posZ,F,damage==4.0F?0:6),player);
            }

            List<EntityLivingBase> G = playerIn.world.getEntities(EntityLivingBase.class, e -> inArea(e,playerIn));
            for (EntityLivingBase entityLivingBase : G) {
                entityLivingBase.setFire(5);
                entityLivingBase.attackEntityFrom(DamageSource.ON_FIRE,damage);
            }
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    private boolean inArea(EntityLivingBase e,EntityPlayer player){
        Vec3d F = player.getLookVec();
        Vec3d F1=new Vec3d(e.posX-player.posX,e.posY+e.getEyeHeight()-player.posY-player.getEyeHeight(),e.posZ-player.posZ).normalize();
        return e.getDistance(player) < 14
                && player.canEntityBeSeen(e)
                && Math.toDegrees(Math.acos(F.dotProduct(F1)/(F.lengthVector()*F1.lengthVector())))<10;
    }

    @Override
    public float maxItemSteam() {
        return damage==4.0F?100:300;
    }
}
