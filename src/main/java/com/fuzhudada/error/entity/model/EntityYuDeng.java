package com.fuzhudada.error.entity.model;

import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public class EntityYuDeng extends EntityCreature {
    public EntityYuDeng(World worldIn) {
        super(worldIn);
        this.setSize(this.width, this.height * 2.0F);
    }
}