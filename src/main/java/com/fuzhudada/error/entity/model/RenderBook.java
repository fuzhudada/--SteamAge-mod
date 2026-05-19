package com.fuzhudada.error.entity.model;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBook extends RenderLiving<EntityBook> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/entity/book.png");

    public RenderBook(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBook(), 0.6F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBook entity)
    {
        return TEXTURES;
    }
}
