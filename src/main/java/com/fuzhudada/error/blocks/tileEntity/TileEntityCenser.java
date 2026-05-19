package com.fuzhudada.error.blocks.tileEntity;

import com.fuzhudada.error.blocks.ModBlocks;
import com.fuzhudada.error.blocks.machine.BlockCenser;
import com.fuzhudada.error.item.XunXiang.ItemXunXiang;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

import javax.annotation.Nonnull;
import java.util.List;

public class TileEntityCenser extends TileEntity implements ITickable {
    @Override
    public void update() {
        IBlockState state=world.getBlockState(pos);
        if(!world.isRemote && state.getBlock()== ModBlocks.CENSER) {
            int stick=state.getValue(BlockCenser.STICK);
            boolean burning=state.getValue(BlockCenser.BURNING);
            if (stick > 0 && burning) {
                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class , e -> e.isEntityAlive() && e.getDistanceSq(pos) < 32 * 32);
                for (EntityLivingBase entityLivingBase : G) {
                    entityLivingBase.addPotionEffect(new PotionEffect(ItemXunXiang.getXunXiangFromId(stick-1).PotionOnPlayer));
                }
            }
        }
    }

    public void readFromNBT(@Nonnull NBTTagCompound compound)
    {
        super.readFromNBT(compound);
    }

    @Nonnull
    public NBTTagCompound writeToNBT(@Nonnull NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        return compound;
    }
}
