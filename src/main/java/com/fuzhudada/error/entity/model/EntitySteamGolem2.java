package com.fuzhudada.error.entity.model;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.world.World;

public class EntitySteamGolem2 extends EntityCreature {
    public EntitySteamGolem2(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D, 0.0F));
    }
}
