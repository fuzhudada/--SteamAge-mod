package com.fuzhudada.error.item.Item;

import com.fuzhudada.error.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemEmptyJar extends com.somebody.idlframewok.item.ItemBase {
    public ItemEmptyJar(String name) {
        super(name);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer player, @Nonnull EnumHand hand){
        if(!player.world.isRemote) {
            BlockPos P =this.rayTrace(worldIn,player,true).getBlockPos();
            if(worldIn.getBlockState(P).getBlock()== Blocks.WATER){
                player.getHeldItem(hand).shrink(1);
                worldIn.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.PLAYERS, 2F, player.getRNG().nextFloat() * 0.1F + 0.9F);
                if (!player.inventory.addItemStackToInventory(new ItemStack(ModItems.WATER_JAR)))
                {
                    player.dropItem(new ItemStack(ModItems.WATER_JAR), false);
                }
                return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
            }
        }
        return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItem(hand));
    }
}
