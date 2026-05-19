package com.fuzhudada.error.item.Item;

import com.fuzhudada.error.enchantments.ModEnchantmentInit;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemPotionPack extends com.somebody.idlframewok.item.ItemBase {
    PotionEffect Potion;
    public ItemPotionPack(String name, PotionEffect Potion) {
        super(name);
        this.Potion=Potion;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(playerIn.getHeldItemMainhand().getItem() == this && !worldIn.isRemote) {
            playerIn.getHeldItemMainhand().setCount(playerIn.getHeldItemMainhand().getCount() - 1);
            int r = ModEnchantmentInit.RONG_LIANG_SHENG_JI.getEnchantmentLevel(playerIn.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND)) + 1;
            List<EntityLivingBase> G = worldIn.getEntities(EntityLivingBase.class, e -> e.isEntityAlive()
                    && !(e instanceof EntityPlayer)
                    && !(e instanceof EntityArmorStand)
                    && e.getDistanceSq(playerIn)<r*r);
            for (int i = 0; i <= G.size() - 1; i++) {
                G.get(i).addPotionEffect(new PotionEffect(this.Potion));
            }
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
