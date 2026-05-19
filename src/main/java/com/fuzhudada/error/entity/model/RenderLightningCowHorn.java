package com.fuzhudada.error.entity.model;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class RenderLightningCowHorn extends RenderLiving<EntityLightningCowHorn> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/entity/lightning_cow.png");

    public RenderLightningCowHorn(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelLightningCowHorn(), 0);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(@Nonnull EntityLightningCowHorn entity)
    {
        return TEXTURES;
    }

    @Override
    protected void preRenderCallback(@Nonnull EntityLightningCowHorn entitylivingbase , float partialTickTime) {
        GlStateManager.scale(1,2,1);
    }
}
