package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.entity.projectiles.EntityFeiDao;
import com.fuzhudada.error.item.wand.IWand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemSongMu extends com.somebody.idlframewok.item.ItemSwordBase implements skillLevel, IWand {
    public ItemSongMu(String name, ToolMaterial material) {
        super(name, material);
    }

    public int getLevel(){
        return 3;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {

        if(!canUse(playerIn,handIn)){
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0
                && playerIn.getHeldItemOffhand().getItem() instanceof ItemSuoMu
                && !playerIn.isPotionActive(MobEffects.POISON)
                && playerIn.isPotionActive(com.somebody.idlframewok.potion.ModPotions.CEN_DIAN)) {
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
            for(double b=a-Math.PI*0.1;b<=a+Math.PI*0.1+0.0001;b+=Math.PI*0.01818181818) {
                x=Math.cos(b);
                y=Math.sin(b);
                EntityFeiDao g = new EntityFeiDao(worldIn);
                g.motionX = x;
                g.motionY = 0;
                g.motionZ = y;
                g.DAMAGE = 28;
                g.setNoGravity(true);
                g.setPosition(playerIn.posX + 0.1 * F.x, playerIn.posY + 1.625, playerIn.posZ + 0.1 * F.z);
                g.ATTACKER = playerIn;
                playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE,10,1));
                if (!worldIn.isRemote) {
                    worldIn.spawnEntity(g);
                }
            }
            playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 140);
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
