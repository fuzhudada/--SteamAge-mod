package com.fuzhudada.error.item.sword;

import com.fuzhudada.error.entity.projectiles.EntityGongZhengZhiCi;
import com.fuzhudada.error.util.ModSoundHandler;
import com.somebody.idlframewok.potion.ModPotions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;


public class ItemGongZhengZhiCi extends com.somebody.idlframewok.item.ItemSwordBase implements skillLevel {
    public ItemGongZhengZhiCi(String name, ToolMaterial material) {
        super(name, material);
    }

    public int getLevel(){
        return 2;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {

        if(!canUse(playerIn,handIn)){
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        if (com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0
                && !playerIn.isPotionActive(MobEffects.POISON)
                && playerIn.isPotionActive(ModPotions.CEN_DIAN)) {
            Vec3d F = playerIn.getLookVec();
            EntityGongZhengZhiCi g = new EntityGongZhengZhiCi(worldIn);
            g.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.0F, 1.0F);
            g.setNoGravity(true);
            g.DAMAGE = 44;
            g.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + playerIn.getEyeHeight() + 1.5 * F.y, playerIn.posZ + 1.5 * F.z);
            g.ATTACKER = playerIn;
            if (!worldIn.isRemote) {
                worldIn.spawnEntity(g);
            }
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundHandler.GONG_ZHENG_ZHI_CI , SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
            playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 36);
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
