package com.fuzhudada.error.entity.model;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderGunCarrier extends RenderLiving<EntityGunCarrier> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/entity/gun_carrier.png");

    public RenderGunCarrier(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelGunCarrier(), 0.6F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityGunCarrier entity)
    {
        return TEXTURES;
    }
}
