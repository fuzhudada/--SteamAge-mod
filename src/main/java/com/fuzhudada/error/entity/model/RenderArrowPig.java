package com.fuzhudada.error.entity.model;

import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class RenderArrowPig extends RenderLivingBase<EntityArrowPig> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/entity/arrow_pig.png");

    public RenderArrowPig(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelArrowPig(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(@Nonnull EntityArrowPig entity) {
        return TEXTURES;
    }
}
