package com.fuzhudada.error.entity.model;// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSteamGolem1 extends ModelBase {
	private final ModelRenderer bone;
	private final ModelRenderer bone6;
	private final ModelRenderer cube_r1;
	private final ModelRenderer bone2;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer bone4;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer bone3;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer bone5;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;

	public ModelSteamGolem1() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 9.0F, 0.0F);
		

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 0, 25, -3.0F, 4.0F, -4.0F, 6, 4, 8, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone6.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -0.7854F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 0, -5.0F, -11.0F, -5.0F, 10, 15, 10, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 6.0F, -4.0F);
		bone.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 48, 31, -1.0F, 8.0F, 8.0F, 5, 2, 3, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 1.0F, 0.0F);
		bone2.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, -1.0036F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 24, 47, -1.0F, -2.0F, 8.0F, 3, 7, 3, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-1.0F, 6.0F, 0.0F);
		bone2.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, 0.6109F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 44, 36, 0.0F, -5.0F, 8.0F, 4, 7, 3, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 1.0F, 5.0F);
		bone.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 24, 36, -4.0F, -9.0F, -15.0F, 6, 7, 4, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 1.0F, -17.0F);
		bone4.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.7854F, 0.0F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 40, 8, 2.0F, -7.0F, -2.0F, 11, 2, 2, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, -2.0F, -17.0F);
		bone4.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.7854F, 0.0F, 0.0F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 40, 12, 2.0F, -7.0F, -2.0F, 11, 2, 2, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(-1.0F, 0.0F, -12.0F);
		bone4.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, 0.2182F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 10, 43, -6.0F, -8.0F, -3.0F, 3, 6, 4, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 6.0F, -15.0F);
		bone.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 48, 26, -1.0F, 8.0F, 8.0F, 5, 2, 3, 0.0F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(0.0F, 1.0F, 0.0F);
		bone3.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, -1.0036F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 41, 46, -1.0F, -2.0F, 8.0F, 3, 7, 3, 0.0F, false));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(-1.0F, 6.0F, 0.0F);
		bone3.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, 0.6109F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 44, 16, 0.0F, -5.0F, 8.0F, 4, 7, 3, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 1.0F, 21.0F);
		bone.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 28, 25, -4.0F, -9.0F, -15.0F, 6, 7, 4, 0.0F, false));

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(0.0F, 1.0F, -16.0F);
		bone5.addChild(cube_r9);
		setRotationAngle(cube_r9, -0.7854F, 0.0F, 0.0F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 30, 0, 2.0F, -7.0F, -2.0F, 11, 2, 2, 0.0F, false));

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(0.0F, -2.0F, -16.0F);
		bone5.addChild(cube_r10);
		setRotationAngle(cube_r10, -0.7854F, 0.0F, 0.0F);
		cube_r10.cubeList.add(new ModelBox(cube_r10, 30, 4, 2.0F, -7.0F, -2.0F, 11, 2, 2, 0.0F, false));

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(-1.0F, 0.0F, -12.0F);
		bone5.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, 0.0F, 0.2182F);
		cube_r11.cubeList.add(new ModelBox(cube_r11, 0, 37, -6.0F, -8.0F, -3.0F, 3, 6, 4, 0.0F, false));
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