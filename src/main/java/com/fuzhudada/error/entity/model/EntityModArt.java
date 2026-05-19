package com.fuzhudada.error.entity.model;

import com.fuzhudada.error.item.ModItems;
import com.google.common.collect.Lists;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class EntityModArt extends EntityHanging {
    public EnumArt art;

    public EntityModArt(World worldIn) {
        super(worldIn);
    }

    public EntityModArt(World worldIn, BlockPos pos, EnumFacing facing)
    {
        super(worldIn, pos);
        List<EnumArt> list = Lists.newArrayList();
        int i = 0;

        for (EnumArt entitypainting$enumart : EnumArt.values())
        {
            this.art = entitypainting$enumart;
            this.updateFacingWithBoundingBox(facing);

            if (this.onValidSurface())
            {
                list.add(entitypainting$enumart);
                int j = entitypainting$enumart.sizeX * entitypainting$enumart.sizeY;

                if (j > i)
                {
                    i = j;
                }
            }
        }

        if (!list.isEmpty())
        {
            Iterator<EnumArt> iterator = list.iterator();

            while (iterator.hasNext())
            {
                EnumArt entitypainting$enumart1 = iterator.next();

                if (entitypainting$enumart1.sizeX * entitypainting$enumart1.sizeY < i)
                {
                    iterator.remove();
                }
            }

            this.art = list.get(this.rand.nextInt(list.size()));
        }

        this.updateFacingWithBoundingBox(facing);
    }

    @SideOnly(Side.CLIENT)
    public EntityModArt(World worldIn, BlockPos pos, EnumFacing facing, String title)
    {
        this(worldIn, pos, facing);

        for (EnumArt entitypainting$enumart : EnumArt.values())
        {
            if (entitypainting$enumart.title.equals(title))
            {
                this.art = entitypainting$enumart;
                break;
            }
        }

        this.updateFacingWithBoundingBox(facing);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        compound.setString("Motive", this.art.title);
        super.writeEntityToNBT(compound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        String s = compound.getString("Motive");

        for (EnumArt entitypainting$enumart : EnumArt.values())
        {
            if (entitypainting$enumart.title.equals(s))
            {
                this.art = entitypainting$enumart;
            }
        }

        if (this.art == null)
        {
            this.art = EnumArt.BIHUA;
        }

        super.readEntityFromNBT(compound);
    }

    public int getWidthPixels()
    {
        return this.art.sizeX;
    }

    public int getHeightPixels()
    {
        return this.art.sizeY;
    }

    /**
     * Called when this entity is broken. Entity parameter may be null.
     */
    public void onBroken(@Nullable Entity brokenEntity)
    {
        if (this.world.getGameRules().getBoolean("doEntityDrops"))
        {
            this.playSound(SoundEvents.ENTITY_PAINTING_BREAK, 1.0F, 1.0F);

            if (brokenEntity instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer)brokenEntity;

                if (entityplayer.capabilities.isCreativeMode)
                {
                    return;
                }
            }

            this.entityDropItem(new ItemStack(ModItems.BI_HUA), 0.0F);
        }
    }

    public void playPlaceSound()
    {
        this.playSound(SoundEvents.ENTITY_PAINTING_PLACE, 1.0F, 1.0F);
    }

    /**
     * Sets the location and Yaw/Pitch of an entity in the world
     */
    public void setLocationAndAngles(double x, double y, double z, float yaw, float pitch)
    {
        this.setPosition(x, y, z);
    }

    /**
     * Set the position and rotation values directly without any clamping.
     */
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport)
    {
        BlockPos blockpos = this.hangingPosition.add(x - this.posX, y - this.posY, z - this.posZ);
        this.setPosition(blockpos.getX() , blockpos.getY() , blockpos.getZ());
    }

    public enum EnumArt
    {
        BIHUA("bihua", 64, 64);

        /** Painting Title. */
        public final String title;
        public final int sizeX;
        public final int sizeY;

        EnumArt(String titleIn , int width , int height)
        {
            this.title = titleIn;
            this.sizeX = width;
            this.sizeY = height;
        }
    }
}
