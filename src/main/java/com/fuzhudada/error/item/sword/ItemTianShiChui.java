package com.fuzhudada.error.item.sword;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;


public class ItemTianShiChui extends com.somebody.idlframewok.item.ItemSwordBase implements skillLevel {
    public ItemTianShiChui(String name, ToolMaterial material) {
        super(name, material);
    }

    public int getLevel(){
        return 3;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {

        if(!canUse(playerIn,handIn)){
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        ItemStack I = playerIn.getHeldItem(handIn);
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(I) ==0 && I.getItemDamage()<10 && !playerIn.isPotionActive(MobEffects.POISON)){
            List<EntityLivingBase> G = worldIn.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
            for (int i = 0; i <= G.size() - 1; i++) {
                if (playerIn.getDistance(G.get(i))<6 && !(G.get(i) instanceof EntityArmorStand)) {
                    if(G.get(i).getHealth()<G.get(i).getMaxHealth()) G.get(i).heal(30);
                }
            }
            for(int i=0;i<108;i++){
                double x=playerIn.posX+6*Math.sin(i*Math.PI/54);
                double z=playerIn.posZ+6*Math.cos(i*Math.PI/54);
                playerIn.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, x, playerIn.posY+0.1, z, 0, 1.2D, 0);
                playerIn.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, x, playerIn.posY+0.5, z, 0, 1.2D, 0);
            }
            playerIn.getCooldownTracker().setCooldown(I.getItem(), 600);
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
