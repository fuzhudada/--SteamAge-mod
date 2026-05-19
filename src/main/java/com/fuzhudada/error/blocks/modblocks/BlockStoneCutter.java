package com.fuzhudada.error.blocks.modblocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BlockStoneCutter extends BlockCanRotate {
    protected static final AxisAlignedBB BASE_AABB = new AxisAlignedBB(0.0D , 0.0D , 0.0D , 1.0D , 0.5D , 1.0D);

    public BlockStoneCutter(String name , Material material , SoundType type) {
        super(name , material , type);
    }

    @Nonnull
    public AxisAlignedBB getBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess source, @Nonnull BlockPos pos)
    {
        return BASE_AABB;
    }

    public void addCollisionBoxToList(@Nonnull IBlockState state , @Nonnull World worldIn , @Nonnull BlockPos pos , @Nonnull AxisAlignedBB entityBox , @Nonnull List<AxisAlignedBB> collidingBoxes , @Nullable Entity entityIn , boolean isActualState) {
        addCollisionBoxToList(pos , entityBox , collidingBoxes , BASE_AABB);
    }

    @Nonnull
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
}