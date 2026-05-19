package com.fuzhudada.error.entity.model;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBomb extends RenderLiving<EntityBomb> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("error","textures/items/item/bomb.png");
    private static final ResourceLocation TEXTURES1 = new ResourceLocation("error","textures/items/item/fire_bomb.png");
    private static final ResourceLocation TEXTURES2 = new ResourceLocation("error","textures/items/item/electricial_bomb.png");

    public RenderBomb(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBomb(), 0.5F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBomb entity)
    {
        if(entity.getType()==1){
            return TEXTURES1;
        }else if(entity.getType()==3){
            return TEXTURES2;
        }else{
            return TEXTURES;
        }
    }
}
