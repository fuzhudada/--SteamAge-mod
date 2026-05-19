package com.fuzhudada.error.entity.model;

import net.minecraft.client.renderer.entity.RenderHorse;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.ResourceLocation;

public class RenderFireHorse extends RenderHorse {
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/entity/fire_horse.png");

    public RenderFireHorse(RenderManager p_i47205_1_) {
        super(p_i47205_1_);
    }

    protected ResourceLocation getEntityTexture(EntityHorse entity)
    {
        return TEXTURES;
    }
}
