package com.fuzhudada.error.entity.model;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class RenderLightningCow extends RenderLiving<EntityLightningCow> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/entity/lightning_cow.png");

    public RenderLightningCow(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelLightningCow(), 0.6F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(@Nonnull EntityLightningCow entity)
    {
        return TEXTURES;
    }
}
