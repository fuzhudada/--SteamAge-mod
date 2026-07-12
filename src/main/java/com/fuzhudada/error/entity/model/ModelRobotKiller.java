package com.fuzhudada.error.entity.model;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelRobotKiller extends AnimatedGeoModel<EntityRobotKiller> {
	@Override
	public ResourceLocation getModelLocation(EntityRobotKiller object) {
		return new ResourceLocation("error","geo/robot_killer.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(EntityRobotKiller object) {
		return new ResourceLocation("error", "textures/entity/robot_killer.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(EntityRobotKiller animatable) {
		return new ResourceLocation("error","animations/robot_killer.animation.json");
	}
}
