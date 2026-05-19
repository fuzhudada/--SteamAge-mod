package com.fuzhudada.error.entity.model;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class RenderCrystalShield extends Render<EntityCrystalShield> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("error","textures/entity/crystal_shield.png");
    private final ModelCrystalShield model = new ModelCrystalShield();

    public RenderCrystalShield(RenderManager manager)
    {
        super(manager);
    }

    public void doRender(@Nonnull EntityCrystalShield entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(-2.5F, -1.0F, 2.5F);
        this.bindEntityTexture(entity);
        this.model.render(entity, 0.0F, 0.0F, partialTicks, 0.0F,0.0F, 0.03125F);
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
        GlStateManager.scale(1.5F, 1.5F, 1.5F);
        this.model.render(entity, 0.0F, 0.0F, partialTicks, 0.0F,0.0F, 0.03125F);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(@Nonnull EntityCrystalShield entity)
    {
        return TEXTURE;
    }
}
