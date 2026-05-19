package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.entity.projectiles.EntityGongZhengZhiCi;
import com.fuzhudada.error.entity.projectiles.EntityShuangZi;
import com.fuzhudada.error.util.ModSoundHandler;
import com.somebody.idlframewok.potion.ModPotions;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemBlueWizardSword extends com.somebody.idlframewok.item.ItemSwordBase {
    public static final int[] nextL={1,2,0,0,1,1},nextR={4,3,3,4,5,0};
    public static Style style=new Style().setColor(TextFormatting.YELLOW);

    public ItemBlueWizardSword(String name , ToolMaterial material) {
        super(name , material);
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(itemstack) ==0
                && !playerIn.isPotionActive(MobEffects.POISON)
                && playerIn.isPotionActive(ModPotions.CEN_DIAN)) {
            int cur=getState(itemstack),nx=nextR[cur];
            if(cur==5 && nx==0){
                skill3(worldIn,playerIn,8,336);
                playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 100);
            }else{
                skill3(worldIn,playerIn,4,112);
                playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 20);
            }
            setState(itemstack,nx);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
        }
        return new ActionResult<>(EnumActionResult.FAIL, itemstack);
    }

    public boolean onLeftClickEntity(@Nonnull ItemStack stack, @Nonnull EntityPlayer player, @Nonnull Entity entity)
    {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(stack) ==0
                && !player.isPotionActive(MobEffects.POISON)
                && player.isPotionActive(ModPotions.CEN_DIAN)
                && player.getCooldownTracker().getCooldown(stack.getItem(),0)<=0){
            int cur=getState(stack),nx=nextL[cur];
            if(cur==2 && nx==0){
                skill1(player.world,player);
                player.getCooldownTracker().setCooldown(stack.getItem(), 120);
            }
            if(cur==3 && nx==0){
                skill2(player.world,player);
                player.getCooldownTracker().setCooldown(stack.getItem(), 100);
            }

            setState(stack,nx);
        }
        return false;
    }

    private void skill1(@Nonnull World worldIn , @Nonnull EntityPlayer playerIn){
        Vec3d F = playerIn.getLookVec();
        EntityGongZhengZhiCi g = new EntityGongZhengZhiCi(worldIn);
        g.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.0F, 1.0F);
        g.setNoGravity(true);
        g.DAMAGE = 580;
        g.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + playerIn.getEyeHeight() + 1.5 * F.y, playerIn.posZ + 1.5 * F.z);
        g.ATTACKER = playerIn;
        g.setExplode(false);
        g.setPass(false);
        if (!worldIn.isRemote) {
            worldIn.spawnEntity(g);
        }
        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundHandler.GONG_ZHENG_ZHI_CI , SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
    }

    private void skill2(@Nonnull World worldIn , @Nonnull EntityPlayer playerIn){
        Vec3d V = playerIn.getLookVec();
        EntityShuangZi g = new EntityShuangZi(worldIn);
        g.motionX = V.x;
        g.motionY = 0;
        g.motionZ = V.z;
        g.setPosition(playerIn.posX + V.x, playerIn.posY+0.1, playerIn.posZ + V.z);
        g.ATTACKER = playerIn;
        g.setType(1);
        g.DAMAGE=408;
        if (!worldIn.isRemote) {
            worldIn.spawnEntity(g);
        }
        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundHandler.HUANG_JIN_BAN_HU , SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
    }

    private void skill3(@Nonnull World worldIn , @Nonnull EntityPlayer playerIn , double dis , float damage){
        List<EntityLivingBase> G = worldIn.getEntities(EntityLivingBase.class, e -> inArea(e,playerIn,dis)
                && !(e instanceof EntityArmorStand)
                && !(e instanceof EntityPlayer));
        for (int i = 0; i <= G.size() - 1; i++) {
            if(dis>7 && ((playerIn.posX - G.get(i).posX != 0) || (playerIn.posZ - G.get(i).posZ != 0))) {
                G.get(i).knockBack(playerIn, 2, playerIn.posX - G.get(i).posX, playerIn.posZ - G.get(i).posZ);
            }
            G.get(i).attackEntityFrom(DamageSource.causePlayerDamage(playerIn), damage);
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
        for(int b=0;b<4*dis;b++) {
            double ag=a-Math.PI+b*Math.PI/(2*dis);
            x=playerIn.posX+5*Math.cos(ag);
            z=playerIn.posZ+5*Math.sin(ag);
            playerIn.world.spawnParticle(EnumParticleTypes.SWEEP_ATTACK,x,playerIn.posY+playerIn.getEyeHeight(),z,0,0,0);
        }
        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP , SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
    }

    private int getState(ItemStack itemStack){
        if (itemStack.getTagCompound() == null) {
            itemStack.setTagCompound(new NBTTagCompound());
            itemStack.getTagCompound().setInteger("state",0);
        }

        return itemStack.getTagCompound().getInteger("state");
    }

    private void setState(ItemStack itemStack,int state){
        if (itemStack.getTagCompound() == null) {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        itemStack.getTagCompound().setInteger("state",state);
    }

    private boolean inArea(EntityLivingBase e,EntityPlayer player,double dis){
        Vec3d F = player.getLookVec();
        F=F.addVector(0,-F.y,0).normalize();
        Vec3d F1=new Vec3d(e.posX-player.posX,0,e.posZ-player.posZ).normalize();
        return e.getDistance(player) < dis
                && Math.toDegrees(Math.acos(F.dotProduct(F1)/(F.lengthVector()*F1.lengthVector())))<90
                && Math.abs(e.posY-player.posY)<1;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn)
    {
        int state=getState(stack);
        if(state>0) tooltip.add(new TextComponentTranslation("blueWizardSword.s"+state).setStyle(style).getUnformattedText());
    }
}
