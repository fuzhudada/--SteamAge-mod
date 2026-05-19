package com.fuzhudada.error.blocks.modblocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nonnull;

public class BlockGrass extends BlockKeKeJu{
    protected static final AxisAlignedBB FLOWER_AABB = new AxisAlignedBB(0, 0, 0, 1, 1, 1);

    public BlockGrass(String name, Material material) {
        super(name, material);
    }

    @Nonnull
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FLOWER_AABB;
    }
}
