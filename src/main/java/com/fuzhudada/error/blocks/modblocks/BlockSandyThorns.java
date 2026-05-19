package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.item.ModItems;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockSandyThorns extends BlockBush implements IGrowable {
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);
    private static final AxisAlignedBB[] SANDY_THORNS_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.35D, 0.0D, 0.35D, 0.65D, 0.3125D, 0.65D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};

    public BlockSandyThorns(String name){
        super(Material.PLANTS, MapColor.GRAY);
        this.setSoundType(SoundType.PLANT);
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
        this.setTickRandomly(true);
        setUnlocalizedName(name);
        setRegistryName(name);

        ModBlocks.BLOCKS.add(this);
    }

    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.SAND;
    }

    @Nonnull
    public AxisAlignedBB getBoundingBox(IBlockState state, @Nonnull IBlockAccess source, @Nonnull BlockPos pos)
    {
        return SANDY_THORNS_AABB[state.getValue(AGE)/5];
    }

    public void updateTick(@Nonnull World worldIn, @Nonnull BlockPos pos, IBlockState state, @Nonnull Random rand)
    {
        int i = state.getValue(AGE);

        if ((i < 15 || (i == 15 && worldIn.isAirBlock(pos.up()))) && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(2)==0))
        {
            if(i==15){
                worldIn.setBlockState(pos.up(), ModBlocks.SANDY_THORNS_FLOWER.getDefaultState());
            }else{
                IBlockState newState = state.withProperty(AGE, i + 1);
                worldIn.setBlockState(pos, newState, 2);
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, newState);
            }
        }

        super.updateTick(worldIn, pos, state, rand);
    }

    @Nonnull
    public Item getItemDropped(@Nonnull IBlockState state, @Nonnull Random rand, int fortune)
    {
        return Items.AIR;
    }

    public int quantityDropped(@Nonnull Random random)
    {
        return 0;
    }

    @Nonnull
    public ItemStack getItem(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state)
    {
        return new ItemStack(ModItems.SANDY_THORNS_SEED);
    }

    @Nonnull
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AGE, meta);
    }

    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(AGE);
    }

    @Override
    public void getDrops(@Nonnull net.minecraft.util.NonNullList<ItemStack> drops, @Nonnull net.minecraft.world.IBlockAccess world, @Nonnull BlockPos pos, IBlockState state, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if (state.getValue(AGE) >= 10)
        {
            int count = 2 + rand.nextInt(3) + (fortune > 0 ? rand.nextInt(fortune + 1) : 0);
            for (int i = 0; i < count; i++)
            {
                drops.add(new ItemStack(ModItems.SANDY_THORNS));
            }
        }else{
            drops.add(new ItemStack(ModItems.SANDY_THORNS_SEED));
        }
    }

    @Nonnull
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, AGE);
    }

    public void onEntityCollidedWithBlock(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Entity entityIn)
    {
        entityIn.setInWeb();
        if(!(entityIn instanceof EntityItem)) {
            entityIn.attackEntityFrom(DamageSource.CACTUS, 1);
        }
    }

    public boolean canGrow(@Nonnull World worldIn, @Nonnull BlockPos pos, IBlockState state, boolean isClient)
    {
        int i = state.getValue(AGE);
        return i < 15 || (i == 15 && worldIn.isAirBlock(pos.up()));
    }

    public boolean canUseBonemeal(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull BlockPos pos, @Nonnull IBlockState state)
    {
        return true;
    }

    public void grow(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull BlockPos pos, @Nonnull IBlockState state)
    {
        if(Math.random()<0.5){
            int i = state.getValue(AGE);

            if (i < 15 || (i == 15 && worldIn.isAirBlock(pos.up())))
            {
                if(i==15){
                    worldIn.setBlockState(pos.up(), ModBlocks.SANDY_THORNS_FLOWER.getDefaultState());
                }else{
                    IBlockState newState = state.withProperty(AGE, Math.min(15,i + 5));
                    worldIn.setBlockState(pos, newState, 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, newState);
                }
            }
        }
    }
}
