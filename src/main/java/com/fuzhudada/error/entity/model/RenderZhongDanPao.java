package com.fuzhudada.error.entity.model;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderZhongDanPao extends RenderLiving<EntityZhongDanPao> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/items/weapon/zhong_pao_dan.png");

    public RenderZhongDanPao(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelZhongPaoDan(), 0.5F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityZhongDanPao entity)
    {
        return TEXTURES;
    }
}
