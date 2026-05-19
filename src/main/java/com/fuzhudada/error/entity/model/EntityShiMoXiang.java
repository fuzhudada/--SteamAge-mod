package com.fuzhudada.error.entity.model;

import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public class EntityShiMoXiang extends EntityCreature {
    public EntityShiMoXiang(World worldIn) {
        super(worldIn);
        this.setSize(this.width * 4.0F, this.height * 2.0F);
    }
}
