package com.fuzhudada.error.entity.model;

import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public class EntityGunCarrier extends EntityCreature {
    EntityGunCarrier(World worldIn){
        super(worldIn);
        this.setSize(this.width*11F, this.height*1.3F);
    }
}
