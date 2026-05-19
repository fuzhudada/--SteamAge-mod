package com.fuzhudada.error.entity.model;

import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public class EntityDaPao extends EntityCreature {
    public EntityDaPao(World worldIn) {
        super(worldIn);
        this.setSize(this.width * 4.0F, this.height);
    }
}
