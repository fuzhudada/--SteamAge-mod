package com.fuzhudada.error.blocks.machine;

import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.item.Item.ItemZhenQiGuanZi;
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
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Objects;

public class BlockSteamFiller  extends Block implements com.somebody.idlframewok.util.IHasModel {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public BlockSteamFiller(String name){
        super(Material.ROCK);
        setSoundType(SoundType.STONE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        setUnlocalizedName(name);
        setRegistryName(name);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    public void registerModels() {
        com.somebody.idlframewok.IdlFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    public boolean onBlockActivated(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, EntityPlayer playerIn, @Nonnull EnumHand hand, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack stack=playerIn.getHeldItem(hand);
        if(stack.getItem() instanceof ISteamWeapon){
            float k1 = ISteamWeapon.getSteam(stack);
            float k2 = ((ISteamWeapon) stack.getItem()).maxItemSteam();
            float s = k2 - k1;
            if (s > 0) {
                boolean flag=false;
                for (int i = 0; i < playerIn.inventory.getSizeInventory(); ++i) {
                    ItemStack stack1 = playerIn.inventory.getStackInSlot(i);
                    if (stack1.getItem() instanceof ItemZhenQiGuanZi && stack1 != stack) {
                        flag=true;
                        float k3 = ISteamWeapon.getSteam(stack1);
                        if (s < k3) {
                            k1=k2;
                            ISteamWeapon.setSteam(stack1 , k3 - s);
                            break;
                        } else {
                            stack1.shrink(1);
                            if (!playerIn.inventory.addItemStackToInventory(new ItemStack(ModItems.KONG_GUAN_ZI))) {
                                playerIn.dropItem(new ItemStack(ModItems.KONG_GUAN_ZI) , false);
                            }
                            s -= k3;
                            k1 += k3;
                        }
                    }
                }
                ISteamWeapon.setSteam(stack,k1);
                if(flag) {
                    worldIn.playSound(null , pos.getX() , pos.getY() , pos.getZ() , SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE , SoundCategory.BLOCKS , 2F , playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                    for (int i = 0; i < 15; i++) {
                        worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE , pos.getX() + 0.5 * Math.random() + 0.25 , pos.getY() + 1 , pos.getZ() + 0.5 * Math.random() + 0.25 , 0 , 0.1 , 0);
                    }
                    return true;
                }
            }
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
}
