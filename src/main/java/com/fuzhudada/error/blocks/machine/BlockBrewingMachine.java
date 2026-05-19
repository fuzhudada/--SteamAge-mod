package com.fuzhudada.error.blocks.machine;

import com.fuzhudada.error.blocks.BlockBase;
import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.item.ModItems;
import com.fuzhudada.error.item.sword.ISteamWeapon;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.HashMap;

public class BlockBrewingMachine extends BlockBase {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final HashMap<Item, Block> RECIPES = new HashMap<>();

    static {
        RECIPES.put(Items.WHEAT,ModBlocks.MAI_JIU);
        RECIPES.put(Items.POTATO,ModBlocks.FU_TE_JIA);
        RECIPES.put(ModItems.XUN_XIANG_GUO,ModBlocks.XUN_XIANG_GUO_SHI_JIU);
        RECIPES.put(ModItems.BOMB_FRUIT,ModBlocks.BAO_DAN_GUO_HONG_JIU);
        RECIPES.put(ModItems.SANDY_THORNS,ModBlocks.DAO_YOU);
        RECIPES.put(ModItems.WIND_FRUIT,ModBlocks.WIND_WINE);
    }

    public BlockBrewingMachine(String name , Material material , SoundType type) {
        super(name , material , type);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    public void getDrops(@Nonnull NonNullList<ItemStack> drops, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull IBlockState state, int fortune)
    {
        drops.add(new ItemStack(ModBlocks.BREWING_MACHINE));
    }

    public boolean onBlockActivated(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, EntityPlayer playerIn, @Nonnull EnumHand hand, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack stack=playerIn.getHeldItem(hand);
        if(RECIPES.containsKey(stack.getItem()) && ISteamWeapon.checkSteamOnPlayer(playerIn)>=25){
            ISteamWeapon.consumeSteamOnPlayer(playerIn,25);
            if (!playerIn.inventory.addItemStackToInventory(new ItemStack(RECIPES.get(stack.getItem())))) {
                playerIn.dropItem(new ItemStack(RECIPES.get(stack.getItem())) , false);
            }
            stack.shrink(1);
            worldIn.playSound(null , pos.getX() , pos.getY() , pos.getZ() , SoundEvents.BLOCK_PISTON_EXTEND , SoundCategory.BLOCKS , 2F , playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
            for (int i = 0; i < 15; i++) {
                worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE , pos.getX() + 0.5 * Math.random() + 0.25 , pos.getY() + 1 , pos.getZ() + 0.5 * Math.random() + 0.25 , 0 , 0.1 , 0);
            }
            return true;
        }
        return false;
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

    public boolean isOpaqueCube(@Nonnull IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(@Nonnull IBlockState state)
    {
        return false;
    }
}
