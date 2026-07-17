package com.fuzhudada.error.entity.model;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelFlag extends AnimatedGeoModel<EntityFlag> {
    @Override
    public ResourceLocation getModelLocation(EntityFlag object) {
        return new ResourceLocation("error","geo/flag.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityFlag object) {
        return new ResourceLocation("error", "textures/entity/flag.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityFlag animatable) {
        return new ResourceLocation("error","animations/flag.animation.json");
    }
}
