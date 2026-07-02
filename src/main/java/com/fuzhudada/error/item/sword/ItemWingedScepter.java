package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.entity.weapon.EntityWingedScepter;
import com.fuzhudada.error.item.wand.IWand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemWingedScepter extends com.somebody.idlframewok.item.ItemSwordBase implements skillLevel, IWand {
    public ItemWingedScepter(String name, ToolMaterial material) {
        super(name, material);
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {
        if(!canUse(playerIn,handIn)){
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0
                && !playerIn.isPotionActive(MobEffects.POISON)
                && mana(2000,200,playerIn)) {
            EntityWingedScepter g = new EntityWingedScepter(worldIn,playerIn,600,8,16,3);
            g.setPosition(playerIn.posX, playerIn.posY, playerIn.posZ);
            if (!worldIn.isRemote) {
                worldIn.spawnEntity(g);
            }
            playerIn.getCooldownTracker().setCooldown(itemstack.getItem(), 600);

            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    @Override
    public int getLevel() {
        return 5;
    }
}
