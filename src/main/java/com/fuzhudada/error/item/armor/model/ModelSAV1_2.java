package com.fuzhudada.error.item.armor.model;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSAV1_2 extends ModelBiped {
	private final ModelRenderer bone;
	public final ModelRenderer Body;
	public final ModelRenderer LeftLeg;
	public final ModelRenderer RightLeg;

	public ModelSAV1_2() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, -24.0F, 0.0F);
		bone.addChild(Body);
		Body.cubeList.add(new ModelBox(Body, 0, 0, -4.0F, 23.0F, -2.0F, 8, 12, 4, 0.51F, false));

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(1.9F, -12.0F, 0.0F);
		bone.addChild(LeftLeg);
		LeftLeg.cubeList.add(new ModelBox(LeftLeg, 0, 16, -4.0F, 11.0F, -2.0F, 4, 12, 4, 0.5F, false));

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-1.9F, -12.0F, 0.0F);
		bone.addChild(RightLeg);
		RightLeg.cubeList.add(new ModelBox(RightLeg, 16, 16, 0.0F, 11.0F, -2.0F, 4, 12, 4, 0.5F, false));

		this.bipedBody.addChild(Body);
		this.bipedLeftLeg.addChild(LeftLeg);
		this.bipedRightLeg.addChild(RightLeg);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}