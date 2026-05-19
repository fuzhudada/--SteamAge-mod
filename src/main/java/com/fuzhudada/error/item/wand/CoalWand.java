package com.fuzhudada.error.item.wand;

import com.fuzhudada.error.entity.projectiles.EntityGongZhengZhiCi;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class CoalWand extends com.somebody.idlframewok.item.ItemBase implements IWand{
    public CoalWand(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0) {
            playerIn.setActiveHand(handIn);
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    @Override
    public void serverUseTick(ItemStack stack, EntityLivingBase living, int count) {
        if(count % 5 == 0 && living instanceof EntityPlayer && mana(48,5,(EntityPlayer) living)
                && com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(stack) ==0) {
            EntityPlayer playerIn=(EntityPlayer)living;
            World worldIn=playerIn.getEntityWorld();
            Vec3d F = playerIn.getLookVec().normalize();
            EntityGongZhengZhiCi g = new EntityGongZhengZhiCi(worldIn);
            g.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2F, 0);
            g.setNoGravity(true);
            int d=getDamage(count);
            g.DAMAGE=d;
            g.setFire(d==5);
            g.setExplode(false);
            g.setHurtTime(false);
            g.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + 1.625+1.5*F.y, playerIn.posZ + 1.5 * F.z);
            g.ATTACKER = playerIn;
            if (!worldIn.isRemote) {
                worldIn.spawnEntity(g);
            }
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
        }

        if(living instanceof EntityPlayer && IWand.getMana((EntityPlayer) living)>25
                && com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(stack) ==0){
            living.addPotionEffect(new PotionEffect(MobEffects.SPEED,0,15));
        }
        super.serverUseTick(stack, living, count);
    }

    @Override
    public void clientUseTick(ItemStack stack, EntityLivingBase living, int count) {
        if(count % 5 == 0 && living instanceof EntityPlayer && mana(48,5,(EntityPlayer) living)
                && com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(stack) ==0) {
            EntityPlayer playerIn=(EntityPlayer)living;
            World worldIn=playerIn.getEntityWorld();
            worldIn.spawnParticle(EnumParticleTypes.LAVA,playerIn.posX,playerIn.posY+playerIn.getEyeHeight(),playerIn.posZ,Math.random()*1-0.5,Math.random(),Math.random()*1-0.5);
            worldIn.spawnParticle(EnumParticleTypes.LAVA,playerIn.posX,playerIn.posY+playerIn.getEyeHeight(),playerIn.posZ,Math.random()*1-0.5,Math.random(),Math.random()*1-0.5);
        }
        super.clientUseTick(stack, living, count);
    }

    public int getMaxItemUseDuration(@Nonnull ItemStack stack)
    {
        return 72000;
    }

    private int getDamage(int l){
        if(l<=20){
            return 1;
        }else if(l<=35){
            return 2;
        }else if(l<=50){
            return 4;
        }else if(l<=65){
            return 6;
        }else{
            return 9;
        }
    }
}
