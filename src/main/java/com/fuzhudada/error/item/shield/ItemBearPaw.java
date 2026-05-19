package com.fuzhudada.error.item.shield;

import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemBearPaw extends ItemShieldBase {
    public ItemBearPaw(String name , int damage) {
        super(name , damage, ModSoundHandler.ASSASSIN);
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {
        ItemStack itemstack1 = playerIn.getHeldItem(handIn);
        boolean flag=false;

        for (int i = 0; i < playerIn.inventory.getSizeInventory() && i<9; ++i)
        {
            ItemStack itemstack = playerIn.inventory.getStackInSlot(i);

            if(itemstack.getItem() == Items.FISH || itemstack.getItem() == Items.COOKED_FISH){
                flag=true;
            }
        }

        if(!flag){
            return super.onItemRightClick(worldIn,playerIn,handIn);
        }else{
            playerIn.sendMessage(new TextComponentTranslation("shield.bear_paw"));
            return new ActionResult<>(EnumActionResult.FAIL, itemstack1);
        }
    }
}
