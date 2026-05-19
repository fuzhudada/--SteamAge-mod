package com.fuzhudada.error.entity.model;

import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public class EntitySmallFlower extends EntityCreature {
    public EntitySmallFlower(World worldIn) {
        super(worldIn);
        this.setSize(this.width*0.6F, this.height);
    }
}
