package com.fuzhudada.error.item.food;

import com.fuzhudada.error.item.ModItems;
import com.fuzhudada.error.item.wand.IWand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ManaCookie extends com.somebody.idlframewok.item.food.ItemFoodBase implements IWand {
    public ManaCookie(String name, int amount, float saturation, boolean isWolfFood) {
        super(name, amount, saturation, isWolfFood);
        this.setAlwaysEdible();
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote)
        {
            mana(-2000,0,player);
            player.getCooldownTracker().setCooldown(ModItems.MANA_COOKIE , 600);
            player.getCooldownTracker().setCooldown(ModItems.MANA_PUMPKIN_PIE, 600);
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(@Nonnull ItemStack stack)
    {
        return true;
    }
}
