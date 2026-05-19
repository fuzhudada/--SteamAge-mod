package com.fuzhudada.error.entity.model;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderFlower extends GeoEntityRenderer<EntityFlower> {
    public RenderFlower(RenderManager renderManager) {
        super(renderManager, new ModelFlower());
    }

    @Override
    public void doRender(EntityFlower entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity,x,y,z,entityYaw,partialTicks);
    }
}
