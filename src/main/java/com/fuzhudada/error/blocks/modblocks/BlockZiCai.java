package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.blocks.BlockBase;
import com.fuzhudada.error.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class BlockZiCai extends BlockBase implements IPlantable {
    public BlockZiCai(String name, Material material, SoundType type) {
        super(name, material, type);
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
        this.setTickRandomly(true);
    }

    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 8);

    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    public void updateTick(World worldIn, BlockPos pos, @Nonnull IBlockState state, @Nonnull Random rand)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());
        if (soil.getBlock() == ModBlocks.SHAN_HU1||soil.getBlock() == ModBlocks.SHAN_HU2
                ||soil.getBlock() == ModBlocks.SHAN_HU3||soil.getBlock() == ModBlocks.SHAN_HU4||soil.getBlock() == ModBlocks.SHAN_HU5)
        {
            double k = Math.random();
            BlockPos Pos2 = pos;
            if (k<0.25) {
                Pos2 = pos.east();
            }
            if ( k >= 0.25 && k<0.5 ) {
                Pos2 = pos.south();
            }
            if ( k >= 0.5 && k<0.75 ) {
                Pos2 = pos.west();
            }
            if (k >= 0.75) {
                Pos2 = pos.north();
            }
            IBlockState SF = worldIn.getBlockState(Pos2.down());
            if (worldIn.isAirBlock(Pos2) && (SF.getBlock() == ModBlocks.SHAN_HU1||SF.getBlock() == ModBlocks.SHAN_HU2
                    ||SF.getBlock() == ModBlocks.SHAN_HU3||SF.getBlock() == ModBlocks.SHAN_HU4||SF.getBlock() == ModBlocks.SHAN_HU5))
            {
                    int j = state.getValue(AGE);

                    if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, true))
                    {
                        if (j == 8)
                        {
                            worldIn.setBlockState(Pos2, this.getDefaultState());
                            worldIn.setBlockState(pos, state.withProperty(AGE, 0), 2);
                        }
                        else
                        {
                            worldIn.setBlockState(pos, state.withProperty(AGE, j + 1), 2);
                        }
                        net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                    }
            }
        }
    }

    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(AGE);
    }

    @Nonnull
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AGE, meta);
    }

    @Nonnull
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, AGE);
    }

    @Override
    public IBlockState getPlant(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() != this) return getDefaultState();
        return state;
    }

    @Override
    public net.minecraftforge.common.EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
        return EnumPlantType.Plains;
    }

    protected static final AxisAlignedBB ZI_CAI_AABB = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.1D, 1D);

    @Nonnull
    public AxisAlignedBB getBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess source, @Nonnull BlockPos pos)
    {
        return ZI_CAI_AABB;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(@Nonnull IBlockState blockState, @Nonnull IBlockAccess worldIn, @Nonnull BlockPos pos)
    {
        return NULL_AABB;
    }

    public boolean isFullCube(@Nonnull IBlockState state)
    {
        return false;
    }

    public boolean isPassable(@Nonnull IBlockAccess worldIn, @Nonnull BlockPos pos)
    {
        return true;
    }

    public boolean isOpaqueCube(@Nonnull IBlockState state)
    {
        return false;
    }

    @Nonnull
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());
        return super.canPlaceBlockAt(worldIn, pos) && (soil.getBlock() == ModBlocks.SHAN_HU1||soil.getBlock() == ModBlocks.SHAN_HU2
                ||soil.getBlock() == ModBlocks.SHAN_HU3||soil.getBlock() == ModBlocks.SHAN_HU4||soil.getBlock() == ModBlocks.SHAN_HU5);
    }

    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == ModBlocks.SHAN_HU1 || state.getBlock() == ModBlocks.SHAN_HU2|| state.getBlock() == ModBlocks.SHAN_HU3
                ||state.getBlock() == ModBlocks.SHAN_HU4||state.getBlock() == ModBlocks.SHAN_HU5;
    }

    public void neighborChanged(@Nonnull IBlockState state, @Nonnull World worldIn, @Nonnull BlockPos
            pos, @Nonnull Block blockIn, @Nonnull BlockPos fromPos)
    {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
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
        if (state.getBlock() == this)
        {
            IBlockState soil = worldIn.getBlockState(pos.down());
            return soil.getBlock() == ModBlocks.SHAN_HU1||soil.getBlock() == ModBlocks.SHAN_HU2
                ||soil.getBlock() == ModBlocks.SHAN_HU3||soil.getBlock() == ModBlocks.SHAN_HU4||soil.getBlock() == ModBlocks.SHAN_HU5;
        }
        return this.canSustainBush(worldIn.getBlockState(pos.down()));
    }
}
