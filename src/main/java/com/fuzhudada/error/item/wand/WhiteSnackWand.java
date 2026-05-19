package com.fuzhudada.error.item.wand;

import com.fuzhudada.error.entity.weapon.EntityWhiteSnackWand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class WhiteSnackWand extends com.somebody.idlframewok.item.ItemBase implements IWand{
    public WhiteSnackWand(String name) {
        super(name);
        setMaxStackSize(1);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0
                && mana(1000,100,playerIn)) {
            Vec3d F = playerIn.getLookVec().normalize();
            EntityWhiteSnackWand g = new EntityWhiteSnackWand(worldIn);
            g.setPosition(playerIn.posX + 2.5 * F.x, playerIn.posY, playerIn.posZ + 2.5 * F.z);
            g.ATTACKER = playerIn;
            g.DAMAGE=90;
            if (!worldIn.isRemote) {
                worldIn.spawnEntity(g);
            }
            playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 100);
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
