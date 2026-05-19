package com.fuzhudada.error.entity.model;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderJianZuo extends RenderLiving<EntityJianZuo>
{
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/entity/jianzuo.png");

    public RenderJianZuo(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelJianZuo(), 1.2F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityJianZuo entity)
    {
        return TEXTURES;
    }
}
