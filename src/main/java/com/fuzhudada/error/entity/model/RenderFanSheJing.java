package com.fuzhudada.error.entity.model;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFanSheJing extends RenderLiving<EntityFanSheJing>
{
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/entity/fanshejing.png");

    public RenderFanSheJing(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelFanSheJing(), 0.6F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityFanSheJing entity)
    {
        return TEXTURES;
    }
}
