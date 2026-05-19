package com.fuzhudada.error.entity.model;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelSandGiant extends AnimatedGeoModel<EntitySandGiant> {
	@Override
	public ResourceLocation getModelLocation(EntitySandGiant object) {
		return new ResourceLocation("error","geo/sand_giant.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(EntitySandGiant object) {
		return new ResourceLocation("error", "textures/entity/sand_giant.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(EntitySandGiant animatable) {
		return new ResourceLocation("error","animations/sand_giant.animation.json");
	}
}