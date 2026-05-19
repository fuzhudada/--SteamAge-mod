package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.item.ModItems;
import com.somebody.idlframewok.potion.ModPotions;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class BlockXunXiangShuLeaves extends BlockLeaves{

    public BlockXunXiangShuLeaves(String name, Material material, SoundType type) {
        super(name, material, type);
    }

    public void onEntityCollidedWithBlock(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Entity entityIn)
    {
        if(entityIn instanceof EntityPlayer) {
            ((EntityPlayer)entityIn).addPotionEffect(new PotionEffect(ModPotions.QU_SAN_XIANG,200,0));
        }else if(entityIn instanceof EntityLivingBase){
            ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(ModPotions.QU_SAN_XIANG,6000,0));
        }
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ModBlocks.XUN_XIANG_SHU_SHU_MIAO);
    }

    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance)
    {
        if (worldIn.rand.nextInt(chance) == 0)
        {
            spawnAsEntity(worldIn, pos, new ItemStack(ModItems.XUN_XIANG_GUO));
        }
    }
}
