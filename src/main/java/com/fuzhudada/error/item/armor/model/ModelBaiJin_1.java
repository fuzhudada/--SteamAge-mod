package com.fuzhudada.error.item.armor.model;// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBaiJin_1 extends ModelBiped {
	public final ModelRenderer Head;
	private final ModelRenderer Head_r1;
	private final ModelRenderer Head_r2;
	private final ModelRenderer Head_r3;
	private final ModelRenderer Head_r4;
	private final ModelRenderer Head_r5;
	private final ModelRenderer Head_r6;
	public final ModelRenderer Body;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;
	private final ModelRenderer cube_r12;
	private final ModelRenderer cube_r13;
	private final ModelRenderer pifeng;
	private final ModelRenderer cube_r14;
	private final ModelRenderer cube_r15;
	private final ModelRenderer cube_r16;
	private final ModelRenderer cube_r17;
	public final ModelRenderer RightArm;
	public final ModelRenderer LeftArm;

	public ModelBaiJin_1() {
		textureWidth = 128;
		textureHeight = 128;

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.cubeList.add(new ModelBox(Head, 0, 12, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.3F, false));
		Head.cubeList.add(new ModelBox(Head, 0, 0, -5.0F, -8.0F, -5.0F, 10, 2, 10, 0.0F, false));

		Head_r1 = new ModelRenderer(this);
		Head_r1.setRotationPoint(3.0F, 23.0F, -2.0F);
		Head.addChild(Head_r1);
		setRotationAngle(Head_r1, -0.0436F, 0.0F, 0.0F);
		Head_r1.cubeList.add(new ModelBox(Head_r1, 20, 28, -3.5F, -31.0F, -5.0F, 1, 2, 1, 0.0F, false));

		Head_r2 = new ModelRenderer(this);
		Head_r2.setRotationPoint(3.0F, 24.0F, 3.0F);
		Head.addChild(Head_r2);
		setRotationAngle(Head_r2, 0.0873F, 0.0F, 0.0F);
		Head_r2.cubeList.add(new ModelBox(Head_r2, 0, 28, -3.5F, -35.0F, -5.0F, 1, 3, 1, 0.0F, false));

		Head_r3 = new ModelRenderer(this);
		Head_r3.setRotationPoint(2.0F, -7.0F, -4.0F);
		Head.addChild(Head_r3);
		setRotationAngle(Head_r3, 0.0865F, -0.0114F, 0.1304F);
		Head_r3.cubeList.add(new ModelBox(Head_r3, 32, 42, -1.0F, -2.5079F, -0.7285F, 1, 2, 1, 0.0F, false));

		Head_r4 = new ModelRenderer(this);
		Head_r4.setRotationPoint(-2.0F, -8.0F, -5.0F);
		Head.addChild(Head_r4);
		setRotationAngle(Head_r4, 0.0865F, 0.0114F, -0.1304F);
		Head_r4.cubeList.add(new ModelBox(Head_r4, 36, 42, 0.0F, -1.4245F, 0.1806F, 1, 2, 1, 0.0F, false));

		Head_r5 = new ModelRenderer(this);
		Head_r5.setRotationPoint(4.0F, -7.0F, -5.0F);
		Head.addChild(Head_r5);
		setRotationAngle(Head_r5, 0.0859F, -0.0151F, 0.1739F);
		Head_r5.cubeList.add(new ModelBox(Head_r5, 0, 44, -1.0F, -1.4245F, 0.1806F, 1, 2, 1, 0.0F, false));

		Head_r6 = new ModelRenderer(this);
		Head_r6.setRotationPoint(-4.0F, -6.0F, -4.0F);
		Head.addChild(Head_r6);
		setRotationAngle(Head_r6, 0.0859F, 0.0151F, -0.1739F);
		Head_r6.cubeList.add(new ModelBox(Head_r6, 36, 45, 0.0F, -2.5079F, -0.7285F, 1, 2, 1, 0.0F, false));

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 0, 28, -4.0F, 0.0F, -2.0F, 8, 12, 4, 0.3F, false));
		Body.cubeList.add(new ModelBox(Body, 36, 45, -5.0F, -1.0F, -3.0F, 1, 9, 6, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 40, 36, -10.0F, -1.0F, -3.0F, 5, 3, 6, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 30, 0, 5.0F, -1.0F, -3.0F, 5, 3, 6, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 0, 44, 4.0F, -1.0F, -3.0F, 1, 9, 6, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 50, 45, -4.0F, -1.0F, 2.0F, 8, 9, 1, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 50, 55, -4.0F, 1.0F, -3.0F, 8, 7, 1, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(12.0F, 3.0F, -5.0F);
		Body.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.2618F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 0, -7.0F, -1.0F, 8.0F, 2, 2, 2, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 4, -19.0F, -1.0F, 8.0F, 2, 2, 2, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, -0.5F, 0.0F);
		Body.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, -0.3491F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 8, 44, -3.5F, 7.3F, -3.0F, 5, 2, 1, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, -0.5F, 0.0F);
		Body.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, 0.3491F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 24, 12, -1.5F, 7.3F, -3.0F, 5, 2, 1, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 1.0F, 0.0F);
		Body.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 0.1745F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 0, 18, -4.0F, 0.0F, -3.0F, 3, 1, 1, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, -0.1745F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 8, 1.0F, 1.0F, -3.0F, 3, 1, 1, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, 2.0F, -4.0F);
		Body.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, 0.7854F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 24, 15, -1.0F, -1.0F, 0.0F, 2, 2, 1, 0.0F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(6.0F, 1.0F, -4.0F);
		Body.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, 0.7854F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 30, 0, -1.0F, -1.0F, 0.0F, 2, 2, 1, 0.0F, false));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(-6.0F, 1.0F, -4.0F);
		Body.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, 0.7854F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 30, 3, -1.0F, -1.0F, 0.0F, 2, 2, 1, 0.0F, false));

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(0.0F, 1.0F, -3.0F);
		Body.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, 0.0F, 0.7854F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 40, 9, -1.0F, -1.0F, 0.0F, 2, 2, 1, 0.0F, false));

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(-22.0F, 1.0F, 0.0F);
		Body.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 0.0F, 0.1309F);
		cube_r10.cubeList.add(new ModelBox(cube_r10, 46, 3, 11.0F, -2.0F, -3.0F, 2, 6, 6, 0.0F, false));

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(-2.0F, 4.0F, 0.0F);
		Body.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, 0.0F, -0.1309F);
		cube_r11.cubeList.add(new ModelBox(cube_r11, 46, 22, 11.0F, -2.0F, -3.0F, 2, 6, 6, 0.0F, false));

		cube_r12 = new ModelRenderer(this);
		cube_r12.setRotationPoint(-2.0F, -2.0F, 0.0F);
		Body.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.0F, 0.0F, 0.1745F);
		cube_r12.cubeList.add(new ModelBox(cube_r12, 8, 53, 11.0F, -2.0F, -3.0F, 2, 3, 6, 0.0F, false));

		cube_r13 = new ModelRenderer(this);
		cube_r13.setRotationPoint(-2.0F, -2.0F, 0.0F);
		Body.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.0F, 0.0F, -0.1309F);
		cube_r13.cubeList.add(new ModelBox(cube_r13, 56, 0, -9.0F, -1.0F, -3.0F, 2, 3, 6, 0.0F, false));

		pifeng = new ModelRenderer(this);
		pifeng.setRotationPoint(0.0F, 1.0F, 3.0F);
		Body.addChild(pifeng);


		cube_r14 = new ModelRenderer(this);
		cube_r14.setRotationPoint(0.0F, 14.0F, -4.0F);
		pifeng.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.4272F, 0.0916F, -0.1983F);
		cube_r14.cubeList.add(new ModelBox(cube_r14, 4, 12, -5.0F, -1.0F, 7.5F, 1, 5, 1, 0.0F, false));

		cube_r15 = new ModelRenderer(this);
		cube_r15.setRotationPoint(0.0F, 14.0F, -4.0F);
		pifeng.addChild(cube_r15);
		setRotationAngle(cube_r15, 0.4272F, -0.0916F, 0.1983F);
		cube_r15.cubeList.add(new ModelBox(cube_r15, 0, 12, 4.0F, -1.0F, 7.5F, 1, 5, 1, 0.0F, false));

		cube_r16 = new ModelRenderer(this);
		cube_r16.setRotationPoint(0.0F, 14.0F, -4.0F);
		pifeng.addChild(cube_r16);
		setRotationAngle(cube_r16, 0.4363F, 0.0F, 0.0F);
		cube_r16.cubeList.add(new ModelBox(cube_r16, 48, 15, -4.0F, 0.0F, 7.5F, 8, 5, 1, 0.0F, false));

		cube_r17 = new ModelRenderer(this);
		cube_r17.setRotationPoint(0.0F, 2.0F, -8.0F);
		pifeng.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.2618F, 0.0F, 0.0F);
		cube_r17.cubeList.add(new ModelBox(cube_r17, 24, 28, -5.0F, -1.0F, 8.0F, 10, 13, 1, 0.0F, false));

		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightArm.cubeList.add(new ModelBox(RightArm, 20, 42, -3.0F, -2.0F, -2.0F, 4, 12, 4, 0.28F, false));

		LeftArm = new ModelRenderer(this);
		LeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		LeftArm.cubeList.add(new ModelBox(LeftArm, 32, 12, -1.0F, -2.0F, -2.0F, 4, 12, 4, 0.28F, false));

		this.bipedHeadwear.addChild(Head);
		this.bipedBody.addChild(Body);
		this.bipedLeftArm.addChild(LeftArm);
		this.bipedRightArm.addChild(RightArm);
		this.bipedHeadwear.showModel=false;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.bipedHead.showModel=false;
		this.pifeng.rotateAngleX= (float) Math.min(Math.PI/4,2*(entity.motionX*entity.motionX+entity.motionZ*entity.motionZ));
		super.render(entity, f, f1, f2, f3, f4, f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}