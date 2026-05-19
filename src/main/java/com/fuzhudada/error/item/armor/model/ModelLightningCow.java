package com.fuzhudada.error.item.armor.model;
// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLightningCow extends ModelBiped {
	public final ModelRenderer Head;
	private final ModelRenderer cube_r1;
	public final ModelRenderer Body;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer cube_r9;
	public final ModelRenderer RightArm;
	private final ModelRenderer cube_r10;
	public final ModelRenderer LeftArm;
	private final ModelRenderer cube_r11;
	public final ModelRenderer RightLeg;
	public final ModelRenderer LeftLeg;

	public ModelLightningCow() {
		textureWidth = 128;
		textureHeight = 128;

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -9.0F, 0.0F);
		setRotationAngle(Head, 0.0F, 3.1416F, 0.0F);
		Head.cubeList.add(new ModelBox(Head, 0, 18, -4.0F, -3.0F, -4.0F, 8, 3, 8, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 1.0F, 0.0F);
		Head.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -0.7854F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 0, -8.0F, -1.0F, -8.0F, 16, 2, 16, 0.0F, false));

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 0, 29, -4.0F, 0.0F, -2.0F, 8, 12, 4, 0.3F, false));
		Body.cubeList.add(new ModelBox(Body, 0, 61, -3.0F, 5.0F, -2.5F, 6, 9, 1, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 24, 29, -5.0F, 6.0F, -3.0F, 10, 2, 6, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(4.0F, 13.0F, 0.0F);
		Body.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, -0.1309F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 56, 26, 0.0F, -5.0F, -2.0F, 1, 13, 5, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-4.0F, 13.0F, 0.0F);
		Body.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, 0.1309F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 56, 26, -1.0F, -5.0F, -2.0F, 1, 13, 5, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 7.5F, -4.0F);
		Body.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 0.7854F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 58, 18, -2.0F, -2.0F, 0.5F, 3, 3, 1, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, 0.1745F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 24, 37, 3.0F, -3.0F, -3.0F, 7, 2, 6, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, -1.0F, 0.0F);
		Body.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, -0.1745F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 32, 18, -10.0F, -2.0F, -3.0F, 7, 2, 6, 0.0F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(0.0F, 0.0F, 1.0F);
		Body.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0436F, 0.0F, 0.0F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 56, 44, -4.0F, 5.0F, 0.5F, 8, 9, 1, 0.0F, false));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(0.0F, 0.0F, 2.0F);
		Body.addChild(cube_r8);
		setRotationAngle(cube_r8, -0.0873F, 0.0F, 0.0F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 16, 57, -4.0F, -1.0F, 0.0F, 8, 7, 1, 0.0F, false));

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(0.0F, 0.0F, -3.0F);
		Body.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0873F, 0.0F, 0.0F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 56, 54, -4.0F, -1.0F, 0.0F, 8, 7, 1, 0.0F, false));

		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightArm.cubeList.add(new ModelBox(RightArm, 0, 45, -3.0F, -2.0F, -2.0F, 4, 12, 4, 0.3F, false));

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightArm.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 0.0F, 0.0436F);
		cube_r10.cubeList.add(new ModelBox(cube_r10, 16, 45, -4.0F, -2.3F, -3.0F, 4, 6, 6, 0.0F, false));

		LeftArm = new ModelRenderer(this);
		LeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		LeftArm.cubeList.add(new ModelBox(LeftArm, 0, 45, -1.0F, -2.0F, -2.0F, 4, 12, 4, 0.3F, false));

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(0.0F, 0.0F, 0.0F);
		LeftArm.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, 0.0F, -0.0873F);
		cube_r11.cubeList.add(new ModelBox(cube_r11, 36, 45, 0.0F, -2.3F, -3.0F, 4, 6, 6, 0.0F, false));

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-2.0F, 10.0F, 0.0F);
		RightLeg.cubeList.add(new ModelBox(RightLeg, 34, 57, 0.0F, 0.0F, -3.0F, 4, 2, 5, 0.3F, false));

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(-2.0F, 10.0F, 0.0F);
		LeftLeg.cubeList.add(new ModelBox(LeftLeg , 34, 57, 0.0F, 0.0F, -3.0F, 4, 2, 5, 0.3F, false));

		this.bipedHeadwear.addChild(Head);
		this.bipedBody.addChild(Body);
		this.bipedLeftArm.addChild(LeftArm);
		this.bipedRightArm.addChild(RightArm);
		this.bipedLeftLeg.addChild(LeftLeg);
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