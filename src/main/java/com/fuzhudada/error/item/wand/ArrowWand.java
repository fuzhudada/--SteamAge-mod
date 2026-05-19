package com.fuzhudada.error.item.wand;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ArrowWand extends com.somebody.idlframewok.item.ItemBase {
    public ArrowWand(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0) {
            int j = -1;
            for (int i = 0; i < playerIn.inventory.getSizeInventory(); ++i) {
                ItemStack itemstack = playerIn.inventory.getStackInSlot(i);

                if (itemstack.getItem() instanceof MoLiJuJiQi) {
                    j = i;
                    break;
                }
            }
            if(j>-1 && playerIn.inventory.getStackInSlot(j).getItemDamage()<=playerIn.inventory.getStackInSlot(j).getMaxDamage()-60) {
                Vec3d D = playerIn.getLookVec();
                Vec3d F = D.normalize();
                EntityTippedArrow g = new EntityTippedArrow(worldIn, playerIn);
                g.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;
                g.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.0F, 1.0F);
                g.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + playerIn.getEyeHeight(), playerIn.posZ + 1.5 * F.z);
                g.setDamage(3.3);
                if (!worldIn.isRemote) {
                    worldIn.spawnEntity(g);
                }
                playerIn.inventory.getStackInSlot(j).setItemDamage(playerIn.inventory.getStackInSlot(j).getItemDamage()+60);
                playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 20);
                playerIn.getCooldownTracker().setCooldown(playerIn.inventory.getStackInSlot(j).getItem(), 22);
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
