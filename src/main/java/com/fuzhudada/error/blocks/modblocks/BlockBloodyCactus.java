package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.item.ModItems;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Random;

public class BlockBloodyCactus extends BlockCactus implements com.somebody.idlframewok.util.IHasModel {
    public BlockBloodyCactus(String name){
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.CLOTH);

        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
        ModBlocks.BLOCKS.add(this);
    }

    @Nonnull
    public Item getItemDropped(@Nonnull IBlockState p_getItemDropped_1_, @Nonnull Random p_getItemDropped_2_, int p_getItemDropped_3_) {
        return Item.getItemFromBlock(this);
    }

    @Nonnull
    @Override
    public ItemStack getPickBlock(@Nonnull IBlockState state , @Nonnull RayTraceResult target , @Nonnull World world , @Nonnull BlockPos pos , @Nonnull EntityPlayer player) {
        return new ItemStack(ModBlocks.BLOODY_CACTUS);
    }

    @Override
    public boolean canSustainPlant(@Nonnull IBlockState state , @Nonnull IBlockAccess world , @Nonnull BlockPos pos , @Nonnull EnumFacing direction , @Nonnull IPlantable plantable) {
        return this == ModBlocks.BLOODY_CACTUS || world.getBlockState(pos).getBlock() == Blocks.SAND;
    }

    @Override
    public void registerModels() {
        com.somebody.idlframewok.IdlFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
