package com.fuzhudada.error.blocks.itemBlock;

import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.blocks.modblocks.BlockGoldenLily;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Objects;

import static com.fuzhudada.error.blocks.modblocks.BlockGoldenLilyBig.FACING;
import static com.fuzhudada.error.blocks.modblocks.BlockGoldenLilyBig.PIECE;

public class ItemBlockGlodenLilyBig extends ItemBlock {
    public ItemBlockGlodenLilyBig(Block block) {
        super(block);
    }

    @Nonnull
    public EnumActionResult onItemUse(@Nonnull EntityPlayer player, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull EnumHand hand, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        return EnumActionResult.PASS;
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);
        Block placedBlock= ModBlocks.GOLDEN_LILY;

        if(raytraceresult == null){
            return new ActionResult<>(EnumActionResult.PASS, itemstack);
        }else{
            if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK)
            {
                BlockPos blockpos = raytraceresult.getBlockPos();

                if (!worldIn.isBlockModifiable(playerIn, blockpos) || !playerIn.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, itemstack)
                        || !worldIn.isBlockModifiable(playerIn, blockpos.east()) || !playerIn.canPlayerEdit(blockpos.offset(raytraceresult.sideHit).east(), raytraceresult.sideHit, itemstack)
                        || !worldIn.isBlockModifiable(playerIn, blockpos.south()) || !playerIn.canPlayerEdit(blockpos.offset(raytraceresult.sideHit).south(), raytraceresult.sideHit, itemstack)
                        || !worldIn.isBlockModifiable(playerIn, blockpos.east().south()) || !playerIn.canPlayerEdit(blockpos.offset(raytraceresult.sideHit).east().south(), raytraceresult.sideHit, itemstack)) {
                    return new ActionResult<>(EnumActionResult.FAIL, itemstack);
                }

                BlockPos blockpos1 = blockpos.up();
                IBlockState iblockstate = worldIn.getBlockState(blockpos);
                IBlockState iblockstatee = worldIn.getBlockState(blockpos.east());
                IBlockState iblockstatese = worldIn.getBlockState(blockpos.east().south());
                IBlockState iblockstates = worldIn.getBlockState(blockpos.south());

                if (iblockstate.getMaterial() == Material.WATER && iblockstate.getValue(BlockLiquid.LEVEL) == 0
                        && iblockstatee.getMaterial() == Material.WATER && iblockstatee.getValue(BlockLiquid.LEVEL) == 0
                        && iblockstatee.getMaterial() == Material.WATER && iblockstatee.getValue(BlockLiquid.LEVEL) == 0
                        && iblockstatese.getMaterial() == Material.WATER && iblockstatee.getValue(BlockLiquid.LEVEL) == 0
                        && iblockstates.getMaterial() == Material.WATER && iblockstatee.getValue(BlockLiquid.LEVEL) == 0
                        && worldIn.isAirBlock(blockpos1) && worldIn.isAirBlock(blockpos1.east()) && worldIn.isAirBlock(blockpos1.east().south()) && worldIn.isAirBlock(blockpos1.south())) {
                    // special case for handling block placement with water lilies
                    net.minecraftforge.common.util.BlockSnapshot blocksnapshot = net.minecraftforge.common.util.BlockSnapshot.getBlockSnapshot(worldIn, blockpos1);

                    EnumFacing direction = playerIn.getHorizontalFacing();

                    final IBlockState lilypad = ModBlocks.GOLDEN_LILY_BIG.getDefaultState().withProperty(FACING, direction);
                    worldIn.setBlockState(blockpos1, lilypad.withProperty(PIECE, 0), 10);
                    worldIn.setBlockState(blockpos1.east(), lilypad.withProperty(PIECE, 1), 10);
                    worldIn.setBlockState(blockpos1.east().south(), lilypad.withProperty(PIECE, 2), 10);
                    worldIn.setBlockState(blockpos1.south(), lilypad.withProperty(PIECE, 3), 11);

                    if (net.minecraftforge.event.ForgeEventFactory.onPlayerBlockPlace(playerIn, blocksnapshot, net.minecraft.util.EnumFacing.UP, handIn).isCanceled())
                    {
                        blocksnapshot.restore(true, false);
                        return new ActionResult<>(EnumActionResult.FAIL, itemstack);
                    }

                    if (playerIn instanceof EntityPlayerMP) {
                        CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)playerIn, blockpos1, itemstack);
                        CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)playerIn, blockpos1.east(), itemstack);
                        CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)playerIn, blockpos1.east().south(), itemstack);
                        CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)playerIn, blockpos1.south(), itemstack);
                    }

                    if (!playerIn.capabilities.isCreativeMode)
                    {
                        itemstack.shrink(1);
                    }

                    playerIn.addStat(Objects.requireNonNull(StatList.getObjectUseStats(this)));
                    worldIn.playSound(playerIn, blockpos, SoundEvents.BLOCK_WATERLILY_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
                }
            }

            return new ActionResult<>(EnumActionResult.FAIL, itemstack);
        }
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
}
