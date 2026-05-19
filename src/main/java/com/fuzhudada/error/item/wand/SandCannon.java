package com.fuzhudada.error.item.wand;

import com.fuzhudada.error.entity.projectiles.EntitySandCannon;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class SandCannon extends com.somebody.idlframewok.item.ItemBase implements IWand {
    public SandCannon(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    public void onPlayerStoppedUsing(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull EntityLivingBase entityLiving, int timeLeft)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer playerIn = (EntityPlayer)entityLiving;
            int i = this.getMaxItemUseDuration(stack) - timeLeft;
            if (i < 0) return;

            if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(stack) ==0
                    && mana(400+i/10*200,100,playerIn)) {
                Vec3d F = playerIn.getLookVec().normalize();
                EntitySandCannon g = new EntitySandCannon(worldIn,Math.min(i/10+1,4),playerIn,500);
                g.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.5F, 1.0F);
                g.setNoGravity(false);
                g.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + 1.625+1.5*F.y, playerIn.posZ + 1.5 * F.z);
                if (!worldIn.isRemote) {
                    worldIn.spawnEntity(g);
                }
                playerIn.getCooldownTracker().setCooldown(stack.getItem(), 100);
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_SAND_BREAK, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
            }
        }
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0
                && !playerIn.isPotionActive(MobEffects.POISON)) {
            playerIn.setActiveHand(handIn);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
        }
        return new ActionResult<>(EnumActionResult.FAIL, itemstack);
    }

    public int getMaxItemUseDuration(@Nonnull ItemStack stack)
    {
        return 72000;
    }

    @Nonnull
    public EnumAction getItemUseAction(@Nonnull ItemStack stack)
    {
        return EnumAction.BOW;
    }
}
