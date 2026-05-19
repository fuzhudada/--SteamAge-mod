package com.fuzhudada.error.item.Item;

import com.fuzhudada.error.blocks.ModBlocks;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemBombFruitSeed extends com.somebody.idlframewok.item.ItemBase {
    public int type;

    public ItemBombFruitSeed(String name) {
        super(name);
        this.type=0;
    }

    public ItemBombFruitSeed(String name,int type) {
        super(name);
        this.type=type;
    }

    @Nonnull
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, @Nonnull BlockPos pos, @Nonnull EnumHand hand, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        net.minecraft.block.state.IBlockState state = worldIn.getBlockState(pos);
        if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && state.getBlock() == Blocks.FARMLAND && worldIn.isAirBlock(pos.up()))
        {
            if(type==0){
                worldIn.setBlockState(pos.up(), ModBlocks.BOMB_FRUIT_CROP.getDefaultState());
            }else{
                worldIn.setBlockState(pos.up(), ModBlocks.WIND_FRUIT_CROP.getDefaultState());
            }

            if (player instanceof EntityPlayerMP)
            {
                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos.up(), itemstack);
            }

            itemstack.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }
}
