package com.fuzhudada.error.item.food;

import com.fuzhudada.error.entity.weapon.EntityWind;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ItemWindFruit extends com.somebody.idlframewok.item.food.ItemFoodBase {
    public ItemWindFruit(String name, int amount, float saturation, boolean isWolfFood) {
        super(name, amount, saturation, isWolfFood,12);
        this.setAlwaysEdible();
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote)
        {
            EntityWind wind=new EntityWind(worldIn);
            wind.setPosition(player.posX,player.posY,player.posZ);
            worldIn.spawnEntity(wind);
            player.getCooldownTracker().setCooldown(stack.getItem(), 200);
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(@Nonnull ItemStack stack)
    {
        return true;
    }
}
