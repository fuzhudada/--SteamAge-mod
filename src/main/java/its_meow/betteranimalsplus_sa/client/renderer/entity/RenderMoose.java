package its_meow.betteranimalsplus_sa.client.renderer.entity;

import its_meow.betteranimalsplus_sa.client.model.ModelMoose;
import its_meow.betteranimalsplus_sa.common.entity.EntityMoose;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class RenderMoose extends RenderLiving<EntityMoose> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/entity/moose.png");

    public RenderMoose(RenderManager rendermanager) {
        super(rendermanager, new ModelMoose(), 0.8F);
    }
    
    @Override
    protected void preRenderCallback(@Nonnull EntityMoose bear, float partialTickTime) {
        float scale = 1.5F;
        GlStateManager.scale(scale, scale, scale);
        super.preRenderCallback(bear, partialTickTime);
    }

    @Override
    protected ResourceLocation getEntityTexture(@Nonnull EntityMoose entity) {
        return TEXTURES;
    }

}
