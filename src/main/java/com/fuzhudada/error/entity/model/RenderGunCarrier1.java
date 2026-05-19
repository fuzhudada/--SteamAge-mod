package com.fuzhudada.error.entity.model;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderGunCarrier1 extends RenderLiving<EntityGunCarrier1> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/entity/gun_carrier1.png");

    public RenderGunCarrier1(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelGunCarrier1(), 0.6F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityGunCarrier1 entity)
    {
        return TEXTURES;
    }
}
