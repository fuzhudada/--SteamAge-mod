package com.fuzhudada.error.entity.model;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelFlower extends AnimatedGeoModel<EntityFlower> {
    @Override
    public ResourceLocation getModelLocation(EntityFlower object) {
        return new ResourceLocation("error","geo/flower.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityFlower object) {
        if(object.getHealth()>2000) {
            return new ResourceLocation("error", "textures/entity/flower.png");
        }else{
            return new ResourceLocation("error", "textures/entity/flower1.png");
        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityFlower animatable) {
        return new ResourceLocation("error","animations/flower_head_rotation.animation.json");
    }
}
