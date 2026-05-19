package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.item.ModItems;
import com.fuzhudada.error.item.sword.ISteamWeapon;
import com.fuzhudada.error.particle.ModParticleHandler;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class BlockCampfire extends Block implements com.somebody.idlframewok.util.IHasModel{
    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
    public static final PropertyInteger STATE = PropertyInteger.create("state",0,2);

    public BlockCampfire(String name , Material material , SoundType type) {
        super(material);
        setSoundType(type);
        this.setDefaultState(this.blockState.getBaseState().withProperty(STATE,1));
        lightValue=15;
        lightOpacity=7;

        setUnlocalizedName(name);
        setRegistryName(name);

        ModBlocks.BLOCKS.add(this);
    }

    public boolean onBlockActivated(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, EntityPlayer playerIn, @Nonnull EnumHand hand, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemStack=playerIn.getHeldItem(hand);
        if(state.getValue(STATE) == 0){
            if(itemStack.getItem()==Items.FLINT_AND_STEEL){
                worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                state=state.withProperty(STATE,1);
                worldIn.setBlockState(pos, state, 3);
                itemStack.damageItem(1,playerIn);
                return true;
            }else if(itemStack.getItem()==ModItems.PEN_HUO_QI && ISteamWeapon.shrinkSteam(itemStack,5)){
                state=state.withProperty(STATE,1);
                worldIn.setBlockState(pos, state, 3);
                return true;
            }else if(itemStack.getItem()==ModItems.PEN_HUO_QI2 && ISteamWeapon.shrinkSteam(itemStack,5)){
                state=state.withProperty(STATE,2);
                worldIn.setBlockState(pos, state, 3);
                return true;
            }
        }else{
            if(itemStack.getItem() instanceof ItemSpade){
                worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                state=state.withProperty(STATE,0);
                worldIn.setBlockState(pos, state, 3);
                itemStack.damageItem(1,playerIn);
                return true;
            }else if(itemStack.getItem()==ModItems.WATER_JAR){
                worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                state=state.withProperty(STATE,0);
                worldIn.setBlockState(pos, state, 3);
                itemStack.shrink(1);
                if (!playerIn.inventory.addItemStackToInventory(new ItemStack(ModItems.KONG_GUAN_ZI)))
                {
                    playerIn.dropItem(new ItemStack(ModItems.KONG_GUAN_ZI), false);
                }
                return true;
            }
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("incomplete-switch")
    public void randomDisplayTick(IBlockState stateIn, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull Random rand)
    {
        if (stateIn.getValue(STATE)>0 && rand.nextDouble()<0.75)
        {
            if(worldIn.getBlockState(pos.down()).getBlock() == Blocks.HAY_BLOCK){
                worldIn.spawnParticle(ModParticleHandler.CAMPFIRE , pos.getX()+0.2+Math.random()*0.6, pos.getY() + 0.5, pos.getZ()+0.2+Math.random()*0.6, 0.0D, 0.1D, 0.0D,275);
            }else{
                worldIn.spawnParticle(ModParticleHandler.CAMPFIRE, pos.getX()+0.2+Math.random()*0.6, pos.getY() + 0.5, pos.getZ()+0.2+Math.random()*0.6, 0.0D, 0.1D, 0.0D, 110);
            }
        }
    }

    public void onEntityWalk(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull Entity entityIn){
        if(worldIn.getBlockState(pos).getValue(STATE)>0 && entityIn instanceof EntityLivingBase){
            EntityLivingBase entityLivingBase=(EntityLivingBase) entityIn;
            if(!entityLivingBase.isPotionActive(MobEffects.FIRE_RESISTANCE)){
                entityLivingBase.setFire(3);
            }
        }
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if(state.getValue(STATE)<2){
            return ModItems.CAMPFIRE;
        }
        return ModItems.SOUL_CAMPFIRE;
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        if(state.getValue(STATE)<2){
            return new ItemStack(ModItems.CAMPFIRE);
        }
        return new ItemStack(ModItems.SOUL_CAMPFIRE);
    }

    @Nonnull
    public AxisAlignedBB getBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess source, @Nonnull BlockPos pos)
    {
        return AABB;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(@Nonnull IBlockState blockState, @Nonnull IBlockAccess worldIn, @Nonnull BlockPos pos)
    {
        return AABB;
    }

    @Nonnull
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
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
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(STATE, meta);
    }

    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(STATE);
    }

    @Nonnull
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, STATE);
    }

    @Override
    public void registerModels() {
        com.somebody.idlframewok.IdlFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
