package com.fuzhudada.error.entity.model;// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSteamGolem3 extends ModelBase {
	private final ModelRenderer bone;
	private final ModelRenderer bone6;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer bone2;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer bone4;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer bone3;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer bone5;
	private final ModelRenderer cube_r11;
	private final ModelRenderer cube_r12;
	private final ModelRenderer cube_r13;
	private final ModelRenderer bone7;
	private final ModelRenderer cube_r14;
	private final ModelRenderer cube_r15;
	private final ModelRenderer bone8;
	private final ModelRenderer cube_r16;
	private final ModelRenderer cube_r17;

	public ModelSteamGolem3() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 9.0F, 0.0F);
		

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 0, 25, -3.0F, 4.0F, -4.0F, 6, 4, 8, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 56, 47, -9.0F, -15.0F, 1.0F, 2, 7, 2, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 0, 0, -9.0F, -15.0F, -3.0F, 2, 7, 2, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(2.0F, 3.0F, 0.0F);
		bone6.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, -0.1309F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 37, -9.0F, -9.0F, -3.0F, 3, 8, 6, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(2.0F, -2.0F, 0.0F);
		bone6.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, -0.2182F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 28, 25, -10.0F, -9.0F, -4.0F, 4, 5, 8, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone6.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, -0.7854F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 0, -5.0F, -11.0F, -5.0F, 10, 15, 10, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(2.0F, 6.0F, -4.0F);
		bone.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.1745F, 0.0F);
		bone2.cubeList.add(new ModelBox(bone2, 54, 1, -1.0F, 8.0F, 8.0F, 5, 2, 3, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 1.0F, 0.0F);
		bone2.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, -1.0036F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 52, 26, -1.0F, -2.0F, 8.0F, 3, 7, 3, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(-1.0F, 6.0F, 0.0F);
		bone2.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, 0.6109F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 42, 49, 0.0F, -5.0F, 8.0F, 4, 7, 3, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 1.0F, 5.0F);
		bone.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 38, 38, -4.0F, -9.0F, -15.0F, 6, 7, 4, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, 0.0F, -17.0F);
		bone4.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.7854F, 0.0F, 0.0F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 40, 8, 2.0F, -7.0F, -2.0F, 7, 2, 2, 0.0F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(0.0F, -2.0F, -17.0F);
		bone4.addChild(cube_r7);
		setRotationAngle(cube_r7, -0.7854F, 0.0F, 0.0F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 40, 12, 2.0F, -7.0F, -2.0F, 11, 2, 2, 0.0F, false));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(-1.0F, 0.0F, -12.0F);
		bone4.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, 0.2182F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 14, 49, -6.0F, -8.0F, -3.0F, 3, 6, 4, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(5.0F, 6.0F, -15.0F);
		bone.addChild(bone3);
		setRotationAngle(bone3, 0.0F, -0.1745F, 0.0F);
		bone3.cubeList.add(new ModelBox(bone3, 20, 25, -1.0F, 8.0F, 8.0F, 5, 2, 3, 0.0F, false));

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(0.0F, 1.0F, 0.0F);
		bone3.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, 0.0F, -1.0036F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 0, 51, -1.0F, -2.0F, 8.0F, 3, 7, 3, 0.0F, false));

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(-1.0F, 6.0F, 0.0F);
		bone3.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 0.0F, 0.6109F);
		cube_r10.cubeList.add(new ModelBox(cube_r10, 28, 49, 0.0F, -5.0F, 8.0F, 4, 7, 3, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 1.0F, 21.0F);
		bone.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 18, 38, -4.0F, -9.0F, -15.0F, 6, 7, 4, 0.0F, false));

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(0.0F, 0.0F, -16.0F);
		bone5.addChild(cube_r11);
		setRotationAngle(cube_r11, -0.7854F, 0.0F, 0.0F);
		cube_r11.cubeList.add(new ModelBox(cube_r11, 30, 0, 2.0F, -7.0F, -2.0F, 7, 2, 2, 0.0F, false));

		cube_r12 = new ModelRenderer(this);
		cube_r12.setRotationPoint(0.0F, -2.0F, -16.0F);
		bone5.addChild(cube_r12);
		setRotationAngle(cube_r12, -0.7854F, 0.0F, 0.0F);
		cube_r12.cubeList.add(new ModelBox(cube_r12, 30, 4, 2.0F, -7.0F, -2.0F, 11, 2, 2, 0.0F, false));

		cube_r13 = new ModelRenderer(this);
		cube_r13.setRotationPoint(-1.0F, 0.0F, -12.0F);
		bone5.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.0F, 0.0F, 0.2182F);
		cube_r13.cubeList.add(new ModelBox(cube_r13, 44, 16, -6.0F, -8.0F, -3.0F, 3, 6, 4, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(-6.0F, 6.0F, 3.0F);
		bone.addChild(bone7);
		setRotationAngle(bone7, 0.0F, 2.7925F, 0.0F);
		bone7.cubeList.add(new ModelBox(bone7, 54, 1, -1.0F, 8.0F, 8.0F, 5, 2, 3, 0.0F, false));

		cube_r14 = new ModelRenderer(this);
		cube_r14.setRotationPoint(0.0F, 1.0F, 0.0F);
		bone7.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.0F, 0.0F, -1.0036F);
		cube_r14.cubeList.add(new ModelBox(cube_r14, 52, 26, -1.0F, -2.0F, 8.0F, 3, 7, 3, 0.0F, false));

		cube_r15 = new ModelRenderer(this);
		cube_r15.setRotationPoint(-1.0F, 6.0F, 0.0F);
		bone7.addChild(cube_r15);
		setRotationAngle(cube_r15, 0.0F, 0.0F, 0.6109F);
		cube_r15.cubeList.add(new ModelBox(cube_r15, 42, 49, 0.0F, -5.0F, 8.0F, 4, 7, 3, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, 6.0F, 15.0F);
		bone.addChild(bone8);
		setRotationAngle(bone8, 0.0F, -2.7925F, 0.0F);
		bone8.cubeList.add(new ModelBox(bone8, 54, 1, -1.0F, 8.0F, 8.0F, 5, 2, 3, 0.0F, false));

		cube_r16 = new ModelRenderer(this);
		cube_r16.setRotationPoint(0.0F, 1.0F, 0.0F);
		bone8.addChild(cube_r16);
		setRotationAngle(cube_r16, 0.0F, 0.0F, -1.0036F);
		cube_r16.cubeList.add(new ModelBox(cube_r16, 52, 26, -1.0F, -2.0F, 8.0F, 3, 7, 3, 0.0F, false));

		cube_r17 = new ModelRenderer(this);
		cube_r17.setRotationPoint(-1.0F, 6.0F, 0.0F);
		bone8.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.0F, 0.0F, 0.6109F);
		cube_r17.cubeList.add(new ModelBox(cube_r17, 42, 49, 0.0F, -5.0F, 8.0F, 4, 7, 3, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}