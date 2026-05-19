package com.fuzhudada.error.entity.weapon;

import com.fuzhudada.error.entity.model.ModelDaPao;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class RenderShouXuanZheZhiBing extends RenderLiving<EntityCreature>
{
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/entity/dapao.png");

    public RenderShouXuanZheZhiBing(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelDaPao(), 0F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(@Nonnull EntityCreature entity)
    {
        return TEXTURES;
    }

    protected void preRenderCallback(EntityMob entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(0F, 0F,0F);
    }
}
