package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.item.ModItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockBombFruit extends BlockCrops {
    private static final AxisAlignedBB[] CARROT_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D)};
    public Item Seed;
    public Item Crop;
    public int minDrop,maxDrop,seedDrop;

    public BlockBombFruit(String name){
        setUnlocalizedName(name);
        setRegistryName(name);

        ModBlocks.BLOCKS.add(this);
        Seed=ModItems.BOMB_FRUIT_SEED;
        Crop=ModItems.BOMB_FRUIT;
        minDrop=3;
        maxDrop=7;
        seedDrop=3;
    }

    public BlockBombFruit(String name,Item Seed,Item Crop,int minDrop,int maxDrop,int seedDrop){
        setUnlocalizedName(name);
        setRegistryName(name);

        ModBlocks.BLOCKS.add(this);
        this.Seed=Seed;
        this.Crop=Crop;
        this.seedDrop=seedDrop;
        this.minDrop=minDrop;
        this.maxDrop=maxDrop;
    }

    @Nonnull
    protected Item getSeed()
    {
        return Seed;
    }

    @Nonnull
    protected Item getCrop()
    {
        return Crop;
    }

    @Nonnull
    public AxisAlignedBB getBoundingBox(IBlockState state, @Nonnull IBlockAccess source, @Nonnull BlockPos pos)
    {
        return CARROT_AABB[state.getValue(this.getAgeProperty())];
    }

    public void getDrops(@Nonnull NonNullList<ItemStack> p_getDrops_1_, @Nonnull IBlockAccess p_getDrops_2_, @Nonnull BlockPos p_getDrops_3_, @Nonnull IBlockState p_getDrops_4_, int p_getDrops_5_) {
        super.getDrops(p_getDrops_1_, p_getDrops_2_, p_getDrops_3_, p_getDrops_4_, 0);
        int age = this.getAge(p_getDrops_4_);
        Random rand = p_getDrops_2_ instanceof World ? ((World)p_getDrops_2_).rand : new Random();
        if (age >= this.getMaxAge()) {
            p_getDrops_1_.add(new ItemStack(this.getCrop(), rand.nextInt(maxDrop-minDrop)+minDrop, 0));

            for(int i = 0; i < seedDrop + p_getDrops_5_; ++i) {
                if (rand.nextInt(2 * this.getMaxAge()) <= age) {
                    p_getDrops_1_.add(new ItemStack(this.getSeed(), 1, 0));
                }
            }
        }

    }
}
