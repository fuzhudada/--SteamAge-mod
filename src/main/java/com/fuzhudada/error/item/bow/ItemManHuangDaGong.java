package com.fuzhudada.error.item.bow;

import com.fuzhudada.error.entity.projectiles.EntityHuangJinJian;
import com.somebody.idlframewok.potion.ModPotions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemManHuangDaGong extends ItemBowBase{
    public ItemManHuangDaGong(String name, int damage, Item[] canUse) {
        super(name, damage, canUse);
    }

    @Override
    public void addArrowEffect(ItemArrowBase arrow, EntityTippedArrow entityTippedArrow, ItemStack bow, EntityPlayer player, World world, int i,byte b) {
        if(player.isPotionActive(ModPotions.CEN_DIAN)){
            entityTippedArrow.addEffect(new PotionEffect(MobEffects.SLOWNESS,20,1));
            entityTippedArrow.addEffect(new PotionEffect(ModPotions.JI_XING_ZHONG_DU,49,3));
            entityTippedArrow.addTag("bone");
        }

        super.addArrowEffect(arrow, entityTippedArrow, bow, player, world, i, b);
    }

    @Override
    public void addHuangJinJianEffect(ItemArrowBase arrow, EntityHuangJinJian entityHuangJinJian , ItemStack bow, EntityPlayer player, World world, int i) {
        if(player.isPotionActive(ModPotions.CEN_DIAN)){
            entityHuangJinJian.addTag("bone");
        }

        super.addHuangJinJianEffect(arrow, entityHuangJinJian, bow, player, world, i);
    }
}
