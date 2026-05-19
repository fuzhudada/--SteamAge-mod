package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class BlockGoldenFruit extends Block implements com.somebody.idlframewok.util.IHasModel {
    public BlockGoldenFruit(String name , Material material , SoundType type) {
        super(material);
        setSoundType(type);
        setUnlocalizedName(name);
        setRegistryName(name);

        ModBlocks.BLOCKS.add(this);
    }

    public boolean isOpaqueCube(@Nonnull IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(@Nonnull IBlockState state)
    {
        return false;
    }

    @Nonnull
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    public void getDrops(@Nonnull NonNullList<ItemStack> drops, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull IBlockState state, int fortune)
    {
        drops.add(new ItemStack(ModItems.GOLDEN_FRUIT));
    }

    @Nonnull
    public ItemStack getPickBlock(@Nonnull IBlockState state, @Nonnull RayTraceResult target, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull EntityPlayer player)
    {
        return new ItemStack(ModItems.GOLDEN_FRUIT);
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        Block soil = worldIn.getBlockState(pos.up()).getBlock();
        return super.canPlaceBlockAt(worldIn, pos) && (soil==ModBlocks.JUSTICE_LEAVES || soil==ModBlocks.JUSTICE_LOG || soil==ModBlocks.GOLDEN_VINE);
    }

    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == ModBlocks.JUSTICE_LEAVES || state.getBlock() == ModBlocks.JUSTICE_LOG || state.getBlock() == ModBlocks.GOLDEN_VINE;
    }

    public void neighborChanged(@Nonnull IBlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull Block blockIn, @Nonnull BlockPos fromPos)
    {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        this.checkAndDropBlock(worldIn, pos, state);
    }

    public void updateTick(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Random rand)
    {
        this.checkAndDropBlock(worldIn, pos, state);
    }

    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(worldIn, pos, state))
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
    }

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        {
            Block soil = worldIn.getBlockState(pos.up()).getBlock();
            return soil==ModBlocks.JUSTICE_LEAVES || soil==ModBlocks.JUSTICE_LOG || soil==ModBlocks.GOLDEN_VINE;
        }
        return this.canSustainBush(worldIn.getBlockState(pos.up()));
    }

    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.3125D, 0.375D, 0.3125D, 0.6875D, 1.0D, 0.6875D);

    @Nonnull
    public AxisAlignedBB getBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess source, @Nonnull BlockPos pos)
    {
        return AABB;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(@Nonnull IBlockState blockState, @Nonnull IBlockAccess worldIn, @Nonnull BlockPos pos)
    {
        return AABB;
    }

    @Override
    public void registerModels() {
        com.somebody.idlframewok.IdlFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
