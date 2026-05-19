package com.fuzhudada.error.blocks.machine;

import com.fuzhudada.error.blocks.BlockBase;
import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.blocks.tileEntity.TileEntityCenser;
import com.fuzhudada.error.item.XunXiang.ItemXiangLu;
import com.fuzhudada.error.item.XunXiang.ItemXunXiang;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class BlockCenser extends BlockBase implements ITileEntityProvider {
    public static final PropertyInteger STICK = PropertyInteger.create("stick",0,6);
    public static final PropertyBool BURNING = PropertyBool.create("burning");

    public BlockCenser(String name , Material material , SoundType type) {
        super(name , material , type);
        this.setDefaultState(this.blockState.getBaseState().withProperty(STICK,0).withProperty(BURNING,false));
        this.setTickRandomly(true);
    }

    public void getDrops(@Nonnull NonNullList<ItemStack> drops, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull IBlockState state, int fortune)
    {
        drops.add(new ItemStack(ModBlocks.CENSER));
    }

    public boolean onBlockActivated(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, EntityPlayer playerIn, @Nonnull EnumHand hand, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemStack=playerIn.getHeldItem(hand);
        if(state.getValue(STICK) == 0){

            if(itemStack.getItem() instanceof ItemXunXiang){
                worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_SAND_PLACE, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                state=state.withProperty(STICK,((ItemXunXiang)itemStack.getItem()).getId()+1).withProperty(BURNING,false);
                worldIn.setBlockState(pos, state, 3);
                itemStack.shrink(1);

                return true;
            }
        }else{
            if(itemStack.getItem() instanceof ItemXiangLu && Arrays.binarySearch(((ItemXiangLu)itemStack.getItem()).canUse,state.getValue(STICK)-1)>-1){
                if(!state.getValue(BURNING)) {
                    worldIn.playSound(null , pos.getX() , pos.getY() , pos.getZ() , SoundEvents.ITEM_FLINTANDSTEEL_USE , SoundCategory.PLAYERS , 2F , playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                    state = state.withProperty(BURNING , true);
                    worldIn.setBlockState(pos , state , 3);
                    return true;
                }
            }else{
                worldIn.playSound(null , pos.getX() , pos.getY() , pos.getZ() , SoundEvents.BLOCK_SAND_PLACE , SoundCategory.PLAYERS , 2F , playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                if(state.getValue(BURNING)){
                    if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items.STICK))) {
                        playerIn.dropItem(new ItemStack(Items.STICK) , false);
                    }
                }else {
                    if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Objects.requireNonNull(ItemXunXiang.getXunXiangFromId(state.getValue(STICK)-1))))) {
                        playerIn.dropItem(new ItemStack(Objects.requireNonNull(ItemXunXiang.getXunXiangFromId(state.getValue(STICK)-1))) , false);
                    }
                }
                state = state.withProperty(BURNING , false).withProperty(STICK,0);
                worldIn.setBlockState(pos , state , 3);
                return true;
            }
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("incomplete-switch")
    public void randomDisplayTick(IBlockState stateIn, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull Random rand)
    {
        if (stateIn.getValue(BURNING))
        {
            worldIn.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 1.3125, pos.getZ() + 0.5, 0.0D, 0.0D, 0.0D);
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + 0.5, pos.getY() + 1.3125, pos.getZ() + 0.5, 0.0D, 0.1D, 0.0D);
        }
    }

    public void updateTick(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Random rand)
    {
    }

    public boolean isOpaqueCube(@Nonnull IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(@Nonnull IBlockState state)
    {
        return false;
    }

    protected static final AxisAlignedBB CENSER_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.125D, 0.9375D, 1.0D, 0.875D);
    protected static final AxisAlignedBB BASE_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.125D, 0.9375D, 0.6875D, 0.875D);
    protected static final AxisAlignedBB STICK_AABB = new AxisAlignedBB(0.4375D, 0.6875D, 0.4375D, 0.5625D, 1.0D, 0.5625D);

    @Nonnull
    public AxisAlignedBB getBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess source, @Nonnull BlockPos pos)
    {
        return CENSER_AABB;
    }

    public void addCollisionBoxToList(@Nonnull IBlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull AxisAlignedBB entityBox, @Nonnull List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, BASE_AABB);
        if(state.getValue(STICK)>0) {
            addCollisionBoxToList(pos , entityBox , collidingBoxes , STICK_AABB);
        }
    }

    @Nonnull
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(STICK, meta);
    }

    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(STICK);
    }

    public int getComparatorInputOverride(IBlockState blockState, @Nonnull World worldIn, @Nonnull BlockPos pos)
    {
        return blockState.getValue(STICK)>0?(blockState.getValue(BURNING)?14:7):0;
    }

    public boolean hasComparatorInputOverride(@Nonnull IBlockState state)
    {
        return true;
    }

    @Nonnull
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, STICK,BURNING);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn , int meta) {
        return new TileEntityCenser();
    }

    public void breakBlock(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state)
    {
        super.breakBlock(worldIn, pos, state);
        worldIn.removeTileEntity(pos);
    }
}
