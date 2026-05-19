package com.fuzhudada.error.item.food;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HealFoodBase extends com.somebody.idlframewok.item.food.ItemFoodBase {
    int Heal;
    public HealFoodBase(String name, int amount, float saturation, boolean iswolfFood, int Heal) {
        super(name, amount, saturation, iswolfFood);
        this.Heal=Heal;
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote)
        {
            if(player.getHealth()<player.getMaxHealth()) player.heal((float)Heal);
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect( ItemStack stack)
    {
        return true;
    }
}

