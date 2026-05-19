package com.fuzhudada.error.entity.model;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelElephant extends AnimatedGeoModel<EntityElephant> {
    @Override
    public ResourceLocation getModelLocation(EntityElephant object) {
        return new ResourceLocation("error","geo/bloody_elephant.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityElephant object) {
        return new ResourceLocation("error", "textures/entity/bloody_elephant.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityElephant animatable) {
        return new ResourceLocation("error","animations/elephant.animation.json");
    }
}