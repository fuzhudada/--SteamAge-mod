package com.fuzhudada.error.item.sword;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemRongHui extends com.somebody.idlframewok.item.ItemSwordBase {
    public ItemRongHui(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 200, -4));
        playerIn.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 600, 2));
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
