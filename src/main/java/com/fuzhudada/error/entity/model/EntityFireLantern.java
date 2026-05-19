package com.fuzhudada.error.entity.model;

import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public class EntityFireLantern extends EntityCreature {
    public EntityFireLantern(World worldIn) {
        super(worldIn);
        this.setSize(this.width, this.height*0.35F);
    }
}
