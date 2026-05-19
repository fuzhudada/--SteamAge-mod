package com.fuzhudada.error.entity.model;

import com.fuzhudada.error.particle.ModParticleHandler;
import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public class EntityBookClose extends EntityCreature {
    public EntityBookClose(World worldIn) {
        super(worldIn);
    }

    public void onUpdate()
    {
        if(world.isRemote){
            world.spawnParticle(ModParticleHandler.TEST,posX+2*Math.cos(ticksExisted*0.1),posY+getEyeHeight(),posZ+2*Math.sin(ticksExisted*0.1),0,0,0);
            world.spawnParticle(ModParticleHandler.TEST,posX+2*Math.cos(ticksExisted*0.1),posY+getEyeHeight()+2*Math.sin(ticksExisted*0.1),posZ,0,0,0);
            world.spawnParticle(ModParticleHandler.TEST,posX,posY+getEyeHeight()+2*Math.cos(ticksExisted*0.1),posZ+2*Math.sin(ticksExisted*0.1),0,0,0);
        }

        super.onUpdate();
    }
}
