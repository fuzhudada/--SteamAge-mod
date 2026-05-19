package com.fuzhudada.error.item.bow;

import com.fuzhudada.error.item.sword.ISteamWeapon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemZhenQiGong extends ItemBowBase implements ISteamWeapon {

    public ItemZhenQiGong(String name, int damage, int shootSpeed, Item[] canUse) {
        super(name, damage, shootSpeed, canUse);
    }

    @Nonnull
    public EnumAction getItemUseAction(@Nonnull ItemStack stack)
    {
        return EnumAction.NONE;
    }

    @Override
    public boolean onShoot(ItemStack bow, ItemStack arrow_itemStack, EntityPlayer player, World world, int i) {
        if(!ISteamWeapon.shrinkSteam(bow,0.13F)){
            player.getCooldownTracker().setCooldown(bow.getItem(), 20);
        }
        return super.onShoot(bow, arrow_itemStack, player, world, i);
    }

    @Override
    public float maxItemSteam() {
        return 50;
    }
}
