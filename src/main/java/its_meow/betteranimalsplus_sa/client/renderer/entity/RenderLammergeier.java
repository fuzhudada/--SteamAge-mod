package its_meow.betteranimalsplus_sa.client.renderer.entity;

import javax.annotation.Nonnull;

import its_meow.betteranimalsplus_sa.client.model.ModelLammergeier;
import its_meow.betteranimalsplus_sa.common.entity.EntityLammergeier;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLammergeier extends RenderLiving<EntityLammergeier> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/entity/lammergeier.png");

    public RenderLammergeier(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelLammergeier(), 0.3F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityLammergeier entity) {
        return TEXTURES;
    }

    @Override
    protected void preRenderCallback(EntityLammergeier entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(2,2,2);
    }
}
