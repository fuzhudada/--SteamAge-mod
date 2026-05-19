package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;

public class ItemSteamHammer extends com.somebody.idlframewok.item.ItemSwordBase implements skillLevel,ISteamWeapon{
    public ItemSteamHammer(String name, ToolMaterial material) {
        super(name, material);
        this.setMaxDamage(200);
    }

    public int getLevel() {
        return 3;
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {

        if(!canUse(playerIn,handIn)){
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        ItemStack I = playerIn.getHeldItem(handIn);
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(I) ==0
                && !playerIn.isPotionActive(MobEffects.POISON)
                && ISteamWeapon.shrinkSteam(I, 20)){
            playerIn.getCooldownTracker().setCooldown(I.getItem(), 200);
            I.addEnchantment(Objects.requireNonNull(Enchantment.getEnchantmentByID(48)) ,1);
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundHandler.STEAM_HAMMER, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    public boolean hitEntity(ItemStack stack, @Nonnull EntityLivingBase target, @Nonnull EntityLivingBase attacker)
    {
        if(EnchantmentHelper.getEnchantments(stack).containsKey(Enchantments.POWER)) {
            target.setFire(8);
            target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 10));
            for(int a=0;a<150;a++){
                target.world.spawnParticle(EnumParticleTypes.CRIT, target.posX + (Math.random() - 0.5D) * 3, target.posY + (Math.random() - 0.5D) * 3 + target.getEyeHeight(), target.posZ + (Math.random() - 0.5D) * 3, 0,0,0);
            }

            Map<Enchantment,Integer> map= EnchantmentHelper.getEnchantments(stack);
            map.remove(Enchantments.POWER);
            EnchantmentHelper.setEnchantments(map,stack);
        }
        return true;
    }

    @Override
    public float maxItemSteam() {
        return 200;
    }

    public boolean onBlockDestroyed(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull IBlockState state, @Nonnull BlockPos pos, @Nonnull EntityLivingBase entityLiving)
    {
        return true;
    }
}
