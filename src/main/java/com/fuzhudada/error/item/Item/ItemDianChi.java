package com.fuzhudada.error.item.Item;

import com.somebody.idlframewok.potion.ModPotions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Objects;

public class ItemDianChi extends com.somebody.idlframewok.item.ItemBase {
    public ItemDianChi(String name) {
        super(name);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if (playerIn.isPotionActive(ModPotions.CHONG_NENG)) {
            int Amp = Objects.requireNonNull(playerIn.getActivePotionEffect(ModPotions.CHONG_NENG)).getAmplifier();
            int Dur = Objects.requireNonNull(playerIn.getActivePotionEffect(ModPotions.CHONG_NENG)).getDuration();
            playerIn.addPotionEffect(new PotionEffect(ModPotions.CHONG_NENG, Dur + 3600, Amp + 1));
        }else {
            playerIn.addPotionEffect(new PotionEffect(ModPotions.CHONG_NENG, 3600, 0));
        }
        ItemStack Item = playerIn.getHeldItem(handIn);
        playerIn.getCooldownTracker().setCooldown(Item.getItem(), 3600);
        Item.shrink(1);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
