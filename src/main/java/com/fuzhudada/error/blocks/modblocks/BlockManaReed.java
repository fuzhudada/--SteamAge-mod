package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.item.ModItems;
import net.minecraft.block.BlockReed;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Random;

public class BlockManaReed extends BlockReed implements com.somebody.idlframewok.util.IHasModel {
    public BlockManaReed(String name){
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.PLANT);

        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
        ModBlocks.BLOCKS.add(this);
    }

    @Nonnull
    public Item getItemDropped(@Nonnull IBlockState p_getItemDropped_1_, @Nonnull Random p_getItemDropped_2_, int p_getItemDropped_3_) {
        return Item.getItemFromBlock(ModBlocks.MANA_REED);
    }

    @Nonnull
    public ItemStack getItem(@Nonnull World p_getItem_1_, @Nonnull BlockPos p_getItem_2_, @Nonnull IBlockState p_getItem_3_) {
        return new ItemStack(ModBlocks.MANA_REED);
    }

    @Override
    public void registerModels() {
        com.somebody.idlframewok.IdlFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
