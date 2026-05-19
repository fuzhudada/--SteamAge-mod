package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.item.ModItems;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Objects;

public class BlockCrystalGrass extends BlockBush implements com.somebody.idlframewok.util.IHasModel {
    private static final AxisAlignedBB CRYSTAL_GRASS_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

    public BlockCrystalGrass(String name){
        super(Material.PLANTS, MapColor.GRAY);
        this.setSoundType(SoundType.GLASS);
        setUnlocalizedName(name);
        setRegistryName(name);
        this.lightValue=7;

        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
        ModBlocks.BLOCKS.add(this);
    }

    public void onEntityCollidedWithBlock(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Entity entityIn)
    {
        if(entityIn.motionX*entityIn.motionX+entityIn.motionZ*entityIn.motionZ>0 && worldIn.rand.nextFloat()<0.2) {
            worldIn.playSound(null,pos,SoundEvents.BLOCK_NOTE_CHIME, SoundCategory.BLOCKS,1,worldIn.rand.nextFloat()*2);
        }
    }

    @Nonnull
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public void registerModels() {
        com.somebody.idlframewok.IdlFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Nonnull
    public ItemStack getPickBlock(@Nonnull IBlockState state, @Nonnull RayTraceResult target, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull EntityPlayer player)
    {
        return new ItemStack(this);
    }

    @Nonnull
    public AxisAlignedBB getBoundingBox(@Nonnull IBlockState p_getBoundingBox_1_, @Nonnull IBlockAccess p_getBoundingBox_2_, @Nonnull BlockPos p_getBoundingBox_3_) {
        return CRYSTAL_GRASS_AABB;
    }
}
