package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.blocks.itemBlock.ItemBlockGoldenLily;
import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.item.ModItems;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockLilyPad;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Random;

public class BlockGoldenLily extends BlockLilyPad implements com.somebody.idlframewok.util.IHasModel {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool FLOWER = PropertyBool.create("flower");

    public BlockGoldenLily(String name){
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.PLANT);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(FLOWER,false));

        ModItems.ITEMS.add(new ItemBlockGoldenLily(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
        ModBlocks.BLOCKS.add(this);
    }

    @Nonnull
    public Item getItemDropped(@Nonnull IBlockState p_getItemDropped_1_, @Nonnull Random p_getItemDropped_2_, int p_getItemDropped_3_) {
        return Item.getItemFromBlock(this);
    }

    public void getDrops(@Nonnull NonNullList<ItemStack> drops, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, IBlockState state, int fortune)
    {
        drops.add(new ItemStack(Item.getItemFromBlock(this)));
        if(state.getValue(FLOWER)){
            drops.add(new ItemStack(ModBlocks.LOTUS));
        }
    }

    @Nonnull
    public ItemStack getItem(@Nonnull World p_getItem_1_, @Nonnull BlockPos p_getItem_2_, @Nonnull IBlockState p_getItem_3_) {
        return new ItemStack(Item.getItemFromBlock(this));
    }

    @Override
    public void registerModels() {
        com.somebody.idlframewok.IdlFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        return (meta & 4) > 0 ? this.getDefaultState().withProperty(FLOWER,true).withProperty(FACING,getFacing(meta & 3)):this.getDefaultState().withProperty(FLOWER,false).withProperty(FACING,getFacing(meta & 3));
    }

    public int getMetaFromState(IBlockState state) {
        return state.getValue(FLOWER) ? getMetaForFacing(state.getValue(FACING)) | 4 : getMetaForFacing(state.getValue(FACING));
    }

    protected static EnumFacing getFacing(int meta)
    {
        switch (meta & 3)
        {
            case 0:
                return EnumFacing.NORTH;
            case 1:
                return EnumFacing.SOUTH;
            case 2:
                return EnumFacing.WEST;
            case 3:
            default:
                return EnumFacing.EAST;
        }
    }

    protected static int getMetaForFacing(EnumFacing facing)
    {
        switch (facing)
        {
            case NORTH:
                return 0;
            case SOUTH:
                return 1;
            case WEST:
                return 2;
            case EAST:
            default:
                return 3;
        }
    }

    @Nonnull
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Nonnull
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    @Nonnull
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING,FLOWER);
    }
}
