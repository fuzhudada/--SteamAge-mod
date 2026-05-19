package com.fuzhudada.error.blocks.modblocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockRuneLeaves extends BlockLeaves{
    public BlockRuneLeaves(String name, Material material, SoundType type) {
        super(name, material, type);
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(@Nonnull IBlockState stateIn, World worldIn, BlockPos pos, @Nonnull Random rand){
        worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE,pos.getX()+Math.random(),pos.getY()+Math.random(),pos.getZ()+Math.random(),0,-0.2,0);
    }
}
