package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.item.ModItems;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockSandyThornsFlower extends BlockBush {
    private static final AxisAlignedBB SANDY_THORNS_FLOWER_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.3125D, 0.75D);

    public BlockSandyThornsFlower(String name){
        super(Material.PLANTS, MapColor.GRAY);
        this.setSoundType(SoundType.PLANT);
        setUnlocalizedName(name);
        setRegistryName(name);

        ModBlocks.BLOCKS.add(this);
    }

    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == ModBlocks.SANDY_THORNS;
    }

    @Nonnull
    public AxisAlignedBB getBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess source, @Nonnull BlockPos pos)
    {
        return SANDY_THORNS_FLOWER_AABB;
    }

    @Nonnull
    public Item getItemDropped(@Nonnull IBlockState state, @Nonnull Random rand, int fortune)
    {
        return Items.AIR;
    }

    public int quantityDropped(@Nonnull Random random)
    {
        return 0;
    }

    @Nonnull
    public ItemStack getItem(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state)
    {
        return new ItemStack(ModItems.SANDY_THORNS_SEED);
    }

    @Override
    public void getDrops(@Nonnull net.minecraft.util.NonNullList<ItemStack> drops, @Nonnull net.minecraft.world.IBlockAccess world, @Nonnull BlockPos pos, @Nonnull IBlockState state, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        int count = 1 + rand.nextInt(3) + (fortune > 0 ? rand.nextInt(fortune + 1) : 0);

        for (int i = 0; i < count; i++)
        {
            drops.add(new ItemStack(ModItems.SANDY_THORNS_SEED));
        }
    }
}
