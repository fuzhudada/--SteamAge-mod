package com.fuzhudada.error.entity.model;// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGunCarrier1 extends ModelBase {
	private final ModelRenderer bone2;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer bone;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer cube_r12;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer cube_r13;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;

	public ModelGunCarrier1() {
		textureWidth = 512;
		textureHeight = 512;

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 19.0F, 0.0F);
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, -21.0F, -15.0F, -46.0F, 40, 16, 91, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 107, -32.0F, -17.0F, -55.0F, 11, 20, 103, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 125, 127, 19.0F, -17.0F, -55.0F, 11, 20, 103, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 230, -30.0F, 3.0F, -53.0F, 9, 3, 99, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 163, 8, 19.0F, 3.0F, -53.0F, 9, 3, 99, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, -22.0F, 6.0F);
		bone2.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.1309F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 37, -21.0F, 1.0F, -56.0F, 40, 14, 4, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(33.0F, -21.0F, 8.0F);
		bone2.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.1309F, 0.0F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 125, 133, -14.0F, -3.0F, -66.0F, 11, 15, 8, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 143, -65.0F, -3.0F, -66.0F, 11, 15, 8, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(33.0F, -2.0F, -16.0F);
		bone2.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.1745F, 0.0F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 42, 0, -14.0F, -4.0F, 60.0F, 11, 19, 7, 0.0F, false));
		cube_r3.cubeList.add(new ModelBox(cube_r3, 125, 107, -65.0F, -4.0F, 60.0F, 11, 19, 7, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 4.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 250, 110, -20.0F, -28.0F, -5.0F, 38, 28, 48, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(-13.0F, -19.0F, 40.0F);
		bone.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.1745F, 0.0F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 0, 0, -6.0F, -23.0F, -1.0F, 7, 23, 7, 0.0F, false));
		cube_r4.cubeList.add(new ModelBox(cube_r4, 0, 0, 24.0F, -23.0F, -1.0F, 7, 23, 7, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(-20.0F, -15.0F, 31.0F);
		bone.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.1745F, 0.0F, 0.0F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 0, -6.0F, -23.0F, -1.0F, 7, 23, 7, 0.0F, false));
		cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 0, 38.0F, -23.0F, -1.0F, 7, 23, 7, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(23.0F, -8.0F, 21.0F);
		bone.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.1745F, 0.0F, 0.0F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 0, -6.0F, -23.0F, -1.0F, 7, 23, 7, 0.0F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(-20.0F, -9.0F, 21.0F);
		bone.addChild(cube_r7);
		setRotationAngle(cube_r7, -0.1745F, 0.0F, 0.0F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 0, 0, -6.0F, -23.0F, -1.0F, 7, 23, 7, 0.0F, false));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(13.0F, 11.0F, 8.0F);
		bone.addChild(cube_r8);
		setRotationAngle(cube_r8, -0.1555F, -0.1536F, -0.7734F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 0, 0, -1.0F, -23.0F, -56.0F, 5, 5, 32, 0.0F, false));

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(13.0F, -3.0F, 11.0F);
		bone.addChild(cube_r9);
		setRotationAngle(cube_r9, -0.1555F, -0.1536F, -0.7734F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 38, 55, -3.0F, -21.0F, -44.0F, 5, 5, 20, 0.0F, false));

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(13.0F, 4.0F, 9.0F);
		bone.addChild(cube_r10);
		setRotationAngle(cube_r10, -0.1555F, -0.1536F, -0.7734F);
		cube_r10.cubeList.add(new ModelBox(cube_r10, 0, 55, -2.0F, -22.0F, -125.0F, 5, 5, 28, 0.4F, false));
		cube_r10.cubeList.add(new ModelBox(cube_r10, 117, 250, -2.0F, -22.0F, -97.0F, 5, 5, 73, 0.0F, false));

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(0.0F, 3.0F, 4.0F);
		bone.addChild(cube_r11);
		setRotationAngle(cube_r11, -0.2182F, 0.0F, 0.0F);
		cube_r11.cubeList.add(new ModelBox(cube_r11, 0, 107, -20.0F, -28.0F, -19.0F, 38, 28, 8, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone3.cubeList.add(new ModelBox(bone3, -2, 53, -34.0F, -18.0F, 1.0F, 2, 11, 14, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 0, 0, -34.0F, -16.0F, 16.0F, 2, 8, -16, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 0, 55, -34.0F, -20.0F, 3.0F, 2, 15, 10, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 24.0F, 13.0F);
		bone4.cubeList.add(new ModelBox(bone4, 0, 0, -34.0F, -16.0F, 16.0F, 2, 8, -16, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 0, 55, -34.0F, -20.0F, 3.0F, 2, 15, 10, 0.0F, false));

		cube_r12 = new ModelRenderer(this);
		cube_r12.setRotationPoint(-33.0F, -18.0F, 14.0F);
		bone4.addChild(cube_r12);
		setRotationAngle(cube_r12, 3.1416F, 0.0F, 0.0F);
		cube_r12.cubeList.add(new ModelBox(cube_r12, -2, 53, -1.0F, -11.0F, -1.0F, 2, 11, 14, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 24.0F, -12.0F);
		bone5.cubeList.add(new ModelBox(bone5, -2, 53, -34.0F, -18.0F, 1.0F, 2, 11, 14, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 0, 0, -34.0F, -16.0F, 16.0F, 2, 8, -16, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 0, 55, -34.0F, -20.0F, 3.0F, 2, 15, 10, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 24.0F, -24.0F);
		bone6.cubeList.add(new ModelBox(bone6, -2, 53, -34.0F, -18.0F, 1.0F, 2, 11, 14, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 0, 0, -34.0F, -16.0F, 16.0F, 2, 8, -16, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 0, 55, -34.0F, -20.0F, 3.0F, 2, 15, 10, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, 0.0F, -13.0F);
		bone6.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, -2, 53, -34.0F, -18.0F, 1.0F, 2, 11, 14, 0.0F, false));
		bone7.cubeList.add(new ModelBox(bone7, 0, 0, -34.0F, -16.0F, 16.0F, 2, 8, -16, 0.0F, false));
		bone7.cubeList.add(new ModelBox(bone7, 0, 55, -34.0F, -20.0F, 3.0F, 2, 15, 10, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(-2.0F, -1.0F, 13.0F);
		setRotationAngle(bone8, 0.0F, 0.0F, -3.1416F);
		bone8.cubeList.add(new ModelBox(bone8, 0, 0, -34.0F, -16.0F, 16.0F, 2, 8, -16, 0.0F, false));
		bone8.cubeList.add(new ModelBox(bone8, 0, 55, -34.0F, -20.0F, 3.0F, 2, 15, 10, 0.0F, false));

		cube_r13 = new ModelRenderer(this);
		cube_r13.setRotationPoint(-33.0F, -18.0F, 14.0F);
		bone8.addChild(cube_r13);
		setRotationAngle(cube_r13, 3.1416F, 0.0F, 0.0F);
		cube_r13.cubeList.add(new ModelBox(cube_r13, -2, 53, -1.0F, -11.0F, -1.0F, 2, 11, 14, 0.0F, false));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(-1.0F, -1.0F, 0.0F);
		setRotationAngle(bone9, 0.0F, 0.0F, -3.1416F);
		bone9.cubeList.add(new ModelBox(bone9, -2, 53, -34.0F, -18.0F, 1.0F, 2, 11, 14, 0.0F, false));
		bone9.cubeList.add(new ModelBox(bone9, 0, 0, -34.0F, -16.0F, 16.0F, 2, 8, -16, 0.0F, false));
		bone9.cubeList.add(new ModelBox(bone9, 0, 55, -34.0F, -20.0F, 3.0F, 2, 15, 10, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(-1.0F, -1.0F, -12.0F);
		setRotationAngle(bone10, 0.0F, 0.0F, -3.1416F);
		bone10.cubeList.add(new ModelBox(bone10, -2, 53, -34.0F, -18.0F, 1.0F, 2, 11, 14, 0.0F, false));
		bone10.cubeList.add(new ModelBox(bone10, 0, 0, -34.0F, -16.0F, 16.0F, 2, 8, -16, 0.0F, false));
		bone10.cubeList.add(new ModelBox(bone10, 0, 55, -34.0F, -20.0F, 3.0F, 2, 15, 10, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(-1.0F, -1.0F, -24.0F);
		setRotationAngle(bone11, 0.0F, 0.0F, -3.1416F);
		bone11.cubeList.add(new ModelBox(bone11, -2, 53, -34.0F, -18.0F, 1.0F, 2, 11, 14, 0.0F, false));
		bone11.cubeList.add(new ModelBox(bone11, 0, 0, -34.0F, -16.0F, 16.0F, 2, 8, -16, 0.0F, false));
		bone11.cubeList.add(new ModelBox(bone11, 0, 55, -34.0F, -20.0F, 3.0F, 2, 15, 10, 0.0F, false));

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(-1.0F, -1.0F, -36.0F);
		setRotationAngle(bone12, 0.0F, 0.0F, -3.1416F);
		bone12.cubeList.add(new ModelBox(bone12, -2, 53, -34.0F, -18.0F, 1.0F, 2, 11, 14, 0.0F, false));
		bone12.cubeList.add(new ModelBox(bone12, 0, 0, -34.0F, -16.0F, 16.0F, 2, 8, -16, 0.0F, false));
		bone12.cubeList.add(new ModelBox(bone12, 0, 55, -34.0F, -20.0F, 3.0F, 2, 15, 10, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone2.render(f5);
		bone.render(f5);
		bone3.render(f5);
		bone4.render(f5);
		bone5.render(f5);
		bone6.render(f5);
		bone8.render(f5);
		bone9.render(f5);
		bone10.render(f5);
		bone11.render(f5);
		bone12.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}