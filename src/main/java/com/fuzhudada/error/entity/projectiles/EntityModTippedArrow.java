package com.fuzhudada.error.entity.projectiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityModTippedArrow extends EntityTippedArrow {
    public EntityModTippedArrow(World worldIn) {
        super(worldIn);
    }

    public EntityModTippedArrow(World worldIn , double x , double y , double z) {
        super(worldIn , x , y , z);
    }

    public EntityModTippedArrow(World worldIn , EntityLivingBase shooter) {
        super(worldIn , shooter);
    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {
        Entity entity = raytraceResultIn.entityHit;
        if(entity != null){
            entity.hurtResistantTime=0;
        }
        super.onHit(raytraceResultIn);
    }
}
