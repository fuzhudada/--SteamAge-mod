package com.fuzhudada.error.entity.model;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderFireLantern extends RenderLiving<EntityFireLantern> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/entity/fire_lantern.png");

    public RenderFireLantern(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelFireLantern(), 0.3F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityFireLantern entity)
    {
        return TEXTURES;
    }
}
