package com.fuzhudada.error.entity.model;

import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public class EntityJianZuo extends EntityCreature {
    public EntityJianZuo(World worldIn) {
        super(worldIn);
        this.setSize(this.width*1.8F, this.height*1.2F);
    }
}
