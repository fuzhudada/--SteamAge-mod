package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.blocks.itemBlock.ItemBlockGlodenLilyBig;
import com.fuzhudada.error.item.ModItems;
import com.google.common.collect.Lists;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockLilyPad;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class BlockGoldenLilyBig extends BlockLilyPad implements com.somebody.idlframewok.util.IHasModel {
    protected static final AxisAlignedBB LILY_PAD_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.09375D, 1.0D);
    public static final PropertyInteger PIECE = PropertyInteger.create("piece",0,3);
    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    private boolean isSelfDestructing = false;
    private final boolean hasFlower;

    public BlockGoldenLilyBig(String name,boolean hasFlower) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.PLANT);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(PIECE,0));

        if(!hasFlower){
            ModItems.ITEMS.add(new ItemBlockGlodenLilyBig(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
        }
        ModBlocks.BLOCKS.add(this);
        this.hasFlower=hasFlower;
    }

    @Nonnull
    public Item getItemDropped(@Nonnull IBlockState p_getItemDropped_1_, @Nonnull Random p_getItemDropped_2_, int p_getItemDropped_3_) {
        return Item.getItemFromBlock(ModBlocks.GOLDEN_LILY_BIG);
    }

    public void getDrops(@Nonnull NonNullList<ItemStack> drops, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull IBlockState state, int fortune)
    {
        drops.add(new ItemStack(ModBlocks.GOLDEN_LILY_BIG));
    }

    @Nonnull
    @Override
    public ItemStack getPickBlock(@Nonnull IBlockState state , @Nonnull RayTraceResult target , @Nonnull World world , @Nonnull BlockPos pos , @Nonnull EntityPlayer player) {
        return new ItemStack(ModBlocks.GOLDEN_LILY_BIG);
    }

    @Override
    public void registerModels() {
        com.somebody.idlframewok.IdlFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    public void addCollisionBoxToList(@Nonnull IBlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull AxisAlignedBB entityBox, @Nonnull List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        if (!(entityIn instanceof EntityBoat))
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, LILY_PAD_AABB);
        }
    }

    @Nonnull
    public AxisAlignedBB getBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess source, @Nonnull BlockPos pos)
    {
        return LILY_PAD_AABB;
    }

    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING,getFacing(meta & 3)).withProperty(PIECE,(meta & 12)>>2);
    }

    public int getMetaFromState(IBlockState state) {
        return getMetaForFacing(state.getValue(FACING)) | (state.getValue(PIECE) << 2);
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
        return new BlockStateContainer(this, FACING,PIECE);
    }

    @Override
    public void onBlockDestroyedByExplosion(@Nonnull World worldIn , @Nonnull BlockPos pos , @Nonnull Explosion explosionIn) {
        dropBlockAsItem(worldIn,pos,worldIn.getBlockState(pos),0);
        if (!this.isSelfDestructing) {
            this.setGiantBlockToAir(worldIn, pos, worldIn.getBlockState(pos), true);
        }
        super.onBlockDestroyedByExplosion(worldIn , pos , explosionIn);
    }

    @Override
    public void onBlockHarvested(@Nonnull World worldIn , @Nonnull BlockPos pos , @Nonnull IBlockState state , @Nonnull EntityPlayer player) {
        if(!player.isCreative()) dropBlockAsItem(worldIn,pos,worldIn.getBlockState(pos),0);
        if (!this.isSelfDestructing) {
            this.setGiantBlockToAir(worldIn, pos, state, !player.isCreative());
        }
        super.onBlockHarvested(worldIn , pos , state , player);
    }

    private void setGiantBlockToAir(World world, BlockPos pos, IBlockState state, boolean dropFlower) {
        // this flag is not threadsafe
        this.isSelfDestructing = true;

        for (BlockPos check : this.getAllMyBlocks(pos, state)) {
            IBlockState stateThere = world.getBlockState(check);
            if (stateThere.getBlock() instanceof BlockGoldenLilyBig) {
                if(dropFlower && !world.isRemote && ((BlockGoldenLilyBig)stateThere.getBlock()).hasFlower){
                    EntityItem item=new EntityItem(world);
                    item.setItem(new ItemStack(ModBlocks.LOTUS));
                    item.setPosition(check.getX()+0.5,check.getY()+0.5,check.getZ()+0.5);
                    world.spawnEntity(item);
                }
                world.destroyBlock(check, false);
            }
        }

        this.isSelfDestructing = false;
    }

    public List<BlockPos> getAllMyBlocks(BlockPos pos, IBlockState state) {
        List<BlockPos> pieces = Lists.newArrayListWithCapacity(4);
        if (state.getBlock() == this) {
            // find NW corner
            BlockPos nwPos = pos;
            switch (state.getValue(PIECE)) {
                case 1:
                    nwPos = nwPos.west();
                    break;
                case 2:
                    nwPos = nwPos.north().west();
                    break;
                case 3:
                    nwPos = nwPos.north();
                    break;
                default:
                    break;
            }

            pieces.add(nwPos);
            pieces.add(nwPos.south());
            pieces.add(nwPos.east());
            pieces.add(nwPos.south().east());
        }

        return pieces;
    }
}
