package com.fuzhudada.error.item.Item;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.entity.weapon.EntityHuoLong;
import com.fuzhudada.error.item.ModItems;
import com.fuzhudada.error.item.sword.ISteamWeapon;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemZhenQiGuanZi extends com.somebody.idlframewok.item.ItemBase implements ISteamWeapon {
    public ItemZhenQiGuanZi(String name) {
        super(name);
        this.setMaxStackSize(1);
        this.setMaxDamage(100);
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(playerIn.isSneaking()) {
            ItemStack I = playerIn.getHeldItem(handIn);
            if(I.getItemDamage()<I.getMaxDamage()) {
                playerIn.getCooldownTracker().setCooldown(I.getItem(), 200);
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                EntityHuoLong.spawnParticle(playerIn.posX, playerIn.posY + 1, playerIn.posZ, 8, worldIn);
                if (!worldIn.isRemote) {
                    float k1 = playerIn.getHeldItem(handIn).getItemDamage();
                    float k2 = playerIn.getHeldItem(handIn).getMaxDamage();
                    List<EntityLivingBase> G = worldIn.getEntities(EntityLivingBase.class, EntitySelectors.IS_ALIVE);
                    for (int i = 0; i <= G.size() - 1; i++) {
                        if (playerIn.getDistance(G.get(i)) < 3 && G.get(i) != playerIn && !G.get(i).isPotionActive(MobEffects.FIRE_RESISTANCE) && !(G.get(i) instanceof EntityArmorStand)) {
                            G.get(i).attackEntityFrom(ModDamageSource.causeSteamDamage(playerIn,playerIn), 62 * (1 - k1 / k2));
                            G.get(i).setFire(8);
                            G.get(i).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 10));
                        }
                    }
                }
            }
            playerIn.getHeldItem(handIn).shrink(1);
            if (!playerIn.inventory.addItemStackToInventory(new ItemStack(ModItems.KONG_GUAN_ZI)))
            {
                playerIn.dropItem(new ItemStack(ModItems.KONG_GUAN_ZI), false);
            }
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    @Override
    public float maxItemSteam() {
        return 100;
    }
}
