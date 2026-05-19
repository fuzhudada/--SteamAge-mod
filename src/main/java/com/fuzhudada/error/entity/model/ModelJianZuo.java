package com.fuzhudada.error.entity.model;// Made with Blockbench 4.8.1
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelJianZuo extends ModelBase {
	private final ModelRenderer bone;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;

	public ModelJianZuo() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 62, -1.0F, -30.0F, -3.0F, 2, 20, 6, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 54, 48, -4.0F, -10.0F, -7.0F, 8, 4, 14, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 22, -10.0F, -2.0F, -10.0F, 20, 2, 20, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -10.0F, -6.0F, -10.0F, 20, 2, 20, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 30, -2.0F, -43.0F, -2.0F, 4, 2, 4, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 44, -9.0F, -4.0F, -8.0F, 18, 2, 16, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.7854F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 22, -2.0F, -24.0F, 20.0F, 4, 4, 4, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, -0.7854F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 49, -1.0F, -41.0F, -1.0F, 2, 9, 2, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-2.0F, -30.0F, 0.0F);
		bone.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.1309F, 0.0F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 52, 50, 0.0F, -4.0F, -11.0F, 4, 5, 1, 0.0F, false));
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 44, 0.0F, -2.0F, -10.0F, 4, 1, 4, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(-2.0F, -30.0F, 0.0F);
		bone.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.2618F, 0.0F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 0, 0, 0.0F, -3.0F, -6.0F, 4, 3, 6, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(-2.0F, -30.0F, 0.0F);
		bone.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.1309F, 0.0F, 0.0F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 52, 44, 0.0F, -4.0F, 10.0F, 4, 5, 1, 0.0F, false));
		cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 36, 0.0F, -2.0F, 6.0F, 4, 1, 4, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(-2.0F, -30.0F, 0.0F);
		bone.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.2618F, 0.0F, 0.0F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 9, 0.0F, -3.0F, 0.0F, 4, 3, 6, 0.0F, false));
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