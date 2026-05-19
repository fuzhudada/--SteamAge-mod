package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemTuCi extends com.somebody.idlframewok.item.ItemSwordBase implements skillLevel {
    public ItemTuCi(String name, ToolMaterial material) {
        super(name, material);
    }

    public int getLevel(){
        return 1;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {

        if(!canUse(playerIn,handIn)){
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        ItemStack I = playerIn.getHeldItem(handIn);
        if (I.getItem().getDamage(I) < 1511 && com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0 && !playerIn.isPotionActive(MobEffects.POISON)) {
            playerIn.getCooldownTracker().setCooldown(I.getItem(), 50);
            Vec3d D = playerIn.getLookVec();
            if(D.x*D.x+D.z*D.z != 0) {
                playerIn.knockBack(playerIn, 1, -D.x, -D.z);
                playerIn.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 20, 4));
                boolean flag=false;

                if(!worldIn.isRemote) {
                    List<EntityLivingBase> G = worldIn.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
                    for (int i = 0; i <= G.size() - 1; i++) {
                        if (ShouldDamage(G.get(i),playerIn,6,0.5,1) && G.get(i) != playerIn && !(G.get(i) instanceof EntityArmorStand)) {
                            G.get(i).attackEntityFrom(DamageSource.causePlayerDamage(playerIn), 35);
                            flag=true;
                        }
                    }
                }

                if(flag)  worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundHandler.ASSASSIN, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
        } else {
            com.somebody.idlframewok.util.CommonFunctions.SafeSendMsgToPlayer(playerIn, "耐久不足！无法使用技能！");
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    public static boolean ShouldDamage(EntityLivingBase Entity,EntityPlayer player,double L,double d,double h){
        Vec3d vec3d=player.getLookVec();
        double x1 = player.posX;
        double y1 = player.posY;
        double z1 = player.posZ;
        double x2 = x1 + L* vec3d.x;
        double z2 = z1 + L* vec3d.z;
        double x3 = Entity.posX;
        double y3 = Entity.posY;
        double z3 = Entity.posZ;
        double d1 = Math.abs((z1-z2)*x3+(x2-x1)*z3+(x1-x2)*z1-(z1-z2)*x1)/Math.sqrt((z1-z2)*(z1-z2)+(x1-x2)*(x1-x2));
        double x4 = (x1+x2)/2;
        double z4 = (z1+z2)/2;
        double d2 = Math.abs((x1-x2)*x3+(z1-z2)*z3+(x2-x1)*x4+(z2-z1)*z4)/Math.sqrt((x2-x1)*(x2-x1)+(z2-z1)*(z2-z1));
        double d3 = Math.abs(y1-y3);
        return (d1 <d && d2 <L/2 && d3 <h);
    }
}
