package com.fuzhudada.error.entity.model;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSteamGolem1 extends RenderLiving<EntitySteamGolem1> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/entity/steam_golem1.png");

    public RenderSteamGolem1(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSteamGolem1(), 0.6F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySteamGolem1 entity)
    {
        return TEXTURES;
    }
}
