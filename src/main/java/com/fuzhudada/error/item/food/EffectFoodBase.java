package com.fuzhudada.error.item.food;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class EffectFoodBase extends com.somebody.idlframewok.item.food.ItemFoodBase {
    PotionEffect effect;
    public EffectFoodBase(String name, int amount, float saturation, boolean iswolfFood, PotionEffect effect) {
        super(name, amount, saturation, iswolfFood);
        this.effect=effect;
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote)
        {
            player.addPotionEffect(new PotionEffect(this.effect));
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(@Nonnull ItemStack stack)
    {
        return true;
    }
}
