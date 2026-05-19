package com.fuzhudada.error.blocks.modblocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BlockComposter extends BlockCanRotate{
    protected static final AxisAlignedBB BASE_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
    protected static final AxisAlignedBB AABB1 = new AxisAlignedBB(0.0D, 0.125D, 0.0D, 1.0D, 1.0D, 0.0625D);
    protected static final AxisAlignedBB AABB2 = new AxisAlignedBB(0.0D, 0.125D, 0.9375D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB AABB3 = new AxisAlignedBB(0.0D, 0.125D, 0.0625D, 0.0625D, 1.0D, 0.9375D);
    protected static final AxisAlignedBB AABB4 = new AxisAlignedBB(0.9375D, 0.125D, 0.0625D, 1.0D, 1.0D, 0.9375D);

    public BlockComposter(String name , Material material , SoundType type) {
        super(name , material , type);
    }

    public void addCollisionBoxToList(@Nonnull IBlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull AxisAlignedBB entityBox, @Nonnull List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, BASE_AABB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB1);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB2);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB3);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB4);
    }
}
