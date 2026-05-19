package com.fuzhudada.error.entity.model;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBookClose extends RenderLiving<EntityBookClose> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/entity/book.png");

    public RenderBookClose(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBookClose(), 0.6F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBookClose entity)
    {
        return TEXTURES;
    }
}
