package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.entity.weapon.EntityShouXuanZheZhiBing;
import com.fuzhudada.error.item.wand.IWand;
import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemShouXuanZhe extends com.somebody.idlframewok.item.ItemSwordBase implements skillLevel, IWand {
    public ItemShouXuanZhe(String name, ToolMaterial material) {
        super(name, material);
    }

    public int getLevel(){
        return 2;
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
                && mana(800,100,playerIn)) {
            EntityShouXuanZheZhiBing g = new EntityShouXuanZheZhiBing(worldIn);
            g.ATTACKER = playerIn;
            g.DAMAGE=140;
            g.setLocationAndAngles(playerIn.posX, playerIn.posY, playerIn.posZ, playerIn.rotationYaw, playerIn.rotationPitch);
            if (!worldIn.isRemote) {
                worldIn.spawnEntity(g);
            }
            playerIn.getCooldownTracker().setCooldown(itemstack.getItem(), 120);
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundHandler.SHOU_XUAN_ZHE , SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
        }
        return new ActionResult<>(EnumActionResult.FAIL, itemstack);
    }
}
