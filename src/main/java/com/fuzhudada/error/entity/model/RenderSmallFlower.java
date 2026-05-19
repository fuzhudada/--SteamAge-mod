package com.fuzhudada.error.entity.model;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSmallFlower extends RenderLiving<EntitySmallFlower> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/entity/ice_small_flower.png");

    public RenderSmallFlower(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSmallFlower(), 0.5F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySmallFlower entity)
    {
        return TEXTURES;
    }
}