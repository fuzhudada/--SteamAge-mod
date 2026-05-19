package com.fuzhudada.error.item.armor.model;// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBloodyArmor extends ModelBiped {
	public final ModelRenderer Head;
	private final ModelRenderer HatLayer_r1;
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
	public final ModelRenderer LeftArm;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;

	public ModelBloodyArmor() {
		textureWidth = 128;
		textureHeight = 128;

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 1.0F, 0.0F);
		Head.cubeList.add(new ModelBox(Head, 24, 21, -4.0F, -9.0F, -4.0F, 8, 7, 8, 0.7F, false));

		HatLayer_r1 = new ModelRenderer(this);
		HatLayer_r1.setRotationPoint(0.0F, 24.0F, 4.0F);
		Head.addChild(HatLayer_r1);
		setRotationAngle(HatLayer_r1, 0.1745F, 0.0F, 0.0F);
		HatLayer_r1.cubeList.add(new ModelBox(HatLayer_r1, 0, 29, -4.0F, -31.0F, -4.0F, 8, 4, 7, 1.0F, false));

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 0, 11, -5.0F, -1.0F, -3.0F, 10, 12, 6, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 0, 0, -6.0F, 9.0F, -4.0F, 12, 3, 8, -0.7F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(6.0F, 13.0F, 0.0F);
		Body.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, -0.1745F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 62, 61, -1.0F, -2.0F, -3.0F, 1, 6, 6, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(6.0F, 11.0F, 0.0F);
		Body.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, -0.3491F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 64, 18, -1.0F, -2.0F, -3.0F, 1, 4, 6, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-6.0F, 13.0F, 0.0F);
		Body.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, 0.1745F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 48, 57, 0.0F, -2.0F, -3.0F, 1, 6, 6, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(-6.0F, 11.0F, 0.0F);
		Body.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 0.3491F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 66, 42, 0.0F, -2.0F, -3.0F, 1, 4, 6, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(4.0F, 0.0F, -1.0F);
		Body.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, 0.3927F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 42, 44, 0.0F, -3.0F, -3.0F, 2, 5, 8, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(-3.0F, -0.3F, -1.0F);
		Body.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, -0.829F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 56, 51, -6.0F, -3.7F, -2.0F, 2, 4, 6, 0.0F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(-4.0F, 1.0F, -1.0F);
		Body.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, -0.6545F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 40, 0, -3.0F, -4.0F, -2.0F, 3, 5, 6, 0.0F, false));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(-4.0F, 0.0F, -1.0F);
		Body.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, -0.3927F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 0, 40, -2.0F, -4.0F, -3.0F, 2, 6, 8, 0.0F, false));

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(0.0F, 4.0F, -3.0F);
		Body.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.3491F, 0.0F, 0.0F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 32, 11, -5.0F, -4.0F, -1.0F, 10, 7, 3, 0.0F, false));

		LeftArm = new ModelRenderer(this);
		LeftArm.setRotationPoint(-1.0F, 0.0F, 0.0F);
		

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(3.0F, 5.0F, 0.0F);
		LeftArm.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 0.0F, 0.2618F);
		cube_r10.cubeList.add(new ModelBox(cube_r10, 52, 15, -2.0F, -1.0F, -3.0F, 3, 3, 6, 0.0F, false));

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(3.0F, 4.0F, 0.0F);
		LeftArm.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, 0.0F, 0.4363F);
		cube_r11.cubeList.add(new ModelBox(cube_r11, 55, 5, -2.0F, -2.0F, -3.0F, 3, 3, 6, 0.0F, false));

		this.bipedHeadwear.addChild(Head);
		this.bipedBody.addChild(Body);
		this.bipedLeftArm.addChild(LeftArm);
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