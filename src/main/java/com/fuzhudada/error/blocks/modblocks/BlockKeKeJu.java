package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.item.ModItems;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Objects;

public class BlockKeKeJu extends BlockBush implements net.minecraftforge.common.IPlantable, com.somebody.idlframewok.util.IHasModel {
    protected static final AxisAlignedBB FLOWER_AABB = new AxisAlignedBB(0.25D, 0D, 0.25D, 0.75D, 0.8D, 0.75D);

    public BlockKeKeJu(String name, Material material) {
        super(material);
        this.setSoundType(SoundType.PLANT);
        setUnlocalizedName(name);
        setRegistryName(name);

        if(!name.equals("lotus")) {
            ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
        }
        ModBlocks.BLOCKS.add(this);
    }

    @Nonnull
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FLOWER_AABB;
    }

    @Nonnull
    public ItemStack getPickBlock(@Nonnull IBlockState state, @Nonnull RayTraceResult target, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull EntityPlayer player)
    {
        return new ItemStack(Item.getItemFromBlock(this));
    }

    @Override
    public void registerModels() {
        com.somebody.idlframewok.IdlFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}