package com.fuzhudada.error.entity.model;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderTank extends RenderLiving<EntityTank> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/entity/tank.png");
    private final float scale;

    public RenderTank(RenderManager renderManagerIn,float scale)
    {
        super(renderManagerIn, new ModelTank(), 0.5F*scale);
        this.scale=scale;
    }

    protected void preRenderCallback(EntityTank entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(this.scale, this.scale, this.scale);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityTank entity)
    {
        return TEXTURES;
    }
}
