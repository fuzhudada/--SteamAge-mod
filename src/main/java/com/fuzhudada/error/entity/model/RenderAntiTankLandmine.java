package com.fuzhudada.error.entity.model;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class RenderAntiTankLandmine extends RenderLiving<EntityAntiTankLandmine> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/items/item/anti_tank_landmine.png");

    public RenderAntiTankLandmine(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelAntiTankLandmine(), 0.0F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(@Nonnull EntityAntiTankLandmine entity)
    {
        return TEXTURES;
    }
}
