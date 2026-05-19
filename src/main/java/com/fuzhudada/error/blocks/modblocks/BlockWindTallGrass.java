package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.blocks.ModBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class BlockWindTallGrass extends BlockGrass{
    public BlockWindTallGrass(String name, Material material) {
        super(name, material);
    }

    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT || state.getBlock() == ModBlocks.WIND_TALL_GRASS;
    }
}
