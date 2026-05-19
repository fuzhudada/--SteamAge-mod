package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.blocks.itemBlock.ItemBlockLotus;
import com.fuzhudada.error.item.ModItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import java.util.Objects;

public class BlockLotus extends BlockGrass{
    public BlockLotus(String name, Material material) {
        super(name, material);
        ModItems.ITEMS.add(new ItemBlockLotus(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT || state.getMaterial() == Material.WATER;
    }
}
