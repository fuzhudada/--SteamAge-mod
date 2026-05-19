package com.fuzhudada.error.entity.model;

import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.world.World;

public class EntityArmorStandWithHand extends EntityArmorStand {
    public EntityArmorStandWithHand(World worldIn) {
        super(worldIn);
    }

    public EntityArmorStandWithHand(World worldIn, double posX, double posY, double posZ)
    {
        this(worldIn);
        this.setPosition(posX, posY, posZ);
    }

//    public boolean getShowArms()
//    {
//        return true;
//    }
}
