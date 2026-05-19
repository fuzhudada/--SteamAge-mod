package com.fuzhudada.error.entity.model;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderZhengQiChe extends RenderLiving<EntityZhengQiChe> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/entity/zhengqiche.png");

    public RenderZhengQiChe(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelZhengQiChe(), 0.5F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityZhengQiChe entity)
    {
        return TEXTURES;
    }
}
