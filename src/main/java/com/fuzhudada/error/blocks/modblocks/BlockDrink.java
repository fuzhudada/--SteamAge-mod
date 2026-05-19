package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.item.ModItems;
import com.fuzhudada.error.item.food.ItemDrink;
import com.fuzhudada.error.item.food.ItemWindWine;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BlockDrink extends Block implements com.somebody.idlframewok.util.IHasModel {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    protected AxisAlignedBB EAST_AABB;
    protected AxisAlignedBB SOUTH_AABB;
    protected AxisAlignedBB WEST_AABB;
    protected AxisAlignedBB NORTH_AABB;

    public BlockDrink(String name, Material material, SoundType type, PotionEffect potionEffect, int coolDown,float dx,float dy,float dz) {
        super(material);
        setSoundType(type);
        setUnlocalizedName(name);
        setRegistryName(name);
        EAST_AABB = new AxisAlignedBB(0.5-dz/2, 0.0D, 0.5-dx/2, 0.5+dz/2, dy, 0.5+dx/2);
        SOUTH_AABB = new AxisAlignedBB(0.5-dx/2, 0.0D, 0.5-dz/2, 0.5+dx/2, dy, 0.5+dz/2);
        WEST_AABB = new AxisAlignedBB(0.5-dz/2, 0.0D, 0.5-dx/2, 0.5+dz/2, dy, 0.5+dx/2);
        NORTH_AABB = new AxisAlignedBB(0.5-dx/2, 0.0D, 0.5-dz/2, 0.5+dx/2, dy, 0.5+dz/2);

        ModBlocks.BLOCKS.add(this);
        if(potionEffect != null){
            if(potionEffect.getPotion()== MobEffects.SPEED){
                ModItems.ITEMS.add(new ItemWindWine(this,potionEffect,coolDown).setRegistryName(this.getRegistryName()));
            }else{
                ModItems.ITEMS.add(new ItemDrink(this,potionEffect,coolDown).setRegistryName(this.getRegistryName()));
            }
        }else{
            ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
        }
    }

    public BlockDrink(String name, PotionEffect potionEffect, int coolDown,float dx,float dy,float dz) {
        this(name,Material.GLASS,SoundType.GLASS,potionEffect,coolDown,dx,dy,dz);
    }

    public BlockDrink(String name, PotionEffect potionEffect,float dx,float dy,float dz) {
        this(name,potionEffect,600,dx,dy,dz);
    }

    public BlockDrink(String name, PotionEffect potionEffect) {
        this(name,potionEffect,600,0.25F,0.5F,0.25F);
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
    public AxisAlignedBB getBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess source, @Nonnull BlockPos pos)
    {
        if(state.getValue(FACING)== EnumFacing.EAST) {
            return EAST_AABB;
        }else if(state.getValue(FACING)==EnumFacing.SOUTH) {
            return SOUTH_AABB;
        }else if(state.getValue(FACING)==EnumFacing.WEST) {
            return WEST_AABB;
        }else{
            return NORTH_AABB;
        }
    }

    public void addCollisionBoxToList(IBlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull AxisAlignedBB entityBox, @Nonnull List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        if(state.getValue(FACING)== EnumFacing.EAST) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
        }else if(state.getValue(FACING)==EnumFacing.SOUTH) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
        }else if(state.getValue(FACING)==EnumFacing.WEST) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
        }else if(state.getValue(FACING)==EnumFacing.NORTH) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
        }
    }

    @Nonnull
    public IBlockState getStateForPlacement(@Nonnull World worldIn, @Nonnull BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, @Nonnull EntityLivingBase placer)
    {
        IBlockState iblockstate = this.getDefaultState();

        if (facing.getAxis().isHorizontal())
        {
            iblockstate = iblockstate.withProperty(FACING, facing);
        }
        else
        {
            iblockstate = iblockstate.withProperty(FACING, placer.getHorizontalFacing().getOpposite());
        }

        return iblockstate;
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
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, getFacing(meta));
    }

    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | getMetaForFacing(state.getValue(FACING));

        return i;
    }

    @Nonnull
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    @Nonnull
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    @Nonnull
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public void registerModels() {
        com.somebody.idlframewok.IdlFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
