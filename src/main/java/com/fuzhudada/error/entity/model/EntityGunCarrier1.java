package com.fuzhudada.error.entity.model;

import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public class EntityGunCarrier1 extends EntityCreature {
    EntityGunCarrier1(World worldIn){
        super(worldIn);
        this.setSize(this.width*11F, this.height*1.3F);
    }
}