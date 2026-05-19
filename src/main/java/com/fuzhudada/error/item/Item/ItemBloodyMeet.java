package com.fuzhudada.error.item.Item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemBloodyMeet extends com.somebody.idlframewok.item.ItemBase {
    public ItemBloodyMeet(String name) {
        super(name);
    }

    @Override
    public void onUpdate(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull Entity entityIn, int itemSlot, boolean isSelected) {
        if(entityIn instanceof EntityLivingBase){
            ((EntityLivingBase)entityIn).heal(77);
            stack.shrink(1);
        }
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }
}
