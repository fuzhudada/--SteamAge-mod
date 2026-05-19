package com.fuzhudada.error.item.wand;

import com.fuzhudada.error.entity.projectiles.EntityWindWand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class WindWand extends com.somebody.idlframewok.item.ItemBase implements IWand{
    public WindWand(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0
           && mana(400,160,playerIn)) {
            Vec3d F = playerIn.getLookVec().normalize();
            EntityWindWand g = new EntityWindWand(worldIn);
            g.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2F, 1.0F);
            g.setNoGravity(true);
            g.DAMAGE=18;
            g.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + 1.625+1.5*F.y, playerIn.posZ + 1.5 * F.z);
            g.ATTACKER = playerIn;
            if (!worldIn.isRemote) {
                worldIn.spawnEntity(g);
            }
            playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 160);
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
