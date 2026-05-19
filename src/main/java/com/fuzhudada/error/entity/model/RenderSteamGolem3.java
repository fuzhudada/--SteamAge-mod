package com.fuzhudada.error.entity.model;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSteamGolem3 extends RenderLiving<EntitySteamGolem3> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/entity/steam_golem3.png");

    public RenderSteamGolem3(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSteamGolem3(), 0.6F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySteamGolem3 entity)
    {
        return TEXTURES;
    }
}
