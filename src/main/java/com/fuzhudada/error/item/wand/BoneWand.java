package com.fuzhudada.error.item.wand;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BoneWand extends com.somebody.idlframewok.item.ItemBase implements IWand{
    public BoneWand(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0) {
            boolean sneaking=playerIn.isSneaking();
            int r=sneaking?1:4;
            int s=findBoneMeal(playerIn);
            if((sneaking && s>=3 && mana(400,100,playerIn)) || (!sneaking && s>=27 && mana(3600,100,playerIn))){
                BlockPos pos=new BlockPos(playerIn.posX,playerIn.posY,playerIn.posZ);
                for(int x=pos.getX()-r;x<=pos.getX()+r;x++){
                    for(int z=pos.getZ()-r;z<=pos.getZ()+r;z++){
                        for(int y=pos.getY()-1;y<=pos.getY()+1;y++){
                            Block block=worldIn.getBlockState(new BlockPos(x,y,z)).getBlock();
                            if(block != Blocks.GRASS && block != Blocks.TALLGRASS) {
                                ItemDye.applyBonemeal(ItemStack.EMPTY, worldIn, new BlockPos(x, y, z), playerIn, handIn);
                            }
                        }
                    }
                }
                shrinkBoneMeal(playerIn,sneaking?3:27);
                playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 100);
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_NOTE_BELL, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    private int findBoneMeal(EntityPlayer player)
    {
        int s=0;
        for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
        {
            ItemStack itemstack = player.inventory.getStackInSlot(i);

            if (itemstack.getItem() == Items.DYE && itemstack.getMetadata() == 15)
            {
                s+=itemstack.getCount();
            }
        }
        return s;
    }

    private void shrinkBoneMeal(EntityPlayer player,int amount){
        int s=0,n;
        for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
        {
            ItemStack itemstack = player.inventory.getStackInSlot(i);

            if (itemstack.getItem() == Items.DYE && itemstack.getMetadata() == 15)
            {
                n=Math.min(amount-s,itemstack.getCount());
                itemstack.shrink(n);
                s+=n;
            }

            if(s>=amount){
                return;
            }
        }
    }
}
