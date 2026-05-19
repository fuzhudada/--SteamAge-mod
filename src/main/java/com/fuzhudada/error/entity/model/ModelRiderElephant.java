package com.fuzhudada.error.entity.model;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelRiderElephant extends AnimatedGeoModel<EntityRiderElephant> {
    @Override
    public ResourceLocation getModelLocation(EntityRiderElephant object) {
        return new ResourceLocation("error","geo/elephant.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityRiderElephant object) {
        return new ResourceLocation("error", "textures/entity/elephant.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityRiderElephant animatable) {
        return new ResourceLocation("error","animations/elephant.animation.json");
    }
}