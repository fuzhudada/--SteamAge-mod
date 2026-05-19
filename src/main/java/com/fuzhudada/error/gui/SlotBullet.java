package com.fuzhudada.error.gui;

import com.fuzhudada.error.item.bow.ItemArrowBase;
import com.fuzhudada.error.item.gun.ItemBullet;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class SlotBullet extends Slot {
    public SlotBullet(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem() instanceof ItemBullet || stack.getItem() instanceof ItemArrowBase;
    }

    public int getItemStackLimit(@Nonnull ItemStack stack)
    {
        return 256;
    }
}
