package com.fuzhudada.error.item.armor.model;
// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLiFu_1 extends ModelBiped {
	private final ModelRenderer bone;
	public final ModelRenderer Head;
	private final ModelRenderer cube_r1;
	public final ModelRenderer LeftLeg;
	public final ModelRenderer LeftArm;
	private final ModelRenderer LeftArm_r1;
	public final ModelRenderer RightArm;
	private final ModelRenderer RightArm_r1;
	public final ModelRenderer Body;
	private final ModelRenderer Body_r1;
	public final ModelRenderer RightLeg;

	public ModelLiFu_1() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 0.0F);
		

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(Head);
		Head.cubeList.add(new ModelBox(Head, 0, 0, -8.0F, -8.1F, -7.0F, 13, 2, 14, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 18, 16, -4.0F, -11.1F, -4.0F, 7, 3, 8, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 0, 16, 5.0F, -10.1F, -7.0F, 2, 4, 14, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.5535F, -0.2783F, -0.1602F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 0, -2.0F, -15.1F, -4.0F, 0, 7, 3, 0.0F, false));

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
		bone.addChild(LeftLeg);
		LeftLeg.cubeList.add(new ModelBox(LeftLeg, 48, 32, -3.5F, -1.5F, -4.0F, 4, 2, 6, 0.3F, false));

		LeftArm = new ModelRenderer(this);
		LeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		bone.addChild(LeftArm);
		LeftArm.cubeList.add(new ModelBox(LeftArm, 44, 42, -5.0F, -3.9F, -2.0F, 4, 11, 4, 0.5F, false));

		LeftArm_r1 = new ModelRenderer(this);
		LeftArm_r1.setRotationPoint(-5.0F, 22.0F, 0.0F);
		LeftArm.addChild(LeftArm_r1);
		setRotationAngle(LeftArm_r1, 0.0F, 0.0F, -0.2618F);
		LeftArm_r1.cubeList.add(new ModelBox(LeftArm_r1, 28, 30, 6.0F, -23.9F, -2.0F, 4, 12, 4, 1.0F, false));

		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		bone.addChild(RightArm);
		RightArm.cubeList.add(new ModelBox(RightArm, 48, 16, 1.0F, -3.9F, -2.0F, 4, 11, 4, 0.5F, false));

		RightArm_r1 = new ModelRenderer(this);
		RightArm_r1.setRotationPoint(5.0F, 22.0F, 0.0F);
		RightArm.addChild(RightArm_r1);
		setRotationAngle(RightArm_r1, 0.0F, 0.0F, 0.2618F);
		RightArm_r1.cubeList.add(new ModelBox(RightArm_r1, 20, 46, -10.0F, -23.9F, -2.0F, 4, 12, 4, 1.0F, false));

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(Body);
		Body.cubeList.add(new ModelBox(Body, 0, 34, -4.0F, 0.0F, -2.0F, 8, 12, 4, 1.0F, false));

		Body_r1 = new ModelRenderer(this);
		Body_r1.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.addChild(Body_r1);
		setRotationAngle(Body_r1, 0.1745F, 0.0F, 0.0F);
		Body_r1.cubeList.add(new ModelBox(Body_r1, 40, 0, -4.0F, -11.0F, 0.0F, 8, 9, 4, 1.0F, false));

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		bone.addChild(RightLeg);
		RightLeg.cubeList.add(new ModelBox(RightLeg, 0, 50, -0.5F, -1.5F, -4.0F, 4, 2, 6, 0.3F, false));

		this.bipedHeadwear.addChild(Head);
		this.bipedBody.addChild(Body);
		this.bipedLeftArm.addChild(LeftArm);
		this.bipedLeftLeg.addChild(LeftLeg);
		this.bipedRightArm.addChild(RightArm);
		this.bipedRightLeg.addChild(RightLeg);
		this.bipedHeadwear.showModel=false;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.bipedHead.showModel=false;
		super.render(entity, f, f1, f2, f3, f4, f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}