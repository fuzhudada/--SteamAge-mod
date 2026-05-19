package com.fuzhudada.error.item.armor.model;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import javax.annotation.Nonnull;

public class ModelDongLiJia_1 extends ModelBiped {
	private final ModelRenderer bone;
	public final ModelRenderer LeftLeg;
	public final ModelRenderer RightLeg;
	public final ModelRenderer LeftArm;
	public final ModelRenderer RightArm;
	public final ModelRenderer Body;
	private final ModelRenderer Body_r1;
	private final ModelRenderer Body_r2;
	private final ModelRenderer Body_r3;
	private final ModelRenderer Body_r4;
	public final ModelRenderer Head;

	public ModelDongLiJia_1() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(1.9F, -12.0F, 0.0F);
		bone.addChild(LeftLeg);
		LeftLeg.cubeList.add(new ModelBox(LeftLeg, 0, 51, -3.5F, 20.0F, -2.0F, 4, 5, 4, 0.5F, false));

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-1.9F, -12.0F, 0.0F);
		bone.addChild(RightLeg);
		RightLeg.cubeList.add(new ModelBox(RightLeg, 54, 22, -0.5F, 20.0F, -2.0F, 4, 5, 4, 0.5F, false));

		LeftArm = new ModelRenderer(this);
		LeftArm.setRotationPoint(5.0F, -22.0F, 0.0F);
		bone.addChild(LeftArm);
		LeftArm.cubeList.add(new ModelBox(LeftArm, 24, 43, -5.0F, 20.0F, -2.0F, 4, 12, 4, 0.5F, false));

		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(-5.0F, -22.0F, 0.0F);
		bone.addChild(RightArm);
		RightArm.cubeList.add(new ModelBox(RightArm, 40, 43, 1.0F, 20.0F, -2.0F, 4, 12, 4, 0.5F, false));

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, -24.0F, 0.0F);
		bone.addChild(Body);
		Body.cubeList.add(new ModelBox(Body, 0, 35, -4.0F, 22.0F, -2.5F, 8, 12, 4, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 56, 53, 3.0F, 15.0F, 3.5F, 2, 10, 2, 1.0F, false));
		Body.cubeList.add(new ModelBox(Body, 56, 41, -5.0F, 15.0F, 3.5F, 2, 10, 2, 1.0F, false));
		Body.cubeList.add(new ModelBox(Body, 0, 10, -4.0F, 14.0F, -2.5F, 8, 20, 5, 0.75F, false));
		Body.cubeList.add(new ModelBox(Body, 0, 0, -9.0F, 19.0F, -3.5F, 18, 4, 6, 0.0F, false));

		Body_r1 = new ModelRenderer(this);
		Body_r1.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.addChild(Body_r1);
		setRotationAngle(Body_r1, -0.0873F, 0.0F, 0.0F);
		Body_r1.cubeList.add(new ModelBox(Body_r1, 48, 0, -4.0F, 4.0F, -3.9F, 8, 9, 1, 0.0F, false));

		Body_r2 = new ModelRenderer(this);
		Body_r2.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.addChild(Body_r2);
		setRotationAngle(Body_r2, 0.2618F, 0.0F, 0.0F);
		Body_r2.cubeList.add(new ModelBox(Body_r2, 21, 10, -2.0F, -11.0F, 3.5F, 4, 1, 1, 1.0F, false));
		Body_r2.cubeList.add(new ModelBox(Body_r2, 26, 26, -4.0F, -10.0F, -3.5F, 8, 11, 6, 1.0F, false));

		Body_r3 = new ModelRenderer(this);
		Body_r3.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.addChild(Body_r3);
		setRotationAngle(Body_r3, 0.2182F, 0.0F, 0.0F);
		Body_r3.cubeList.add(new ModelBox(Body_r3, 16, 54, 6.0F, -8.5F, 5.5F, 1, 5, 5, 0.0F, false));
		Body_r3.cubeList.add(new ModelBox(Body_r3, 54, 31, -7.0F, -8.5F, 5.5F, 1, 5, 5, 0.0F, false));

		Body_r4 = new ModelRenderer(this);
		Body_r4.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.addChild(Body_r4);
		setRotationAngle(Body_r4, 0.1309F, 0.0F, 0.0F);
		Body_r4.cubeList.add(new ModelBox(Body_r4, 58, 10, -5.0F, 2.5F, 4.5F, 2, 10, 1, 0.0F, false));
		Body_r4.cubeList.add(new ModelBox(Body_r4, 28, 59, 3.0F, 2.5F, 4.5F, 2, 10, 1, 0.0F, false));

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -24.0F, 0.0F);
		bone.addChild(Head);
		Head.cubeList.add(new ModelBox(Head, 26, 10, -4.0F, 16.0F, -4.0F, 8, 8, 8, 0.1F, false));

		this.bipedHeadwear.addChild(Head);
		this.bipedBody.addChild(Body);
		this.bipedLeftArm.addChild(LeftArm);
		this.bipedLeftLeg.addChild(LeftLeg);
		this.bipedRightArm.addChild(RightArm);
		this.bipedRightLeg.addChild(RightLeg);
	}

	@Override
	public void render(@Nonnull Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.bipedHead.showModel=false;
		super.render(entity, f, f1, f2, f3, f4, f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}