package com.fuzhudada.error.item.food;

import com.fuzhudada.error.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemGoldenFruit extends EffectFoodBase{
    public ItemGoldenFruit(String name , int amount , float saturation , boolean iswolfFood , PotionEffect effect) {
        super(name , amount , saturation , iswolfFood , effect);
    }

    @Nonnull
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, @Nonnull BlockPos pos, @Nonnull EnumHand hand, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        net.minecraft.block.state.IBlockState state = worldIn.getBlockState(pos);

        if (!state.getBlock().isReplaceable(worldIn, pos))
        {
            pos = pos.offset(facing);
        }

        Block soil=worldIn.getBlockState(pos.up()).getBlock();
        if (player.canPlayerEdit(pos, facing, itemstack)
                && (soil == ModBlocks.GOLDEN_VINE || soil == ModBlocks.JUSTICE_LOG || soil == ModBlocks.JUSTICE_LEAVES)
                && worldIn.mayPlace(ModBlocks.GOLDEN_FRUIT, pos, false, facing, null))
        {
            worldIn.setBlockState(pos, ModBlocks.GOLDEN_FRUIT.getDefaultState());
            worldIn.playSound(null,pos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS,1,1);

            itemstack.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }
}
