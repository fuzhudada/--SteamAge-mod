package com.fuzhudada.error.item.Item;

import com.fuzhudada.error.entity.weapon.EntityHuoLong;
import com.fuzhudada.error.item.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemWaterJar extends com.somebody.idlframewok.item.ItemBase {
    public ItemWaterJar(String name) {
        super(name);
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(playerIn.isSneaking()) {
            ItemStack I = playerIn.getHeldItem(handIn);
            playerIn.getCooldownTracker().setCooldown(I.getItem(), 200);
            EntityHuoLong.spawnParticle(playerIn.posX,playerIn.posY+1,playerIn.posZ,9,worldIn);
            playerIn.getHeldItem(handIn).shrink(1);
            if (!playerIn.inventory.addItemStackToInventory(new ItemStack(ModItems.KONG_GUAN_ZI)))
            {
                playerIn.dropItem(new ItemStack(ModItems.KONG_GUAN_ZI), false);
            }
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
            if (!worldIn.isRemote) {
                List<EntityLivingBase> G = worldIn.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
                for (int i = 0; i <= G.size() - 1; i++) {
                    if (playerIn.getDistance(G.get(i)) < 3) {
                        G.get(i).extinguish();
                    }
                }
            }
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
