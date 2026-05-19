package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;

public class ItemSpear extends com.somebody.idlframewok.item.ItemSwordBase{
    public ItemSpear(String name , ToolMaterial material) {
        super(name , material);
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0
                && !playerIn.isPotionActive(MobEffects.POISON)
                && !EnchantmentHelper.getEnchantments(itemstack).containsKey(Enchantments.POWER)) {
            itemstack.addEnchantment(Objects.requireNonNull(Enchantment.getEnchantmentByID(48)) ,1);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
        }
        return new ActionResult<>(EnumActionResult.FAIL, itemstack);
    }

    public boolean hitEntity(ItemStack stack, @Nonnull EntityLivingBase target, @Nonnull EntityLivingBase attacker)
    {
        if (EnchantmentHelper.getEnchantments(stack).containsKey(Enchantments.POWER)) {
            Map<Enchantment,Integer> map=EnchantmentHelper.getEnchantments(stack);
            map.remove(Enchantments.POWER);
            EnchantmentHelper.setEnchantments(map,stack);
        }
        return true;
    }
}
