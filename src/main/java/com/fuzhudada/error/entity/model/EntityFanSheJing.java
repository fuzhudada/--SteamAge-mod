package com.fuzhudada.error.entity.model;

import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public class EntityFanSheJing extends EntityCreature {
    public EntityFanSheJing(World worldIn) {
        super(worldIn);
        this.setSize(this.width*1.2F, this.height);
    }
}
