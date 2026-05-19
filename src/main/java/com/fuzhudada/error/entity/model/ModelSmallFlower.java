package com.fuzhudada.error.entity.model;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSmallFlower extends ModelBase {
	private final ModelRenderer head1;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r9;
	private final ModelRenderer head2;
	private final ModelRenderer cube_r27;
	private final ModelRenderer cube_r28;
	private final ModelRenderer cube_r29;
	private final ModelRenderer cube_r30;
	private final ModelRenderer heart;
	private final ModelRenderer bone3;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;
	private final ModelRenderer cube_r12;
	private final ModelRenderer bone4;
	private final ModelRenderer cube_r13;
	private final ModelRenderer cube_r14;
	private final ModelRenderer foot;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;

	public ModelSmallFlower() {
		textureWidth = 64;
		textureHeight = 64;

		head1 = new ModelRenderer(this);
		head1.setRotationPoint(0.0F, 9.0F, 0.0F);
		head1.cubeList.add(new ModelBox(head1, 22, 26, -4.0F, 6.0F, -4.0F, 8, 2, 8, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		head1.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0869F, -0.7816F, -0.1231F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 24, 20, 3.3028F, 6.9696F, -2.9997F, 12, 0, 6, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		head1.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.0869F, 0.7816F, -0.1231F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 24, 14, 3.3028F, 6.9696F, -3.0003F, 12, 0, 6, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		head1.addChild(cube_r3);
		setRotationAngle(cube_r3, -3.0547F, 0.7816F, -3.0185F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 24, 8, 3.3028F, 6.9696F, -2.9997F, 12, 0, 6, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
		head1.addChild(cube_r4);
		setRotationAngle(cube_r4, 3.0547F, -0.7816F, -3.0185F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 0, 24, 3.3028F, 6.9696F, -3.0003F, 12, 0, 6, 0.0F, false));

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(0.0F, 0.0F, 0.0F);
		head1.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, -0.829F, 0.0F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 0, 30, -3.0F, 5.0F, -3.0F, 6, 1, 6, 0.0F, false));

		head2 = new ModelRenderer(this);
		head2.setRotationPoint(0.0F, 9.0F, 0.0F);
		

		cube_r27 = new ModelRenderer(this);
		cube_r27.setRotationPoint(0.0F, 0.0F, 0.0F);
		head2.addChild(cube_r27);
		setRotationAngle(cube_r27, 1.5708F, -1.309F, -1.5708F);
		cube_r27.cubeList.add(new ModelBox(cube_r27, 22, 0, 1.9295F, 6.7274F, -4.0F, 11, 0, 8, 0.0F, false));

		cube_r28 = new ModelRenderer(this);
		cube_r28.setRotationPoint(0.0F, 0.0F, 0.0F);
		head2.addChild(cube_r28);
		setRotationAngle(cube_r28, 3.1416F, 0.0F, -2.8798F);
		cube_r28.cubeList.add(new ModelBox(cube_r28, 0, 0, 1.9295F, 6.7274F, -4.0001F, 11, 0, 8, 0.0F, false));

		cube_r29 = new ModelRenderer(this);
		cube_r29.setRotationPoint(0.0F, 0.0F, 0.0F);
		head2.addChild(cube_r29);
		setRotationAngle(cube_r29, -1.5708F, 1.309F, -1.5708F);
		cube_r29.cubeList.add(new ModelBox(cube_r29, 0, 16, 1.9295F, 6.7274F, -4.0F, 11, 0, 8, 0.0F, false));

		cube_r30 = new ModelRenderer(this);
		cube_r30.setRotationPoint(0.0F, 0.0F, 0.0F);
		head2.addChild(cube_r30);
		setRotationAngle(cube_r30, 0.0F, 0.0F, -0.2618F);
		cube_r30.cubeList.add(new ModelBox(cube_r30, 0, 8, 1.9294F, 6.7274F, -4.0F, 11, 0, 8, 0.0F, false));

		heart = new ModelRenderer(this);
		heart.setRotationPoint(0.0F, 0.0F, 0.0F);
		head2.addChild(heart);
		heart.cubeList.add(new ModelBox(heart, 36, 42, -2.0F, 3.0F, -2.0F, 4, 2, 4, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone3.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, -2.0508F, 0.0F);
		

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone3.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, -0.0436F, 0.0F);
		cube_r11.cubeList.add(new ModelBox(cube_r11, 20, 36, -2.0F, -8.0F, -2.0F, 4, 7, 4, 0.0F, false));

		cube_r12 = new ModelRenderer(this);
		cube_r12.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone3.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.0F, -0.3491F, 0.0F);
		

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, -10.0F, 0.0F);
		bone3.addChild(bone4);
		

		cube_r13 = new ModelRenderer(this);
		cube_r13.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone4.addChild(cube_r13);
		setRotationAngle(cube_r13, -0.5672F, 0.0F, 0.0F);
		

		cube_r14 = new ModelRenderer(this);
		cube_r14.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone4.addChild(cube_r14);
		setRotationAngle(cube_r14, -0.2182F, 0.0F, 0.0F);
		

		foot = new ModelRenderer(this);
		foot.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, 0.0F, 0.0F);
		foot.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, -0.2618F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 36, 36, -7.0F, -3.0F, -2.0F, 6, 2, 4, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, 0.0F, 0.0F);
		foot.addChild(cube_r6);
		setRotationAngle(cube_r6, 3.1416F, 0.0F, -2.8798F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 37, -7.0F, -3.0F, -2.0F, 6, 2, 4, 0.0F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(0.0F, 0.0F, 0.0F);
		foot.addChild(cube_r7);
		setRotationAngle(cube_r7, 1.5708F, -1.309F, -1.5708F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 36, 36, -7.0F, -3.0F, -2.0F, 6, 2, 4, 0.0F, false));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(0.0F, 0.0F, 0.0F);
		foot.addChild(cube_r8);
		setRotationAngle(cube_r8, -1.5708F, 1.309F, -1.5708F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 36, 36, -7.0F, -3.0F, -2.0F, 6, 2, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head1.render(f5);
		head2.render(f5);
		bone3.render(f5);
		foot.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}