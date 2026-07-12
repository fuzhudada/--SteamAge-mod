package com.fuzhudada.error.blocks.modblocks;

import com.fuzhudada.error.blocks.BlockBase;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.*;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockVentFan extends BlockBase {
    public static final PropertyBool ACTIVATED = PropertyBool.create("activated");
    public static final PropertyDirection FACING = BlockDirectional.FACING;
    public static final int[] DX = {0, 0, -4, 4, 0, 0}, DY = {0, 0, 0, 0, 4, -4}, DZ = {-4, 4, 0, 0, 0, 0};

    public BlockVentFan(String name, Material material, SoundType type) {
        super(name, material, type);
        this.setDefaultState(this.blockState.getBaseState().withProperty(ACTIVATED, false).withProperty(FACING, EnumFacing.NORTH));
    }

    public boolean onBlockActivated(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand hand, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ) {
        boolean newState = !state.getValue(ACTIVATED);
        worldIn.setBlockState(pos, state.withProperty(ACTIVATED, newState), 3);
        worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 1F, newState ? 1.2F : 0.8F);
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(@Nonnull IBlockState stateIn, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull Random rand){
        if(stateIn.getValue(ACTIVATED)){
            for(int i=0;i<3;i++){
                int f=getMetaForFacing(stateIn.getValue(FACING));
                int dx=DX[f],dy=DY[f],dz=DZ[f];
                for(int j=0;j<2;j++){
                    double x=pos.getX()+0.5+dx+Math.random()*1.2-0.6;
                    double y=pos.getY()+0.5+dy+Math.random()*1.2-0.6;
                    double z=pos.getZ()+0.5+dz+Math.random()*1.2-0.6;
                    double dist=Math.sqrt((pos.getX()+0.5-x)*(pos.getX()+0.5-x)+(pos.getY()+0.5-y)*(pos.getY()+0.5-y)+(pos.getZ()+0.5-z)*(pos.getZ()+0.5-z));
                    if(dist<0.01) dist=0.01;
                    double vx=(pos.getX()+0.5-x)/dist;
                    double vy=(pos.getY()+0.5-y)/dist;
                    double vz=(pos.getZ()+0.5-z)/dist;
                    worldIn.spawnParticle(EnumParticleTypes.CLOUD,x,y,z,vx*0.3,vy*0.3,vz*0.3);
                }
            }
        }
    }

    @Nonnull
    public IBlockState getStateForPlacement(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ, int meta, @Nonnull EntityLivingBase placer)
    {
        IBlockState iblockstate = this.getDefaultState();
        iblockstate = iblockstate.withProperty(FACING, facing);
        return iblockstate;
    }

    protected static EnumFacing getFacing(int meta)
    {
        switch (meta & 7)
        {
            case 0:
                return EnumFacing.NORTH;
            case 1:
                return EnumFacing.SOUTH;
            case 2:
                return EnumFacing.WEST;
            case 3:
                return EnumFacing.EAST;
            case 4:
                return EnumFacing.UP;
            case 5:
            default:
                return EnumFacing.DOWN;
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
                return 3;
            case UP:
                return 4;
            case DOWN:
            default:
                return 5;
        }
    }

    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(ACTIVATED, (meta & 8) == 8).withProperty(FACING, getFacing(meta));
    }

    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | getMetaForFacing(state.getValue(FACING));
        if(state.getValue(ACTIVATED)) i |= 8;

        return i;
    }

    @Nonnull
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Nonnull
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, ACTIVATED, FACING);
    }
}
