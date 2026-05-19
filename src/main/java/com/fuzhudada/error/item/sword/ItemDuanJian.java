package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.entity.projectiles.EntityGongZhengZhiCi;
import com.somebody.idlframewok.potion.ModPotions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemDuanJian extends com.somebody.idlframewok.item.ItemSwordBase{
    public ItemDuanJian(String name, ToolMaterial material) {
        super(name, material);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if (com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0
                && !playerIn.isPotionActive(MobEffects.POISON) && (!playerIn.isPotionActive(ModPotions.CEN_DIAN)
                || (playerIn.isPotionActive(ModPotions.CEN_DIAN) && !playerIn.isSneaking()))) {
            Vec3d F = playerIn.getLookVec();
            EntityGongZhengZhiCi g = new EntityGongZhengZhiCi(worldIn);
            g.motionX = F.x;
            g.motionY = 0;
            g.motionZ = F.z;
            g.setNoGravity(true);
            g.DAMAGE = 5;
            g.setExplode(false);
            g.setPosition(playerIn.posX + F.x, playerIn.posY+playerIn.getEyeHeight(), playerIn.posZ + F.z);
            g.ATTACKER = playerIn;
            if (!worldIn.isRemote) {
                worldIn.spawnEntity(g);
            }
            playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 100);
        }

        if (com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0
                && !playerIn.isPotionActive(MobEffects.POISON) && playerIn.isPotionActive(ModPotions.CEN_DIAN)
                && playerIn.isSneaking()) {
            Vec3d F = playerIn.getLookVec();
            F.addVector(0,-F.y,0);
            F=F.normalize();
            double a,x,y;
            final double atan = Math.atan(F.z / F.x);
            if(F.x>0){
                a= atan;
            }else{
                a= atan +Math.PI;
            }
            for(double b=a-Math.PI/2;b<=a+Math.PI+0.0001;b+=Math.PI/2) {
                x=Math.cos(b);
                y=Math.sin(b);
                EntityGongZhengZhiCi g = new EntityGongZhengZhiCi(worldIn);
                g.motionX = x;
                g.motionY = 0;
                g.motionZ = y;
                g.setNoGravity(true);
                g.DAMAGE = 5;
                g.setExplode(false);
                g.setPosition(playerIn.posX + 0.1 * F.x, playerIn.posY + playerIn.getEyeHeight(), playerIn.posZ + 0.1 * F.z);
                g.ATTACKER = playerIn;
                if (!worldIn.isRemote) {
                    worldIn.spawnEntity(g);
                }
            }
            playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 100);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
