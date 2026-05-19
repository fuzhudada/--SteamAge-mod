package com.fuzhudada.error.item.Item;

import com.fuzhudada.error.gui.InventoryBullet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemBulletBag extends com.somebody.idlframewok.item.ItemBase {
    public ItemBulletBag(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(playerIn.getHeldItemMainhand().getItem() == this && !worldIn.isRemote) {
            IInventory it=new InventoryBullet("bag.bullet",false,9,playerIn.getHeldItem(handIn));
            playerIn.displayGUIChest(it);
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}
