package com.fuzhudada.error.entity.model;// Made with Blockbench 4.6.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelShiMoXiang extends ModelBase {
	private final ModelRenderer bone5;
	private final ModelRenderer bone;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer bone4;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer bone2;
	private final ModelRenderer cube_r8;
	private final ModelRenderer cube_r9;
	private final ModelRenderer bone3;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;

	public ModelShiMoXiang() {
		textureWidth = 128;
		textureHeight = 128;

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -12.0F, 0.0F);
		bone5.addChild(bone);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, -1.0F, 0.0F);
		bone.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.1745F, -0.3054F, -0.0436F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 80, 0, -10.0F, -37.0F, -26.0F, 11, 16, 10, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 12.0F, 0.0F);
		bone.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0873F, 0.1745F, 0.1745F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 69, 29, -18.0F, -48.0F, 13.0F, 13, 9, 11, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, -0.1309F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 48, 82, -4.0F, -17.0F, -5.0F, 10, 10, 10, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 0.3054F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 0, 40, -12.0F, -25.0F, -7.0F, 14, 12, 14, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(-7.0F, -28.0F, 0.0F);
		bone.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.7854F, 0.0F, -0.4363F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 0, -4.0F, -10.0F, -10.0F, 20, 20, 20, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone5.addChild(bone4);
		

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone4.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, -0.0873F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 70, -8.0F, -35.0F, 18.0F, 12, 16, 12, 0.0F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone4.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, -0.6981F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 92, 49, 5.0F, -19.0F, 20.0F, 10, 14, 8, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(12.0F, -42.0F, 2.0F);
		bone5.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 42, 40, -5.0F, -10.0F, -6.0F, 6, 7, 7, 0.0F, false));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(0.0F, 0.0F, -6.0F);
		bone2.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, 0.1309F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 60, 0, -6.0F, -11.0F, 0.0F, 7, 3, 7, 0.0F, false));

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(0.0F, 0.0F, -6.0F);
		bone2.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, 0.0F, 0.5672F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 81, 96, -6.0F, -3.0F, 0.0F, 7, 3, 7, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone5.addChild(bone3);
		

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(0.0F, 0.0F, -46.0F);
		bone3.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 0.0F, -0.0873F);
		cube_r10.cubeList.add(new ModelBox(cube_r10, 44, 54, -8.0F, -35.0F, 18.0F, 12, 16, 12, 0.0F, false));

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(0.0F, 0.0F, -46.0F);
		bone3.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, 0.0F, -0.6981F);
		cube_r11.cubeList.add(new ModelBox(cube_r11, 88, 74, 5.0F, -19.0F, 20.0F, 10, 14, 8, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone5.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}